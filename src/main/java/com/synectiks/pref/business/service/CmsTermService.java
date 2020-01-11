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
import com.synectiks.pref.domain.Term;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsTermVo;
import com.synectiks.pref.graphql.types.term.TermInput;
import com.synectiks.pref.repository.AcademicYearRepository;
import com.synectiks.pref.repository.TermRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsTermService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    TermRepository termRepository;
    
    @Autowired
    AcademicYearRepository academicYearRepository;

    public List<CmsTermVo> getTermList(String status) {
    	Term term = new Term();
        term.setStatus(status);
        List<Term> list = this.termRepository.findAll(Example.of(term));
        List<CmsTermVo> ls = changeTermToCmsTermList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<CmsTermVo> getTermList(){
    	List<Term> list = this.termRepository.findAll();
    	List<CmsTermVo> ls = changeTermToCmsTermList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    private List<CmsTermVo> changeTermToCmsTermList(List<Term> list){
    	List<CmsTermVo> ls = new ArrayList<>();
    	for(Term tr: list) {
    		CmsTermVo vo = CommonUtil.createCopyProperties(tr, CmsTermVo.class);
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
    		ls.add(vo);
    	}
    	return ls;
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
    		AcademicYear ay = this.academicYearRepository.findById(input.getAcademicYearId()).get();
			term.setAcademicYear(ay);
    		term.setDescription(input.getDescription());
    		term.setStatus(input.getStatus());
    		term.setStartDate(input.getStrStartDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
    		term.setEndDate(input.getStrEndDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
    		
    		term = termRepository.save(term);
        	
        	vo = CommonUtil.createCopyProperties(term, CmsTermVo.class);
        	vo.setStrCreatedOn(term.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(term.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(term.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(term.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrStartDate(term.getStartDate() != null ? DateFormatUtil.changeLocalDateFormat(term.getStartDate(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrEndDate(term.getEndDate() != null ? DateFormatUtil.changeLocalDateFormat(term.getEndDate(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
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
    	List ls =  getTermList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
