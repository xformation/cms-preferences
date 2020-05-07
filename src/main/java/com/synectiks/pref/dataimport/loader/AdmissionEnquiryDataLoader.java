package com.synectiks.pref.dataimport.loader;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.*;
import com.synectiks.pref.domain.enumeration.BatchEnum;
import com.synectiks.pref.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.pref.domain.vo.CmsSemesterVo;
import com.synectiks.pref.exceptions.DuplicateRecordFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AdmissionEnquiryDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public AdmissionEnquiryDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        CmsAdmissionEnquiryVo obj = CommonUtil.createCopyProperties(cls.newInstance(), CmsAdmissionEnquiryVo.class);


        String studentName = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentName)) {
            sb.append("student_name, ");
            logger.warn("Mandatory field missing. Field name - student_name");
        } else {
            obj.setStudentName(studentName);
        }

        String studentMiddleName = row.getCellAsString(1).orElse(null);
        obj.setStudentMiddleName(studentMiddleName);

        String studentLastName = row.getCellAsString(2).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentLastName)) {
            sb.append("student_last_name, ");
            logger.warn("Mandatory field missing. Field name - student_last_name");
        } else {
            obj.setStudentLastName(studentLastName);
        }

        String cellPhoneNo = row.getCellAsString(3).orElse(null);
        if (CommonUtil.isNullOrEmpty(cellPhoneNo)) {
            sb.append("cell_phone_no, ");
            logger.warn("Mandatory field missing. Field name - cell_phone_no");
        } else {
            obj.setCellPhoneNo(cellPhoneNo);
        }

        String landLinePhoneNo = row.getCellAsString(4).orElse(null);
        obj.setLandLinePhoneNo(landLinePhoneNo);

        String emailId = row.getCellAsString(5).orElse(null);
        if (CommonUtil.isNullOrEmpty(emailId)) {
            sb.append("email_id, ");
            logger.warn("Mandatory field missing. Field name - email_id");
        } else {
            obj.setEmailId(emailId);
        }

        String dob = row.getCellAsString(6).orElse(null);
        if(CommonUtil.isNullOrEmpty(dob)) {
            sb.append("date_of_birth, ");
            logger.warn("Mandatory field missing. Field name - date_of_birth");
        }else {
            obj.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(dob, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String gender = row.getCellAsString(7).orElse(null);
        if(CommonUtil.isNullOrEmpty(gender)) {
            sb.append("gender, ");
            logger.warn("Mandatory field missing. Field name - gender");
        }else {
            obj.setGender(gender);
        }

        String highestQualification = row.getCellAsString(8).orElse(null);
        if(CommonUtil.isNullOrEmpty(highestQualification)) {
            sb.append("highest_qualification, ");
            logger.warn("Mandatory field missing. Field name - highest_qualification");
        }else {
            obj.setHighestQualification(highestQualification);
        }

        String modeOfEnquiry = row.getCellAsString(9).orElse(null);
        obj.setModeOfEnquiry(modeOfEnquiry);

        String enquiryDate = row.getCellAsString(10).orElse(null);
        if(CommonUtil.isNullOrEmpty(enquiryDate)) {
            sb.append("enquiry_date, ");
            logger.warn("Mandatory field missing. Field name - enquiry_date");
        }else {
            obj.setEnquiryDate(DateFormatUtil.convertStringToLocalDate(enquiryDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String comments = row.getCellAsString(11).orElse(null);
        obj.setComments(comments);


        String branchName = row.getCellAsString(12).orElse(null);
        if(CommonUtil.isNullOrEmpty(branchName)) {
            sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Field name - branch_id");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(b.isPresent()) {
                obj.setBranch(b.get());
                obj.setBranchId(b.get().getId());
            }else {
                sb.append("branch_id, ");
                logger.warn("Branch not found. Given branch name : "+branchName);
            }
        }


            String departmentName = row.getCellAsString(13).orElse(null);
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


        String courseName = row.getCellAsString(14).orElse(null);
        if(CommonUtil.isNullOrEmpty(courseName)) {
            sb.append("course_name, ");
            logger.warn("Mandatory field missing. Field name - fee_course_name");
        }else {
            Course course1 = new Course();
            course1.setName(courseName);
            Optional<Course> c = this.allRepositories.findRepository("course").findOne(Example.of(course1));
            if(c.isPresent()) {
                obj.setCourse(c.get());
            }else {
                sb.append("course_id, ");
                logger.warn("CourseName not found. Given courseName name : " + courseName);
            }
        }

        String semesterName = row.getCellAsString(15).orElse(null);
        if(CommonUtil.isNullOrEmpty(semesterName)) {
            sb.append("semester_name, ");
            logger.warn("Mandatory field missing. Field name - semester_name");
        }else {
            CmsSemesterVo semester1 = new CmsSemesterVo();
            semester1.setDescription(semesterName);
            Optional<CmsSemesterVo> st = this.allRepositories.findRepository("semester").findOne(Example.of(semester1));
            if(st.isPresent()) {
                obj.setSemester(st.get());
            }else {
                sb.append("semester_id, ");
                logger.warn("SemesterName not found. Given semesterName name : " + semesterName);
            }
        }


        String batchName = row.getCellAsString(16).orElse(null);
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



        String stateName = row.getCellAsString(17).orElse(null);
        if(CommonUtil.isNullOrEmpty(stateName)) {
            sb.append("state_id, ");
            logger.warn("Mandatory field missing. Field name - state_id");
        }else {
            State state = new State();
            state.setStateName(stateName);
            Optional<State> s = this.allRepositories.findRepository("state").findOne(Example.of(state));
            if(s.isPresent()) {
                obj.setState(s.get());
                obj.setStateId(s.get().getId());
            }else {
                sb.append("state_id, ");
                logger.warn("State not found. Given state name : "+stateName);
            }
        }

        String cityName = row.getCellAsString(18).orElse(null);
        if(CommonUtil.isNullOrEmpty(cityName)) {
            sb.append("city_id, ");
            logger.warn("Mandatory field missing. Field name - city_id");
        }else {
            City city = new City();
            city.setCityName(cityName);
            Optional<City> c = this.allRepositories.findRepository("city").findOne(Example.of(city));
            if(c.isPresent()) {
                obj.setCity(c.get());
                obj.setCityId(c.get().getId());
            }else {
                sb.append("city_id, ");
                logger.warn("City not found. Given city name : "+cityName);
            }
        }

        String academicYear = row.getCellAsString(19).orElse(null);
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

        String enquiryStatus = row.getCellAsString(20).orElse(null);
        if(CommonUtil.isNullOrEmpty(enquiryStatus)) {
            sb.append("enquiry_status, ");
            logger.warn("Mandatory field missing. Field name - enquiry_status");
        }else {
            obj.setEnquiryStatus(enquiryStatus);
        }

        String createdBy = row.getCellAsString(21).orElse(null);
        obj.setCreatedBy(createdBy);

        String createdOn = row.getCellAsString(22).orElse(null);
        if (CommonUtil.isNullOrEmpty(createdOn)) {
            sb.append("created_on, ");
            logger.warn("Mandatory field missing. Field name - created_on");
        } else {
            obj.setCreatedOn(DateFormatUtil.convertStringToLocalDate(createdOn, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String updatedBy = row.getCellAsString(23).orElse(null);
        obj.setUpdatedBy(updatedBy);

        String updatedOn = row.getCellAsString(24).orElse(null);
        if (CommonUtil.isNullOrEmpty(updatedOn)) {
            sb.append("updated_on, ");
            logger.warn("Mandatory field missing. Field name - updated_on");
        } else {
            obj.setUpdatedOn(DateFormatUtil.convertStringToLocalDate(updatedOn, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
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
                        List expList = postAdmissionEnquiryData(list, applicationProperties, restTemplate);
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
                List expList = postAdmissionEnquiryData(list, applicationProperties, restTemplate);
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

    private List<CmsAdmissionEnquiryVo> postAdmissionEnquiryData(List list, ApplicationProperties applicationProperties, RestTemplate restTemplate){
        logger.debug("Posting admissionEnquiry data to AdmissionEnquiryRestController of cms-admission");
        String url = applicationProperties.getCmsAdmissionEndUrl()+"/api/cmsadmissionenquiry-bulk-load";
        List<CmsAdmissionEnquiryVo> ls = null;
        try {
            ls = restTemplate.postForObject(url, list, List.class);
            if(ls != null && ls.size() >0 ) {
                logger.debug("List of admissionEnquiry records could not be saved : ",ls);
            }
        }catch(Exception e) {
            logger.error("AdmissionEnquiry records could not be saved. Exception : ", e);
        }
        return ls;
    }

}
