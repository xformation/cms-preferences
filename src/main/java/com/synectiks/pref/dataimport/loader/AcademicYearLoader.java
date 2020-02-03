package com.synectiks.pref.dataimport.loader;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;


public class AcademicYearLoader extends DataLoader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean isAcademicYearExist = false;
    private AllRepositories allRepositories;
    private String sheetName;

    public AcademicYearLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, Exception, MandatoryFieldMissingException {
        // find out the active academic year. if it exists then move this current record to exception

        StringBuilder sb = new StringBuilder();
        AcademicYear obj = CommonUtil.createCopyProperties(cls.newInstance(), AcademicYear.class);
        obj.setStatus(CmsConstants.STATUS_ACTIVE);

        if(!this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))){

            String description = row.getCellAsString(0).orElse(null);
            if(CommonUtil.isNullOrEmpty(description)){
                sb.append("description, ");
                logger.warn ("Mandatory field missing. Field name - jhi_year");
            }else{
                obj.setDescription(description);
            }
            String startDate = row.getCellAsString(1).orElse(null);
            if(CommonUtil.isNullOrEmpty(startDate)){
                sb.append("start_date, ");
                logger.warn ("Mandatory field missing. Field name - start_date");
            }else{
                obj.startDate(DateFormatUtil.convertStringToLocalDate(startDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            String endDate = row.getCellAsString(2).orElse(null);
            if(CommonUtil.isNullOrEmpty(endDate)){
                sb.append("end_date, ");
                logger.warn ("Mandatory field missing. Field name - end_date");
            }else{
                obj.endDate(DateFormatUtil.convertStringToLocalDate(endDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            String status = row.getCellAsString(3).orElse(null);
            if(CommonUtil.isNullOrEmpty(status)){
                sb.append("status, ");
                logger.warn ("Mandatory field missing. Field name - status");
            }else{
                if(CmsConstants.STATUS_ACTIVE.toString().equalsIgnoreCase(status)){
                    obj.setStatus(CmsConstants.STATUS_ACTIVE);
                }else {
                    obj.setStatus(CmsConstants.STATUS_DEACTIVE);
                }
            }
        }else{
            sb.append("Application already have an active academic year, ");
            logger.warn ("Application already have an active academic year");
            if(sb.length() > 0) {
                String msg = "Application already have an active academic year";
                throw new Exception(msg);
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }

        return (T)obj;
    }
}
