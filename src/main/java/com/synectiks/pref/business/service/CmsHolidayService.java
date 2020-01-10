package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsHolidayVo;
import com.synectiks.pref.graphql.types.holiday.HolidayInput;
import com.synectiks.pref.repository.AcademicYearRepository;
import com.synectiks.pref.repository.HolidayRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsHolidayService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    HolidayRepository holidayRepository;
    
    @Autowired
    AcademicYearRepository academicYearRepository;

    public List<CmsHolidayVo> getHolidayList(String status) {
    	Holiday holiday = new Holiday();
        holiday.setStatus(status);
        List<Holiday> list = this.holidayRepository.findAll(Example.of(holiday));
        List<CmsHolidayVo> ls = changeHolidayToCmsHolidayList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<CmsHolidayVo> getHolidayList(){
    	List<Holiday> list = this.holidayRepository.findAll();
    	List<CmsHolidayVo> ls = changeHolidayToCmsHolidayList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    private List<CmsHolidayVo> changeHolidayToCmsHolidayList(List<Holiday> list){
    	List<CmsHolidayVo> ls = new ArrayList<>();
    	for(Holiday hl: list) {
    		CmsHolidayVo vo = CommonUtil.createCopyProperties(hl, CmsHolidayVo.class);
    		if(hl.getHolidayDate() != null) {
            	vo.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(hl.getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(hl.getCreatedOn() != null) {
            	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(hl.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(hl.getUpdatedOn() != null) {
            	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(hl.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(hl.getAcademicYear() != null) {
            	vo.setAcademicYearId(hl.getAcademicYear().getId());
            	CmsAcademicYearVo cmsAvo =CommonUtil.createCopyProperties(hl.getAcademicYear(), CmsAcademicYearVo.class); 
        		vo.setCmsAcademicYearVo(cmsAvo);
            }
    		ls.add(vo);
    	}
    	return ls;
    }
    
    public CmsHolidayVo saveHoliday(HolidayInput input) {
    	logger.info("Saving holiday");
    	CmsHolidayVo vo = null;
    	try {
    		Holiday holiday = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new holiday");
    			holiday = CommonUtil.createCopyProperties(input, Holiday.class);
    			holiday.setCreatedOn(LocalDate.now());
    			holiday.setComments(input.getComments());
    		}else {
    			logger.debug("Updating existing holiday");
    			holiday = this.holidayRepository.findById(input.getId()).get();
    			holiday.setUpdatedOn(LocalDate.now());
    			if(input.getComments() != null) {
    				holiday.setComments(input.getComments());
    			}
    		}
    		AcademicYear ay = this.academicYearRepository.findById(input.getAcademicYearId()).get();
			holiday.setAcademicYear(ay);
    		holiday.setDescription(input.getDescription());
    		holiday.setStatus(input.getStatus());
    		holiday.setHolidayDate(input.getStrHolidayDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
			
    		holiday = holidayRepository.save(holiday);
        	
        	vo = CommonUtil.createCopyProperties(holiday, CmsHolidayVo.class);
        	vo.setStrCreatedOn(holiday.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(holiday.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(holiday.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(holiday.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrHolidayDate(holiday.getHolidayDate() != null ? DateFormatUtil.changeLocalDateFormat(holiday.getHolidayDate(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("Holiday is added successfully");
        		logger.debug("Holiday is added successfully");
        	}else {
        		vo.setExitDescription("Holiday is updated successfully");
        		logger.debug("Holiday is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsHolidayVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, holiday data not be saved");
    		logger.error("Holiday save failed. Exception : ",e);
    	}
    	logger.info("Holiday saved successfully");
    	List ls =  getHolidayList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
