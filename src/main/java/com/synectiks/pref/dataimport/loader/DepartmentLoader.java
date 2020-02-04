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
import com.synectiks.pref.business.service.CmsDepartmentService;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.graphql.types.department.DepartmentInput;
import com.synectiks.pref.service.util.CommonUtil;


public class DepartmentLoader extends DataLoader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public DepartmentLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        Department obj = CommonUtil.createCopyProperties(cls.newInstance(), Department.class);

        String name = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(name)) {
            sb.append("name, ");
            logger.warn("Mandatory field missing. Field name - name");
        }else {
            obj.setName(name);
        }

        String description = row.getCellAsString(1).orElse(null);
        obj.setDescription(description);

        String deptHead = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(deptHead)) {
            sb.append("deptHead, ");
            logger.warn("Mandatory field missing. Field name - dept_head");
        }else {
            obj.setDeptHead(deptHead);
        }

        String branchName = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(branchName)) {
            sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Field name - branch_id");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(b.isPresent()) {
                obj.setBranch(b.get());
            }else {
                sb.append("branch_id, ");
                logger.warn("Branch not found. Given branch name : "+branchName);
            }
        }

        String academicYear = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            sb.append("academic_year_id, ");
            logger.warn("Mandatory field missing. Field name - academic_year_id");
        }else {
            AcademicYear academicYear1 = new AcademicYear();
            academicYear1.setDescription(academicYear);
            Optional<AcademicYear> ay = this.allRepositories.findRepository("academic_year").findOne(Example.of(academicYear1));
            if(ay.isPresent()) {
                obj.setAcademicYear(ay.get());
            }else {
                sb.append("academic_year_id, ");
                logger.warn("AcademicYear not found. Given academicYear name : "+academicYear);
            }
        }

        String status = row.getCellAsString(5).orElse(null);
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
				CmsDepartmentService departmentService =  PreferencesApp.getBean(CmsDepartmentService.class);
				
				rows.forEach(row -> {

//					if (list.size() == CmsConstants.BATCH_SIZE) {
////						allRepositories.findRepository(cls).saveAll(list);
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
									Department dep = CommonUtil.createCopyProperties(obj, Department.class);
									DepartmentInput inp = CommonUtil.createCopyProperties(dep, DepartmentInput.class);
									inp.setBranchId(dep.getBranch().getId());
									inp.setAcademicYearId(dep.getAcademicYear().getId());
									departmentService.saveDepartment(inp);
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
