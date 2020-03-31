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
import com.synectiks.pref.business.service.CmsSubjectService;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.Subject;
import com.synectiks.pref.domain.enumeration.BatchEnum;
import com.synectiks.pref.exceptions.DuplicateRecordFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.graphql.types.subject.SubjectInput;
import com.synectiks.pref.service.util.CommonUtil;

public class SubjectDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public SubjectDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException , MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        Subject obj = CommonUtil.createCopyProperties(cls.newInstance(), Subject.class);
        
        String subjectCode = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(subjectCode)) {
        	sb.append("subject_code, ");
            logger.warn("Mandatory field missing. Field name - subject_code");
        }else {
        	obj.setSubjectCode(subjectCode);
        }
        
        String subjectType = row.getCellAsString(1).orElse(null);
        obj.setSubjectType(subjectType);
        
        String subjectDesc = row.getCellAsString(2).orElse(null);
        obj.setSubjectDesc(subjectDesc);
        
        String status = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(status)) {
        	sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        }else {
        	obj.setStatus(status);
        }
        
        String branchName = row.getCellAsString(4).orElse(null);
        Branch branch = null;
        if(CommonUtil.isNullOrEmpty(branchName) ) {
        	sb.append("branch_id, ");
            logger.warn("branch name not provided, Cannot find the branch");
        }else {
        	Branch b2 = new Branch();
        	b2.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(b2));
            if(b.isPresent()) {
            	branch = b.get();
            }else {
            	sb.append("branch_id, ");
                logger.warn("branch name not found, Cannot find the branch");
            }
        }
        
        if(branch != null) {
        	String departmentName = row.getCellAsString(5).orElse(null);
        	if(CommonUtil.isNullOrEmpty(departmentName)) {
        		sb.append("department_id, ");
                logger.warn("Mandatory field missing. Field name - department_id");
        	}else {
            	Department department = new Department();
                department.setName(departmentName);
                department.setBranch(branch);
                Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
                if(dp.isPresent()) {
                    obj.setDepartment(dp.get());
                }else {
                    sb.append("department_id, ");
                    logger.warn("Department not found. Given department name : " + departmentName);
                }
            }
        }
        
        if(obj.getDepartment() != null) {
        	String batchName = row.getCellAsString(6).orElse(null);
        	if(CommonUtil.isNullOrEmpty(batchName)) {
        		sb.append("batch_id, ");
                logger.warn("Mandatory field missing. Field name - batch_id");
        	}else {
            	Batch batch = new Batch();
                batch.setBatch(findBatch(batchName));
                batch.setDepartment(obj.getDepartment());
                Optional<Batch> bt = this.allRepositories.findRepository("batch").findOne(Example.of(batch));
                if(bt.isPresent()) {
                    obj.setBatch(bt.get());
                }else {
                    sb.append("batch_id, ");
                    logger.warn("Batch not found. Given batch : " + batchName);
                }
            }
        }
        
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        if (this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
        	String msg = "Duplicate Subject found";
        	sb.append(msg+",");
            logger.warn(msg);
            if (sb.length() > 0) {
                throw new DuplicateRecordFoundException(msg);
            }
        }
        
        return (T)obj;
    }
    
    private BatchEnum findBatch(String batchName) {
    	if(BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.FIRSTYEAR;
    	}else if(BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.SECONDYEAR;
    	}else if(BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.THIRDYEAR;
    	}else if(BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.FOURTHYEAR;
    	}
    	return null;
    }
    
    public <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls) {
		logger.debug(String.format("Saving %s data started.",this.sheetName));
		
		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			T instance = cls.newInstance();
			CmsSubjectService cmsSubjectService = PreferencesApp.getBean(CmsSubjectService.class);
			try (Stream<Row> rows = sheet.openStream()) {
//				List<T> list = new ArrayList<>();
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
								if(!allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
//									list.add(obj);
									Subject subject = CommonUtil.createCopyProperties(obj, Subject.class);
									SubjectInput inp = CommonUtil.createCopyProperties(obj, SubjectInput.class);
									inp.setDepartmentId(subject.getDepartment().getId());
									inp.setBatchId(subject.getBatch().getId());
									cmsSubjectService.saveSubject(inp);
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
