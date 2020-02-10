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
import com.synectiks.pref.business.service.CmsTeacherService;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.domain.enumeration.BatchEnum;
import com.synectiks.pref.exceptions.DuplicateRecordFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.graphql.types.teacher.TeacherInput;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

public class TeacherDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public TeacherDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException , MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        Teacher obj = CommonUtil.createCopyProperties(cls.newInstance(), Teacher.class);
        
        String teacherName = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(teacherName)) {
        	sb.append("teacher_name, ");
            logger.warn("Mandatory field missing. Field name - teacher_name");
        }else {
        	obj.setTeacherName(teacherName);
        }
        
        String teacherMiddleName = row.getCellAsString(1).orElse(null);
        obj.setTeacherMiddleName(teacherMiddleName);
        
        String teacherLastName = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(teacherLastName)) {
        	sb.append("teacher_last_name, ");
            logger.warn("Mandatory field missing. Field name - teacher_last_name");
        }else {
        	obj.setTeacherLastName(teacherLastName);
        }
        
        String fatherName = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(fatherName)) {
        	sb.append("father_name, ");
            logger.warn("Mandatory field missing. Field name - father_name");
        }else {
        	obj.setFatherName(fatherName);
        }
        
        String fatherMiddleName = row.getCellAsString(4).orElse(null);
        obj.setFatherMiddleName(fatherMiddleName);
        
        String fatherLastName = row.getCellAsString(5).orElse(null);
        if(CommonUtil.isNullOrEmpty(fatherLastName)) {
        	sb.append("father_last_name, ");
            logger.warn("Mandatory field missing. Field name - father_last_name");
        }else {
        	obj.setFatherLastName(fatherLastName);
        }
        
        String spouseName = row.getCellAsString(6).orElse(null);
        obj.setSpouseName(spouseName);
        
        String spouseMiddleName = row.getCellAsString(7).orElse(null);
        obj.setSpouseMiddleName(spouseMiddleName);
        
        String spouseLastName = row.getCellAsString(8).orElse(null);
        obj.setSpouseLastName(spouseLastName);
        
        String motherName = row.getCellAsString(9).orElse(null);
        obj.setMotherName(motherName);
        
        String motherMiddleName = row.getCellAsString(10).orElse(null);
        obj.setMotherMiddleName(motherMiddleName);
        
        String motherLastName = row.getCellAsString(11).orElse(null);
        obj.setMotherLastName(motherLastName);
        
        
        String dob = row.getCellAsString(12).orElse(null);
        if(CommonUtil.isNullOrEmpty(dob)) {
        	sb.append("date_of_birth, ");
            logger.warn("Mandatory field missing. Field name - date_of_birth");
        }else {
        	obj.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(dob, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        
        String placeOfBirth = row.getCellAsString(13).orElse(null);
        obj.setPlaceOfBirth(placeOfBirth);
        
        String religion = row.getCellAsString(14).orElse(null);
        obj.setReligion(religion);
        
        String cast = row.getCellAsString(15).orElse(null);
        obj.setCaste(cast);
        
        String gender = row.getCellAsString(16).orElse(null);
        if(CommonUtil.isNullOrEmpty(gender)) {
        	sb.append("Gender, ");
            logger.warn("Mandatory field missing. Field name - Gender");
        }else {
        	obj.setSex(gender);
        }
        
        String aadharNo = row.getCellAsString(17).orElse(null);
        obj.setAadharNo(aadharNo);
        
        String address = row.getCellAsString(18).orElse(null);
        if(CommonUtil.isNullOrEmpty(address)) {
        	sb.append("address, ");
            logger.warn("Mandatory field missing. Field name - address");
        }else {
        	obj.setAddress(address);
        }
        
        String cityName = row.getCellAsString(19).orElse(null);
		obj.setTown(cityName);
		
        String stateName = row.getCellAsString(20).orElse(null);
		obj.setState(stateName);
		
        String countryName = row.getCellAsString(21).orElse(null);
		obj.setCountry(countryName);
		
		String pinCode = row.getCellAsString(22).orElse(null);
        obj.setPinCode(pinCode);
        
        String teacherContactNumber = row.getCellAsString(23).orElse(null);
        if(CommonUtil.isNullOrEmpty(teacherContactNumber)) {
        	sb.append("teacher_contact_number, ");
            logger.warn("Mandatory field missing. Field name - teacher_contact_number");
        }else {
        	obj.setTeacherContactNumber(teacherContactNumber);
        }
        
        String alternateContactNumber = row.getCellAsString(24).orElse(null);
        obj.setAlternateContactNumber(alternateContactNumber);
        
        String teacherEmailAddress = row.getCellAsString(25).orElse(null);
        if(CommonUtil.isNullOrEmpty(teacherEmailAddress)) {
        	sb.append("teacher_email_address, ");
            logger.warn("Mandatory field missing. Field name - teacher_email_address");
        }else {
        	obj.setTeacherEmailAddress(teacherEmailAddress);
        }
        
        String alternateEmailAddress = row.getCellAsString(26).orElse(null);
        obj.setAlternateEmailAddress(alternateEmailAddress);
        
        String relationWithStaff = row.getCellAsString(27).orElse(null);
        if(CommonUtil.isNullOrEmpty(relationWithStaff)) {
        	sb.append("relation_with_staff, ");
            logger.warn("Mandatory field missing. Field name - relation_with_staff");
        }else {
        	obj.setRelationWithStaff(relationWithStaff);
        }
        
        String emergencyContactName = row.getCellAsString(28).orElse(null);
        if(CommonUtil.isNullOrEmpty(emergencyContactName)) {
        	sb.append("emergency_contact_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_name");
        }else {
        	obj.setEmergencyContactName(emergencyContactName);
        }
        
        String emergencyContactMiddleName = row.getCellAsString(29).orElse(null);
        obj.setEmergencyContactMiddleName(emergencyContactMiddleName);
        
        String emergencyContactLastName = row.getCellAsString(30).orElse(null);
        if(CommonUtil.isNullOrEmpty(emergencyContactLastName)) {
        	sb.append("emergency_contact_last_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_last_name");
        }else {
        	obj.setEmergencyContactLastName(emergencyContactLastName);
        }
        
        String emergencyContactNo = row.getCellAsString(31).orElse(null);
        if(CommonUtil.isNullOrEmpty(emergencyContactNo)) {
        	sb.append("emergency_contact_no, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_no");
        }else {
        	obj.setEmergencyContactNo(emergencyContactNo);
        }
        
        String emergencyContactEmailAddress = row.getCellAsString(32).orElse(null);
        obj.setEmergencyContactEmailAddress(emergencyContactEmailAddress);
        
        String status = row.getCellAsString(33).orElse(null);
        if(CommonUtil.isNullOrEmpty(status)) {
        	sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        }else {
        	obj.setStatus(status);
        }
        
        String designation = row.getCellAsString(34).orElse(null);
        if(CommonUtil.isNullOrEmpty(designation)) {
        	sb.append("designation, ");
            logger.warn("Mandatory field missing. Field name - designation");
        }else {
        	obj.setDesignation(designation);
        }
        
        String staffType = row.getCellAsString(35).orElse(null);
        if(CommonUtil.isNullOrEmpty(staffType)) {
        	sb.append("staff_type, ");
            logger.warn("Mandatory field missing. Field name - staff_type");
        }else {
        	obj.setStaffType(staffType);
        }
        
        String branchName = row.getCellAsString(36).orElse(null);
        if(CommonUtil.isNullOrEmpty(branchName) ) {
        	sb.append("branch_id, ");
            logger.warn("branch name not provided, Cannot find the branch");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            
            if(b.isPresent()) {
            	obj.setBranch(b.get());
            }else {
            	sb.append("branch_id, ");
                logger.warn("branch name not provided, Cannot find the branch");
            }
        }
        
        if(obj.getBranch() != null) {
        	String departmentName = row.getCellAsString(37).orElse(null);
        	if(CommonUtil.isNullOrEmpty(departmentName)) {
        		sb.append("department_id, ");
                logger.warn("Mandatory field missing. Field name - department_id");
        	}else {
            	Department department = new Department();
                department.setName(departmentName);
                department.setBranch(obj.getBranch());
                Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
                if(dp.isPresent()) {
                    obj.setDepartment(dp.get());
                }else {
                    sb.append("department_id, ");
                    logger.warn("Department not found. Given department name : " + departmentName);
                }
            }
        }
        
        
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        if (this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
        	String msg = "Duplicate Teacher found";
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
			CmsTeacherService cmsTeacherService = PreferencesApp.getBean(CmsTeacherService.class);
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
									Teacher teacher = CommonUtil.createCopyProperties(obj, Teacher.class);
									TeacherInput inp = CommonUtil.createCopyProperties(obj, TeacherInput.class);
									inp.setBranchId(teacher.getBranch().getId());
									inp.setDepartmentId(teacher.getDepartment().getId());
									cmsTeacherService.saveTeacher(inp);
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
