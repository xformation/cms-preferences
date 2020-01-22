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
import com.synectiks.pref.domain.Term;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsTermVo;
import com.synectiks.pref.graphql.types.term.TermInput;
import com.synectiks.pref.repository.TermRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsTermService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    TermRepository termRepository;
    
    @Autowired
    CmsAcademicYearService cmsAcademicYearService;
    
    public List<CmsTermVo> getCmsTermListOnFilterCriteria(Map<String, String> criteriaMap){
    	Term tr = new Term();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		tr.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		tr.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		tr.setDescription(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("comments") != null) {
    		tr.setComments(criteriaMap.get("comments"));
    		isFilter = true;
    	}
    	
    	if(criteriaMap.get("academicYearId") != null) {
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
    		tr.setAcademicYear(ay);
    		isFilter = true;
    	}
    	
    	List<Term> list = null;
    	if(isFilter) {
    		list = this.termRepository.findAll(Example.of(tr), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.termRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsTermVo> ls = changeTermToCmsTermList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Term> getTermListOnFilterCriteria(Map<String, String> criteriaMap){
    	Term tr = new Term();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		tr.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		tr.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		tr.setDescription(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("comments") != null) {
    		tr.setComments(criteriaMap.get("comments"));
    		isFilter = true;
    	}
    	
    	if(criteriaMap.get("academicYearId") != null) {
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
    		tr.setAcademicYear(ay);
    		isFilter = true;
    	}
    	
    	List<Term> list = null;
    	if(isFilter) {
    		list = this.termRepository.findAll(Example.of(tr), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.termRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public List<CmsTermVo> getCmsTermList(String status) {
    	Term tr = new Term();
        tr.setStatus(status);
        List<Term> list = this.termRepository.findAll(Example.of(tr));
        List<CmsTermVo> ls = changeTermToCmsTermList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    public List<Term> getTermList(String status) {
    	Term term = new Term();
        term.setStatus(status);
        List<Term> list = this.termRepository.findAll(Example.of(term));
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }
    public List<Term> getTermList(){
    	List<Term> ls = this.termRepository.findAll();
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public CmsTermVo getCmsTerm(Long id){
    	Optional<Term> br = this.termRepository.findById(id);
    	if(br.isPresent()) {
    		CmsTermVo vo = CommonUtil.createCopyProperties(br.get(), CmsTermVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsTerm for given id : "+id+". CmsTerm object : "+vo);
        	return vo;
    	}
    	logger.debug("Term object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Term getTerm(Long id){
    	Optional<Term> br = this.termRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Term object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    /////////////////////////////////////////////////////////////
    public List<CmsTermVo> getCmsTermList(){
    	List<Term> list = this.termRepository.findAll();
    	List<CmsTermVo> ls = changeTermToCmsTermList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    
    
    private List<CmsTermVo> changeTermToCmsTermList(List<Term> list){
    	List<CmsTermVo> ls = new ArrayList<>();
    	for(Term tr: list) {
    		CmsTermVo vo = CommonUtil.createCopyProperties(tr, CmsTermVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Term tr, CmsTermVo vo) {
		vo.setTermsDesc(tr.getDescription());
		if(tr.getStartDate() != null) {
			vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(tr.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(tr.getEndDate() != null) {
			vo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(tr.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(tr.getCreatedOn() != null) {
			vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(tr.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(tr.getUpdatedOn() != null) {
			vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(tr.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(tr.getAcademicYear() != null) {
			vo.setAcademicYearId(tr.getAcademicYear().getId());
			CmsAcademicYearVo cmsAvo =CommonUtil.createCopyProperties(tr.getAcademicYear(), CmsAcademicYearVo.class); 
			vo.setCmsAcademicYearVo(cmsAvo);
		}
	}
    
    public CmsTermVo saveTerm(TermInput input) {
    	logger.info("Saving term");
    	CmsTermVo vo = null;
    	try {
    		Term term = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new term");
    			term = CommonUtil.createCopyProperties(input, Term.class);
    			term.setCreatedOn(LocalDate.now());
    			term.setComments(input.getComments());
    		}else {
    			logger.debug("Updating existing term");
    			term = this.termRepository.findById(input.getId()).get();
    			term.setUpdatedOn(LocalDate.now());
    			if(input.getComments() != null) {
    				term.setComments(input.getComments());
    			}
    		}
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(input.getAcademicYearId());
			term.setAcademicYear(ay);
    		term.setDescription(input.getDescription());
    		term.setStatus(input.getStatus());
    		term.setStartDate(input.getStrStartDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
    		term.setEndDate(input.getStrEndDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
    		
    		term = termRepository.save(term);
        	
        	vo = CommonUtil.createCopyProperties(term, CmsTermVo.class);
        	vo.setStrCreatedOn(term.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(term.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(term.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(term.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrStartDate(term.getStartDate() != null ? DateFormatUtil.changeLocalDateFormat(term.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrEndDate(term.getEndDate() != null ? DateFormatUtil.changeLocalDateFormat(term.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("Term is added successfully");
        		logger.debug("Term is added successfully");
        	}else {
        		vo.setExitDescription("Term is updated successfully");
        		logger.debug("Term is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsTermVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, term data not be saved");
    		logger.error("Term save failed. Exception : ",e);
    	}
    	logger.info("Term saved successfully");
    	List ls =  getCmsTermList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
