package com.synectiks.pref.dataimport.loader;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.vo.CmsAdmissionApplicationVo;
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

public class AdmissionApplicationDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public AdmissionApplicationDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        CmsAdmissionApplicationVo obj = CommonUtil.createCopyProperties(cls.newInstance(), CmsAdmissionApplicationVo.class);


        String sourceOfApplication = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(sourceOfApplication)) {
            sb.append("source_of_application, ");
            logger.warn("Mandatory field missing. Field name - source_of_appliction");
        } else {
            obj.setSourceOfApplication(sourceOfApplication);
        }

        String applicationDate = row.getCellAsString(1).orElse(null);
        if(CommonUtil.isNullOrEmpty(applicationDate)) {
            sb.append("application_date, ");
            logger.warn("Mandatory field missing. Field name - application_date");
        }else {
            obj.setApplicationDate(DateFormatUtil.convertStringToLocalDate(applicationDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String completionDate = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(completionDate)) {
            sb.append("completion_date, ");
            logger.warn("Mandatory field missing. Field name - completion_date");
        }else {
            obj.setCompletionDate(DateFormatUtil.convertStringToLocalDate(completionDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String admissionNo = row.getCellAsString(3).orElse(null);
        if (CommonUtil.isNullOrEmpty(admissionNo)) {
            sb.append("admission_no, ");
            logger.warn("Mandatory field missing. Field name - admission_no");
        } else {
            obj.setAdmissionNo(Long.parseLong(admissionNo));
        }

        String admissionDate = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(admissionDate)) {
            sb.append("admission_date, ");
            logger.warn("Mandatory field missing. Field name - admission_date");
        }else {
            obj.setApplicationDate(DateFormatUtil.convertStringToLocalDate(admissionDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String comments = row.getCellAsString(5).orElse(null);
        obj.setComments(comments);

        String applicationStatus = row.getCellAsString(6).orElse(null);
        if(CommonUtil.isNullOrEmpty(applicationStatus)) {
            sb.append("application_status, ");
            logger.warn("Mandatory field missing. Field name - application_status");
        }else {
            obj.setApplicationStatus(applicationStatus);

        }

        String branchName = row.getCellAsString(7).orElse(null);
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

        String academicYear = row.getCellAsString(8).orElse(null);
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

        String createdBy = row.getCellAsString(9).orElse(null);
        obj.setCreatedBy(createdBy);

        String createdOn = row.getCellAsString(10).orElse(null);
        if (CommonUtil.isNullOrEmpty(createdOn)) {
            sb.append("created_on, ");
            logger.warn("Mandatory field missing. Field name - created_on");
        } else {
            obj.setCreatedOn(DateFormatUtil.convertStringToLocalDate(createdOn, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String updatedBy = row.getCellAsString(11).orElse(null);
        obj.setUpdatedBy(updatedBy);

        String updatedOn = row.getCellAsString(12).orElse(null);
        if (CommonUtil.isNullOrEmpty(updatedOn)) {
            sb.append("updated_on, ");
            logger.warn("Mandatory field missing. Field name - updated_on");
        } else {
            obj.setUpdatedOn(DateFormatUtil.convertStringToLocalDate(updatedOn, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String admissionEnquiry = row.getCellAsString(13).orElse(null);
        if(CommonUtil.isNullOrEmpty(admissionEnquiry)) {
            sb.append("admission_enquiry_id, ");
            logger.warn("Mandatory field missing. Field name - admission_enquiry_id");
        }else {
            CmsAdmissionEnquiryVo admissionEnquiryVo1 = new CmsAdmissionEnquiryVo();
            admissionEnquiryVo1.setStudentName(admissionEnquiry);
            Optional<CmsAdmissionEnquiryVo> ae = this.allRepositories.findRepository("admissionEnquiry").findOne(Example.of(admissionEnquiryVo1));
            if(ae.isPresent()) {
                obj.setAdmissionEnquiryVo(ae.get());
            }else {
                sb.append("admission_enquiry_id, ");
                logger.warn("AdmissionEnquiry not found. Given admissionEnquiry name : " + admissionEnquiry);
            }
        }


        return (T)obj;

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
                        List expList = postAdmissionApplicationData(list, applicationProperties, restTemplate);
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
                List expList = postAdmissionApplicationData(list, applicationProperties, restTemplate);
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

    private List<CmsAdmissionApplicationVo> postAdmissionApplicationData(List list, ApplicationProperties applicationProperties, RestTemplate restTemplate){
        logger.debug("Posting admissionApplication data to AdmissionApplicationRestController of cms-admission");
        String url = applicationProperties.getCmsAdmissionEndUrl()+"/api/cmsadmissionapplication-bulk-load";
        List<CmsAdmissionApplicationVo> ls = null;
        try {
            ls = restTemplate.postForObject(url, list, List.class);
            if(ls != null && ls.size() >0 ) {
                logger.debug("List of admissionApplication records could not be saved : ",ls);
            }
        }catch(Exception e) {
            logger.error("admissionApplication records could not be saved. Exception : ", e);
        }
        return ls;
    }


}
