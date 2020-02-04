package com.synectiks.pref.dataimport.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.business.service.CmsCollegeService;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.exceptions.AdditionalCollegeFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;


public class CollegeDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private boolean isCollegeExist = false;
	private AllRepositories allRepositories;
	private String sheetName;
	public CollegeDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, AdditionalCollegeFoundException, MandatoryFieldMissingException {
//		int count = this.allRepositories.findRepository(cls).findAll().size();
		int count = this.allRepositories.findRepository(this.sheetName).findAll().size();
		String exceptionMsg = "College already exists. This application only supports one college";
		if(count > 0 || this.isCollegeExist) {
			logger.warn(exceptionMsg);
			throw new AdditionalCollegeFoundException(exceptionMsg);
		}
		StringBuilder sb = new StringBuilder();	
		
		College obj = CommonUtil.createCopyProperties(cls.newInstance(), College.class);
		
		String shortName = row.getCellAsString(0).orElse(null);
		if(CommonUtil.isNullOrEmpty(shortName)) {
			sb.append("short_name, ");
			logger.warn("Mandatory field missing. Field name - short_name");
		}else {
			obj.setCollegeName(shortName);
		}
//		obj.setLogoPath(row.getCellAsString(1).orElse(null));
//		obj.setBackgroundImagePath(row.getCellAsString(2).orElse(null));
//		obj.setInstructionInformation(row.getCellAsString(3).orElse(null));
//		obj.setLogoFileName(row.getCellAsString(4).orElse(null));
//		obj.setBackgroundImageFileName(row.getCellAsString(5).orElse(null));
		if(sb.length() > 0) {
			String msg = "Field name - ";
			throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
		}
		this.isCollegeExist = true;
		return (T)obj;
		
	}
	
	@Override
	public <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls) {
		logger.debug(String.format("Saving %s data started.",this.sheetName));
		
		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			T instance = cls.newInstance();
			try (Stream<Row> rows = sheet.openStream()) {
				List<T> list = new ArrayList<>();
				List<ExceptionRecord> exceptionList = new ArrayList<>();
				StringBuilder sb = new StringBuilder(String.format("\nInvalid records found for table - %s: \n", this.sheetName));
				sb.append("Rows having invalid records\n");
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
//								if(!allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
//									list.add(obj);
//								}
								CmsCollegeService cmsCollegeService = PreferencesApp.getBean(CmsCollegeService.class);
								CmsCollegeVo vo = CommonUtil.createCopyProperties(obj, CmsCollegeVo.class);
								cmsCollegeService.addCollege(vo);
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
