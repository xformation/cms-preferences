package com.synectiks.pref.dataimport.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.business.service.CmsBranchService;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.State;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.graphql.types.branch.BranchInput;
import com.synectiks.pref.service.util.CommonUtil;


public class BranchDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public BranchDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
		this.sheetName = sheetName;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException {
		StringBuilder sb = new StringBuilder();	
		Branch obj = CommonUtil.createCopyProperties(cls.newInstance(), Branch.class);
		
		String branchName = row.getCellAsString(0).orElse(null);
		if(CommonUtil.isNullOrEmpty(branchName)) {
			sb.append("branch_name, ");
			logger.warn("Mandatory field missing. Field name - branch_name");
		}else {
			obj.setBranchName(branchName);
		}
		
		String address = row.getCellAsString(1).orElse(null);
		if(CommonUtil.isNullOrEmpty(address)) {
			sb.append("address, ");
			logger.warn("Mandatory field missing. Field name - address_1");
		}else {
			obj.setAddress(address);
		}
		
		String branchHead = row.getCellAsString(2).orElse(null);
		obj.setBranchHead(branchHead);
		
		String collegeName = row.getCellAsString(3).orElse(null);
		if(CommonUtil.isNullOrEmpty(collegeName)) {
			sb.append("college_id, ");
			logger.warn("Mandatory field missing. Field name - college_id");
		}else {
			College college = new College();
			college.setCollegeName(collegeName);
			Optional<College> c = this.allRepositories.findRepository("college").findOne(Example.of(college));
			if(c.isPresent()) {
				obj.setCollege(c.get());
			}else {
				sb.append("college_id, ");
				logger.warn("College not found. Given college name : "+collegeName);
			}
		}
		
		String stateName = row.getCellAsString(5).orElse(null);
		if(CommonUtil.isNullOrEmpty(stateName)) {
			sb.append("state_id, ");
			logger.warn("Mandatory field missing. Field name - state_id");
		}else {
			State state = new State();
			state.setStateName(stateName);
			Optional<State> c = this.allRepositories.findRepository("state").findOne(Example.of(state));
			if(c.isPresent()) {
				obj.setState(c.get());
			}else {
				sb.append("state_id, ");
				logger.warn("State not found. Given state name : "+stateName);
			}
		}
		
		String cityName = row.getCellAsString(4).orElse(null);
		if(CommonUtil.isNullOrEmpty(cityName)) {
			sb.append("city_id, ");
			logger.warn("Mandatory field missing. Field name - city_id");
		}else {
			City city = new City();
			city.setCityName(cityName);
			Optional<City> c = this.allRepositories.findRepository("city").findOne(Example.of(city));
			if(c.isPresent()) {
				obj.setCity(c.get());
			}else {
				sb.append("city_id, ");
				logger.warn("City not found. Given city name : "+cityName);
			}
		}
		
		String status = row.getCellAsString(6).orElse(null);
		if(CommonUtil.isNullOrEmpty(status)) {
			sb.append("status, ");
			logger.warn("Mandatory field missing. Field name - status");
		}else {
			obj.setStatus(status);
		}
		if(sb.length() > 0) {
			String msg = "Field name - ";
			throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
		}
		return (T)obj;
	}
	
	public <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls) {
		logger.debug(String.format("Saving %s data started.",this.sheetName));
		
		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			T instance = cls.newInstance();
			try (Stream<Row> rows = sheet.openStream()) {
//				List<T> list = new ArrayList<>();
				List<ExceptionRecord> exceptionList = new ArrayList<>();
				StringBuilder sb = new StringBuilder(String.format("\nInvalid records found for table - %s: \n", this.sheetName));
				sb.append("Rows having invalid records\n");
				CmsBranchService service =  PreferencesApp.getBean(CmsBranchService.class);
				rows.forEach(row -> {

//					if (list.size() == CmsConstants.BATCH_SIZE) {
//						allRepositories.findRepository(this.sheetName).saveAll(list);
//						list.clear();
//					}
					if (exceptionList.size() == CmsConstants.BATCH_SIZE) {
						allRepositories.findRepository("exception_record").saveAll(exceptionList);
						exceptionList.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						try {
							T obj = getObject(row, cls);
							if(obj != null) {
								if(!allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
//									list.add(obj);
									BranchInput inp = CommonUtil.createCopyProperties(obj, BranchInput.class);
									inp.setStateId(inp.getState().getId());
									inp.setCityId(inp.getCity().getId());
									service.saveBranch(inp);
								}
							}
							
						} catch (InstantiationException | IllegalAccessException e) {
							logger.error("Exception in loading data from excel file :"+e.getMessage(), e);
						} catch(Exception e) {
							ExceptionRecord expObj = getExceptionObject(row, e);
							sb.append(String.format("%s : %s  , %s\n", e.getClass().getSimpleName(), e.getMessage(), row.toString()));
							if(expObj != null) {
								exceptionList.add(expObj);
							}
						}
					}
				});
				// Save remaining items
//				allRepositories.findRepository(this.sheetName).saveAll(list);
//				list.clear();
				if(exceptionList.size() > 0) {
					logger.warn(sb.toString());
					logger.info("Saving records having exceptions/errors in exception_record table");
					allRepositories.findRepository("exception_record").saveAll(exceptionList);
				}
				exceptionList.clear();
			}
		} catch (Exception e) {
			logger.error(String.format("Failed to iterate %s sheet rows ", this.sheetName), e);
		}
		logger.debug(String.format("Saving %s data completed.", this.sheetName));
	}
	
}
