package com.synectiks.pref.dataimport.loader;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.vo.CmsFacility;
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

public class FacilityDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public FacilityDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        CmsFacility obj = CommonUtil.createCopyProperties(cls.newInstance(), CmsFacility.class);

        String name = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(name)) {
            sb.append("name, ");
            logger.warn("Mandatory field missing. Field name - name");
        } else {
            obj.setName(name);
        }

        String status = row.getCellAsString(1).orElse(null);
        if (CommonUtil.isNullOrEmpty(status)) {
            sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        } else {
            obj.setStatus(status);
        }

        String startDate = row.getCellAsString(2).orElse(null);
        if (CommonUtil.isNullOrEmpty(startDate)) {
            sb.append("start_date, ");
            logger.warn("Mandatory field missing. Field name - start_date");
        } else {
            obj.setStartDate(DateFormatUtil.convertStringToLocalDate(startDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String endDate = row.getCellAsString(3).orElse(null);
        if (CommonUtil.isNullOrEmpty(endDate)) {
            sb.append("end_date, ");
            logger.warn("Mandatory field missing. Field name - end_date");
        } else {
            obj.setEndDate(DateFormatUtil.convertStringToLocalDate(endDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String suspandStartDate = row.getCellAsString(4).orElse(null);
        if (CommonUtil.isNullOrEmpty(suspandStartDate)) {
            sb.append("suspand_start_date, ");
            logger.warn("Mandatory field missing. Field name - suspand_start_date");
        } else {
            obj.setSuspandStartDate(DateFormatUtil.convertStringToLocalDate(suspandStartDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String suspandEndDate = row.getCellAsString(5).orElse(null);
        if (CommonUtil.isNullOrEmpty(suspandEndDate)) {
            sb.append("suspand_end_date, ");
            logger.warn("Mandatory field missing. Field name - suspand_end_date");
        } else {
            obj.setSuspandEndDate(DateFormatUtil.convertStringToLocalDate(suspandEndDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String academicYear = row.getCellAsString(6).orElse(null);
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

        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }

//        String amount = row.getCellAsString(8).orElse(null);
//        obj.setAmount(Long.valueOf(amount));

        return (T) obj;
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
                        List expList = postFacilityData(list, applicationProperties, restTemplate);
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
                List expList = postFacilityData(list, applicationProperties, restTemplate);
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

    private List<CmsFacility> postFacilityData(List list, ApplicationProperties applicationProperties, RestTemplate restTemplate){
        logger.debug("Posting facility data to FacilityController of cms-backend");
        String url = applicationProperties.getCmsBackEndUrl()+"/api/cmsfacilities-bulk-load";
        List<CmsFacility> ls = null;
        try {
            ls = restTemplate.postForObject(url, list, List.class);
            if(ls != null && ls.size() >0 ) {
                logger.debug("List of facility records could not be saved : ",ls);
            }
        }catch(Exception e) {
            logger.error("Facility records could not be saved. Exception : ", e);
        }
        return ls;
    }
}
