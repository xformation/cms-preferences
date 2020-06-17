package com.synectiks.pref.dataimport.loader;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.enumeration.BatchEnum;
import com.synectiks.pref.domain.enumeration.Gender;
import com.synectiks.pref.domain.enumeration.StudentTypeEnum;
import com.synectiks.pref.domain.vo.CmsFacility;
import com.synectiks.pref.domain.vo.CmsFeeCategory;
import com.synectiks.pref.domain.vo.CmsFeeDetails;
import com.synectiks.pref.domain.vo.CmsTransportVo;
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

public class FeeDetailsDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public FeeDetailsDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        CmsFeeDetails obj = CommonUtil.createCopyProperties(cls.newInstance(), CmsFeeDetails.class);

        String feeParticularName = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(feeParticularName)) {
            sb.append("fee_particular_name, ");
            logger.warn("Mandatory field missing. Field name - fee_particular_name");
        } else {
            obj.setFeeParticularsName(feeParticularName);
        }

        String feeParticularDesc = row.getCellAsString(1).orElse(null);
        if (CommonUtil.isNullOrEmpty(feeParticularDesc)) {
            sb.append("fee_particular_desc, ");
            logger.warn("Mandatory field missing. Field name - fee_particular_desc");
        } else {
            obj.setFeeParticularDesc(feeParticularDesc);
        }

        String studentType = row.getCellAsString(2).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentType)) {
            sb.append("student_type, ");
            logger.warn("Mandatory field missing. Field name - student_type");
        } else {
            obj.setStudentType(StudentTypeEnum.valueOf(studentType));
        }

        String gender = row.getCellAsString(3).orElse(null);
        if (CommonUtil.isNullOrEmpty(gender)) {
            sb.append("gender, ");
            logger.warn("Mandatory field missing. Field name - gender");
        } else {
            obj.setGender(Gender.valueOf(gender));
        }

        String status = row.getCellAsString(4).orElse(null);
        if (CommonUtil.isNullOrEmpty(status)) {
            sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        } else {
            obj.setStatus(status);
        }

        String createdBy = row.getCellAsString(5).orElse(null);
        obj.setCreatedBy(createdBy);

        String createdOn = row.getCellAsString(6).orElse(null);
        if (CommonUtil.isNullOrEmpty(createdOn)) {
            sb.append("created_on, ");
            logger.warn("Mandatory field missing. Field name - created_on");
        } else {
            obj.setCreatedOn(DateFormatUtil.convertStringToLocalDate(createdOn, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String updatedBy = row.getCellAsString(7).orElse(null);
        obj.setUpdatedBy(updatedBy);

        String updatedOn = row.getCellAsString(8).orElse(null);
        if (CommonUtil.isNullOrEmpty(updatedOn)) {
            sb.append("updated_on, ");
            logger.warn("Mandatory field missing. Field name - updated_on");
        } else {
            obj.setUpdatedOn(DateFormatUtil.convertStringToLocalDate(updatedOn, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        String startDate = row.getCellAsString(9).orElse(null);
        if (CommonUtil.isNullOrEmpty(startDate)) {
            sb.append("start_date, ");
            logger.warn("Mandatory field missing. Field name - start_date");
        } else {
            obj.setStartDate(DateFormatUtil.convertStringToLocalDate(startDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        String endDate = row.getCellAsString(10).orElse(null);
        if (CommonUtil.isNullOrEmpty(endDate)) {
            sb.append("end_date, ");
            logger.warn("Mandatory field missing. Field name - end_date");
        } else {
            obj.setEndDate(DateFormatUtil.convertStringToLocalDate(endDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String batchName = row.getCellAsString(11).orElse(null);
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

        String departmentName = row.getCellAsString(12).orElse(null);
        if(CommonUtil.isNullOrEmpty(departmentName)) {
            sb.append("department_id, ");
            logger.warn("Mandatory field missing. Field name - department_id");
        }else {
            Department department = new Department();
            department.setName(departmentName);
            Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
            if(dp.isPresent()) {
                obj.setDepartment(dp.get());
            }else {
                sb.append("department_id, ");
                logger.warn("Department not found. Given department name : " + departmentName);
            }
        }

        String feeCateogoryName = row.getCellAsString(13).orElse(null);
        if(CommonUtil.isNullOrEmpty(feeCateogoryName)) {
            sb.append("fee_cateogory_name, ");
            logger.warn("Mandatory field missing. Field name - fee_cateogory_name");
        }else {
            CmsFeeCategory feeCategory1 = new CmsFeeCategory();
            feeCategory1.setCategoryName(feeCateogoryName);
            Optional<CmsFeeCategory> fc = this.allRepositories.findRepository("fee_category").findOne(Example.of(feeCategory1));
            if(fc.isPresent()) {
                obj.setCmsFeeCategory(fc.get());
            }else {
                sb.append("fee_category_id, ");
                logger.warn("FeeCategoryName not found. Given feeCategoryName name : " + feeCateogoryName);
            }
        }

        String facilityName = row.getCellAsString(14).orElse(null);
        if(CommonUtil.isNullOrEmpty(facilityName)) {
            sb.append("facility_name, ");
            logger.warn("Mandatory field missing. Field name - facility_name");
        }else {
            CmsFacility facility1 = new CmsFacility();
            facility1.setName(facilityName);
            Optional<CmsFacility> cf = this.allRepositories.findRepository("facility").findOne(Example.of(facility1));
            if(cf.isPresent()) {
                obj.setCmsFacility(cf.get());
            }else {
                sb.append("facility_id, ");
                logger.warn("Facility not found. Given facility name : " + facilityName);
            }
        }

        String transportRouteName = row.getCellAsString(15).orElse(null);
        if (CommonUtil.isNullOrEmpty(transportRouteName)) {
            sb.append("transport_route_id, ");
            logger.warn("transportRoute name not provided, Cannot find the transportRouteName");
        } else {
            CmsTransportVo transport1 = new CmsTransportVo();
            transport1.setRouteName(transportRouteName);
            Optional<CmsTransportVo> b = this.allRepositories.findRepository("transport_route").findOne(Example.of(transportRouteName));

            if (b.isPresent()) {
                obj.setCmsTransportVo(b.get());
            } else {
                sb.append("transport_route_id, ");
                logger.warn("transportRoute name not provided, Cannot find the transportRouteName");
            }
        }



        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }



        return (T) obj;
    }


    private BatchEnum findBatch(String batchName) {
        if (BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
            return BatchEnum.FIRSTYEAR;
        } else if (BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
            return BatchEnum.SECONDYEAR;
        } else if (BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
            return BatchEnum.THIRDYEAR;
        } else if (BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
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
                        List expList = postFeeDetailsData(list, applicationProperties, restTemplate);
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
                List expList = postFeeDetailsData(list, applicationProperties, restTemplate);
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

    private List<CmsFacility> postFeeDetailsData(List list, ApplicationProperties applicationProperties, RestTemplate restTemplate){
        logger.debug("Posting FeeDetails data to FeeDetailsRestController of cms-fee");
        String url = applicationProperties.getFeeSrvUrl()+"/api/cmsfeedetails-bulk-load";
        List<CmsFacility> ls = null;
        try {
            ls = restTemplate.postForObject(url, list, List.class);
            if(ls != null && ls.size() >0 ) {
                logger.debug("List of student records could not be saved : ",ls);
            }
        }catch(Exception e) {
            logger.error("FeeDetails records could not be saved. Exception : ", e);
        }
        return ls;
    }
}
