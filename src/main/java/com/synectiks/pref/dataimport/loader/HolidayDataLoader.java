package com.synectiks.pref.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.exceptions.AdditionalHolidayFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

public class HolidayDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public HolidayDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException, AdditionalHolidayFoundException {
        StringBuilder sb = new StringBuilder();
        Holiday obj = CommonUtil.createCopyProperties(cls.newInstance(), Holiday.class);

        String academicYear = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            sb.append("jhi_year, ");
            logger.warn("Mandatory field missing. Field name - jhi_year");
        }else {
            AcademicYear ay = new AcademicYear();
            ay.setDescription(academicYear);
            Optional<AcademicYear> oay = this.allRepositories.findRepository("academic_year").findOne(Example.of(ay));
            if(oay.isPresent()) {
                obj.setAcademicYear(oay.get());
            }else {
                sb.append("jhi_year, ");
                logger.warn("AcademicYear not found. Given academicYear name : "+academicYear);
            }
        }
        
        String holidayDesc = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(holidayDesc)) {
            sb.append("holiday_desc, ");
            logger.warn("Mandatory field missing. Field name - holiday_desc");
        } else {
            obj.setDescription(holidayDesc);
        }

        String holidayDate = row.getCellAsString(1).orElse(null);
        if (CommonUtil.isNullOrEmpty(holidayDate)) {
            sb.append("holiday_date, ");
            logger.warn("Mandatory field missing. Field name - holiday_date");
        } else {
            obj.holidayDate(DateFormatUtil.convertStringToLocalDate(holidayDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

//        if(!CommonUtil.isNullOrEmpty(academicYear) && !CommonUtil.isNullOrEmpty(holidayDesc) && !CommonUtil.isNullOrEmpty(holidayDate) 
//        		&& this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
//        	String msg = "Application already have a holiday with name : "+holidayDesc+" for the date : "+holidayDate+" in the given academic year :"+academicYear;
//        	sb.append(msg);
//        	logger.warn(msg);
//        	if (sb.length() > 0) {
//              throw new AdditionalHolidayFoundException(msg);
//        	}
//        }
        
        String holidayStatus = row.getCellAsString(2).orElse(null);
        if (CommonUtil.isNullOrEmpty(holidayStatus)) {
            sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - holiday_status");
        } else {
            if (CmsConstants.STATUS_ACTIVE.toString().equalsIgnoreCase(holidayStatus)) {
                obj.setStatus(CmsConstants.STATUS_ACTIVE);
            } else {
                obj.setStatus(CmsConstants.STATUS_DEACTIVE);
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }

        if(this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
        	String msg = "Application already have a holiday in the given academic year :"+academicYear;
        	sb.append(msg);
        	logger.warn(msg);
        	if (sb.length() > 0) {
              throw new AdditionalHolidayFoundException(msg);
        	}
        }
        
        return (T) obj;
    }
}
