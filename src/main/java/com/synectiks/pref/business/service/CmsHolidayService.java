package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsHolidayVo;
import com.synectiks.pref.graphql.types.holiday.HolidayInput;
import com.synectiks.pref.repository.HolidayRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsHolidayService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    HolidayRepository holidayRepository;
    
//    @Autowired
//    AcademicYearRepository academicYearRepository;
    
    @Autowired
    CmsAcademicYearService cmsAcademicYearService;

    public List<CmsHolidayVo> getCmsHolidayListOnFilterCriteria(Map<String, String> criteriaMap){
    	Holiday obj = new Holiday();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		obj.setDescription(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("holidayDate") != null) {
    		LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("holidayDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
    		obj.setHolidayDate(dt);
    		isFilter = true;
    	}
    	if(criteriaMap.get("comments") != null) {
    		obj.setComments(criteriaMap.get("comments"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		obj.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("academicYearId") != null) {
    		AcademicYear academicYear = cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
    		obj.setAcademicYear(academicYear);
    		isFilter = true;
    	}
    	List<Holiday> list = null;
    	if(isFilter) {
    		list = this.holidayRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.holidayRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsHolidayVo> ls = changeHolidayToCmsHolidayList(list);
    	Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return ls;
    }
    
    public List<Holiday> getHolidayListOnFilterCriteria(Map<String, String> criteriaMap){
    	Holiday obj = new Holiday();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		obj.setDescription(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("holidayDate") != null) {
    		LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("holidayDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
    		obj.setHolidayDate(dt);
    		isFilter = true;
    	}
    	if(criteriaMap.get("comments") != null) {
    		obj.setComments(criteriaMap.get("comments"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		obj.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("academicYearId") != null) {
    		AcademicYear academicYear = cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
    		obj.setAcademicYear(academicYear);
    		isFilter = true;
    	}
    	List<Holiday> list = null;
    	if(isFilter) {
    		list = this.holidayRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.holidayRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return list;
    }
    
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
    
    public CmsHolidayVo getCmsHoliday(Long id){
    	Optional<Holiday> th = this.holidayRepository.findById(id);
    	if(th.isPresent()) {
    		CmsHolidayVo vo = CommonUtil.createCopyProperties(th.get(), CmsHolidayVo.class);
    		convertDatesAndProvideDependencies(th.get(), vo);
    		logger.debug("CmsHoliday for given id : "+id+". CmsHoliday object : "+vo);
        	return vo;
    	}
    	logger.debug("Holiday object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Holiday getHoliday(Long id){
    	Optional<Holiday> th = this.holidayRepository.findById(id);
    	if(th.isPresent()) {
    		return th.get();
    	}
    	logger.debug("Holiday object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    private List<CmsHolidayVo> changeHolidayToCmsHolidayList(List<Holiday> list){
    	List<CmsHolidayVo> ls = new ArrayList<>();
    	for(Holiday hl: list) {
    		CmsHolidayVo vo = CommonUtil.createCopyProperties(hl, CmsHolidayVo.class);
    		convertDatesAndProvideDependencies(hl, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Holiday hl, CmsHolidayVo vo) {
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
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(input.getAcademicYearId()); // this.academicYearRepository.findById(input.getAcademicYearId()).get();
			holiday.setAcademicYear(ay);
    		holiday.setDescription(input.getDescription());
    		holiday.setStatus(input.getStatus());
    		holiday.setHolidayDate(input.getStrHolidayDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
			
    		holiday = holidayRepository.save(holiday);
        	
        	vo = CommonUtil.createCopyProperties(holiday, CmsHolidayVo.class);
        	vo.setStrCreatedOn(holiday.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(holiday.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(holiday.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(holiday.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrHolidayDate(holiday.getHolidayDate() != null ? DateFormatUtil.changeLocalDateFormat(holiday.getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
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
