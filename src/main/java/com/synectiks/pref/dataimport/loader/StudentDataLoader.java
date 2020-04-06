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
import org.springframework.web.client.RestTemplate;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.Section;
import com.synectiks.pref.domain.enumeration.BatchEnum;
import com.synectiks.pref.domain.enumeration.Caste;
import com.synectiks.pref.domain.enumeration.Gender;
import com.synectiks.pref.domain.enumeration.RelationWithStudentEnum;
import com.synectiks.pref.domain.enumeration.Religion;
import com.synectiks.pref.domain.enumeration.SectionEnum;
import com.synectiks.pref.domain.enumeration.StudentTypeEnum;
import com.synectiks.pref.domain.vo.CmsPermissionVo;
import com.synectiks.pref.domain.vo.CmsStudentVo;
import com.synectiks.pref.exceptions.DuplicateRecordFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

public class StudentDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public StudentDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException , MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        CmsStudentVo obj = CommonUtil.createCopyProperties(cls.newInstance(), CmsStudentVo.class);

        String studentName = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentName)) {
        	sb.append("student_name, ");
            logger.warn("Mandatory field missing. Field name - student_name");
        }else {
        	obj.setStudentName(studentName);
        }

        String studentMiddleName = row.getCellAsString(1).orElse(null);
        obj.setStudentMiddleName(studentMiddleName);

        String studentLastName = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentLastName)) {
        	sb.append("student_last_name, ");
            logger.warn("Mandatory field missing. Field name - student_last_name");
        }else {
        	obj.setStudentLastName(studentLastName);
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

        String motherName = row.getCellAsString(6).orElse(null);
        obj.setMotherName(motherName);

        String motherMiddleName = row.getCellAsString(7).orElse(null);
        obj.setMotherMiddleName(motherMiddleName);

        String motherLastName = row.getCellAsString(8).orElse(null);
        obj.setMotherLastName(motherLastName);

        String studentAadharNo = row.getCellAsString(9).orElse(null);
        obj.setStudentAadharNo(studentAadharNo);

        String studentPanNo = row.getCellAsString(10).orElse(null);
        obj.setStudentPanNo(studentPanNo);

        String studentSocialSecurityNo = row.getCellAsString(11).orElse(null);
        obj.setStudentSocialSecurityNo(studentSocialSecurityNo);

        String studentTaxReferenceNo = row.getCellAsString(12).orElse(null);
        obj.setStudentTaxReferenceNo(studentTaxReferenceNo);

        String studentBplNo = row.getCellAsString(13).orElse(null);
        obj.setStudentBplNo(studentBplNo);

        String studentDrivingLicenseNo = row.getCellAsString(14).orElse(null);
        obj.setStudentDrivingLicenseNo(studentDrivingLicenseNo);

        String studentPassportNo = row.getCellAsString(15).orElse(null);
        obj.setStudentPassportNo(studentPassportNo);

        String dob = row.getCellAsString(16).orElse(null);
        if(CommonUtil.isNullOrEmpty(dob)) {
        	sb.append("date_of_birth, ");
            logger.warn("Mandatory field missing. Field name - date_of_birth");
        }else {
        	obj.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(dob, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String placeOfBirth = row.getCellAsString(17).orElse(null);
        obj.setPlaceOfBirth(placeOfBirth);

        String religion = row.getCellAsString(18).orElse(null);
        obj.setReligion(Religion.valueOf(religion));

        String cast = row.getCellAsString(19).orElse(null);
        obj.setCaste(Caste.valueOf(cast));

        String subCast = row.getCellAsString(20).orElse(null);
        obj.setSubCaste(subCast);

        String gender = row.getCellAsString(21).orElse(null);
        if(CommonUtil.isNullOrEmpty(gender)) {
        	sb.append("gender, ");
            logger.warn("Mandatory field missing. Field name - gender");
        }else {
        	obj.setSex(Gender.valueOf(gender));
        }

        String studentLocalAddress = row.getCellAsString(22).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentLocalAddress)) {
        	sb.append("student_local_address, ");
            logger.warn("Mandatory field missing. Field name - student_local_address");
        }else {
        	obj.setStudentLocalAddress(studentLocalAddress);
        }

        String studentPermanentAddress = row.getCellAsString(23).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentPermanentAddress)) {
        	sb.append("student_permanent_address, ");
            logger.warn("Mandatory field missing. Field name - student_permanent_address");
        }else {
        	obj.setStudentPermanentAddress(studentPermanentAddress);
        }

        String cityName = row.getCellAsString(24).orElse(null);
		obj.setCity(cityName);

        String stateName = row.getCellAsString(25).orElse(null);
		obj.setState(stateName);

        String countryName = row.getCellAsString(26).orElse(null);
		obj.setCountry(countryName);

		String pinCode = row.getCellAsString(27).orElse(null);
        obj.setPinCode(pinCode);

        String studentPrimaryCellNumber = row.getCellAsString(28).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentPrimaryCellNumber)) {
        	sb.append("student_primary_cell_number, ");
            logger.warn("Mandatory field missing. Field name - student_primary_cell_number");
        }else {
        	obj.setStudentPrimaryCellNumber(studentPrimaryCellNumber);
        }

        String studentAlternateCellNumber = row.getCellAsString(29).orElse(null);
        obj.setStudentAlternateCellNumber(studentAlternateCellNumber);

        String studentLandLinePhoneNumber = row.getCellAsString(30).orElse(null);
        obj.setStudentLandLinePhoneNumber(studentLandLinePhoneNumber);

        String studentPrimaryEmailId = row.getCellAsString(31).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentPrimaryEmailId)) {
        	sb.append("student_primary_email_id, ");
            logger.warn("Mandatory field missing. Field name - student_primary_email_id");
        }else {
        	obj.setStudentPrimaryEmailId(studentPrimaryEmailId);
        }

        String studentAlternateEmailId = row.getCellAsString(32).orElse(null);
        obj.setStudentAlternateEmailId(studentAlternateEmailId);

        String relationWithStudent = row.getCellAsString(33).orElse(null);
        if(CommonUtil.isNullOrEmpty(relationWithStudent)) {
        	sb.append("relation_with_student, ");
            logger.warn("Mandatory field missing. Field name - relation_with_student");
        }else {
        	obj.setRelationWithStudent(RelationWithStudentEnum.valueOf(relationWithStudent));
        }

        String emergencyContactName = row.getCellAsString(34).orElse(null);
        if(CommonUtil.isNullOrEmpty(emergencyContactName)) {
        	sb.append("emergency_contact_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_name");
        }else {
        	obj.setEmergencyContactName(emergencyContactName);
        }

        String emergencyContactMiddleName = row.getCellAsString(35).orElse(null);
        obj.setEmergencyContactMiddleName(emergencyContactMiddleName);

        String emergencyContactLastName = row.getCellAsString(36).orElse(null);
        if(CommonUtil.isNullOrEmpty(emergencyContactLastName)) {
        	sb.append("emergency_contact_last_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_last_name");
        }else {
        	obj.setEmergencyContactLastName(emergencyContactLastName);
        }

        String emergencyContactCellNumber = row.getCellAsString(37).orElse(null);
        if(CommonUtil.isNullOrEmpty(emergencyContactCellNumber)) {
        	sb.append("emergency_contact_no, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_no");
        }else {
        	obj.setEmergencyContactCellNumber(emergencyContactCellNumber);
        }

        String emergencyContactLandLinePhoneNumber = row.getCellAsString(38).orElse(null);
        obj.setEmergencyContactLandLinePhoneNumber(emergencyContactLandLinePhoneNumber);


        String emergencyContactEmailId = row.getCellAsString(39).orElse(null);
        obj.setEmergencyContactEmailId(emergencyContactEmailId);

        String admissionNo = row.getCellAsString(40).orElse(null);
        obj.setAdmissionNo(admissionNo);

        String enrollmentNo = row.getCellAsString(41).orElse(null);
        obj.setEnrollmentNo(enrollmentNo);

        String rollNo = row.getCellAsString(42).orElse(null);
        obj.setRollNo(rollNo);

        String studentType = row.getCellAsString(43).orElse(null);
        if(CommonUtil.isNullOrEmpty(studentType)) {
        	sb.append("student_type, ");
            logger.warn("Mandatory field missing. Field name - student_type");
        }else {
        	obj.setStudentType(StudentTypeEnum.valueOf(studentType));
        }

        String fatherEmailId = row.getCellAsString(45).orElse(null);
        if(CommonUtil.isNullOrEmpty(fatherEmailId)) {
            sb.append("father_email_id, ");
            logger.warn("Mandatory field missing. Field name - father_email_id");
        }else {
            obj.setFatherEmailId(fatherEmailId);
        }

        ///---------------------------------------------------------
        String status = row.getCellAsString(109).orElse(null);
        if(CommonUtil.isNullOrEmpty(status)) {
        	sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        }else {
        	obj.setStatus(status);
        }

        String academicYear = row.getCellAsString(111).orElse(null);
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            sb.append("academic_year_id, ");
            logger.warn("Mandatory field missing. Field name - academic_year_id");
        }else {
            AcademicYear academicYear1 = new AcademicYear();
            academicYear1.setDescription(academicYear);
            Optional<AcademicYear> ay = this.allRepositories.findRepository("academic_year").findOne(Example.of(academicYear1));
            if(ay.isPresent()) {
                obj.setAcademicYear(ay.get());
                obj.setAcademicYearId(ay.get().getId());
            }else {
                sb.append("academic_year_id, ");
                logger.warn("AcademicYear not found. Given academicYear name : "+academicYear);
            }
        }

        String branchName = row.getCellAsString(112).orElse(null);
        if(CommonUtil.isNullOrEmpty(branchName) ) {
        	sb.append("branch_id, ");
            logger.warn("branch name not provided, Cannot find the branch");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));

            if(b.isPresent()) {
            	obj.setBranch(b.get());
            	obj.setBranchId(b.get().getId());
            }else {
            	sb.append("branch_id, ");
                logger.warn("branch name not provided, Cannot find the branch");
            }
        }

        if(obj.getBranch() != null) {
        	String departmentName = row.getCellAsString(113).orElse(null);
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
                    obj.setDepartmentId(dp.get().getId());
                }else {
                    sb.append("department_id, ");
                    logger.warn("Department not found. Given department name : " + departmentName);
                }
            }
        }

        if(obj.getDepartment() != null) {
        	String batchName = row.getCellAsString(114).orElse(null);
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
                    obj.setBatchId(bt.get().getId());
                }else {
                    sb.append("batch_id, ");
                    logger.warn("Batch not found. Given batch : " + batchName);
                }
            }
        }

        if(obj.getBatch() != null) {
        	String sectionName = row.getCellAsString(115).orElse(null);
        	if(CommonUtil.isNullOrEmpty(sectionName)) {
        		sb.append("section_id, ");
                logger.warn("Mandatory field missing. Field name - section_id");
        	}else {
        		Section section = new Section();
        		section.setSection(SectionEnum.valueOf(sectionName));
        		section.setBatch(obj.getBatch());
                Optional<Section> sc = this.allRepositories.findRepository("section").findOne(Example.of(section));
                if(sc.isPresent()) {
                    obj.setSection(sc.get());
                    obj.setSectionId(sc.get().getId());
                }else {
                    sb.append("section_id, ");
                    logger.warn("Section not found. Given section : " + sectionName);
                }
            }
        }


        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }

//        if (this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
//        	String msg = "Duplicate Student found";
//        	sb.append(msg+",");
//            logger.warn(msg);
//            if (sb.length() > 0) {
//                throw new DuplicateRecordFoundException(msg);
//            }
//        }

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
//			T instance = cls.newInstance();
			ApplicationProperties applicationProperties = PreferencesApp.getBean(ApplicationProperties.class);
			RestTemplate restTemplate = PreferencesApp.getBean(RestTemplate.class);

			try (Stream<Row> rows = sheet.openStream()) {
				List<T> list = new ArrayList<>();
				List<ExceptionRecord> exceptionList = new ArrayList<>();
				StringBuilder sb = new StringBuilder(String.format("\nInvalid records found for table - %s: \n", this.sheetName));
				sb.append("Rows having invalid records\n");
				rows.forEach(row -> {

					if (list.size() == CmsConstants.BATCH_SIZE) {
						List expList = postStudentData(list, applicationProperties, restTemplate);
						exceptionList.addAll(expList);
						list.clear();
					}
					if (exceptionList.size() == CmsConstants.BATCH_SIZE) {
						allRepositories.findRepository("exception_record").saveAll(exceptionList);
						exceptionList.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						try {
							T obj = getObject(row, cls);
							if(obj != null) {
								list.add(obj);
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
				List expList = postStudentData(list, applicationProperties, restTemplate);
				list.clear();
				exceptionList.addAll(expList);
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

    private List<CmsStudentVo> postStudentData(List list, ApplicationProperties applicationProperties, RestTemplate restTemplate){
    	logger.debug("Posting student data to StudentRestController of cms-backend");
    	String url = applicationProperties.getCmsBackEndUrl()+"/api/cmsstudents-bulk-load";
    	List<CmsStudentVo> ls = null;
    	try {
            ls = restTemplate.postForObject(url, list, List.class);
            if(ls != null && ls.size() >0 ) {
            	logger.debug("List of student records could not be saved : ",ls);
            }
        }catch(Exception e) {
            logger.error("Student records could not be saved. Exception : ", e);
        }
    	return ls;
    }
}
