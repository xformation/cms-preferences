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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.State;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.graphql.types.branch.BranchInput;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsBranchService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    CmsStateService cmsStateService;

    @Autowired
    CmsCityService cmsCityService;

    @Autowired
    CmsCollegeService cmsCollegeService;

    
    @Transactional(propagation=Propagation.REQUIRED)
    public Branch saveCollegeAsMainBranch(CmsCollegeVo vo, College college) {
    	logger.debug("Making college as main branch");
    	Branch branch = new Branch();
    	branch.setBranchName(vo.getCollegeName());
    	branch.setIsMainBranch(CmsConstants.YES);
    	branch.setCreatedOn(LocalDate.now());
    	branch.setStatus(Constants.STATUS_ACTIVE);
    	branch.setCollege(college);
    	branch = this.branchRepository.save(branch);
    	logger.debug("Main branch college created successfully");
    	return branch;
    }
    
    public List<CmsBranchVo> getBranchList(String status) {
        Branch branch = new Branch();
        branch.setStatus(status);
        List<Branch> list = this.branchRepository.findAll(Example.of(branch));
        List<CmsBranchVo> ls = changeBranchToCmsBranchList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<CmsBranchVo> getBranchList(){
    	List<Branch> list = this.branchRepository.findAll();
    	List<CmsBranchVo> ls = changeBranchToCmsBranchList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    private List<CmsBranchVo> changeBranchToCmsBranchList(List<Branch> list){
    	List<CmsBranchVo> ls = new ArrayList<>();
    	for(Branch br: list) {
    		CmsBranchVo vo = CommonUtil.createCopyProperties(br, CmsBranchVo.class);
    		if(br.getStartDate() != null) {
            	vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(br.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setStartDate(null);
            }
            if(br.getCreatedOn() != null) {
            	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(br.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setCreatedOn(null);
            }
            if(br.getUpdatedOn() != null) {
            	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(br.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setUpdatedOn(null);
            }
            if(br.getState() != null) {
            	vo.setStateId(br.getState().getId());
            }
            if(br.getCity() != null) {
            	vo.setCityId(br.getCity().getId());
            }
            if(br.getCollege() != null) {
            	vo.setCollegeId(br.getCollege().getId());
            }
    		ls.add(vo);
    	}
    	return ls;
    }
    
    public CmsBranchVo saveBranch(BranchInput cmsBranchVo) {
    	logger.info("Saving branch");
    	CmsBranchVo vo = null;
    	try {
    		Branch branch = CommonUtil.createCopyProperties(cmsBranchVo, Branch.class);
    		if(branch.getId() == null) {
    			branch.setCreatedOn(LocalDate.now());
    		}else {
    			branch.setUpdatedOn(LocalDate.now());
    		}
        	State state = cmsStateService.getState(cmsBranchVo.getStateId());
        	City city = cmsCityService.getCity(cmsBranchVo.getCityId());
        	College college = cmsCollegeService.getCollege();
        	branch.setState(state);
        	branch.setCity(city);
        	branch.setCollege(college);
        	branch.setIsMainBranch(CmsConstants.NO);
        	branch = branchRepository.save(branch);
        	vo = CommonUtil.createCopyProperties(branch, CmsBranchVo.class);
        	vo.setStrCreatedOn(branch.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(branch.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(branch.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(branch.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	vo.setExitDescription("Branch is added successfully");
        	logger.debug("Branch is added successfully");
        }catch(Exception e) {
        	vo = new CmsBranchVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, branch data not be saved");
    		logger.error("Branch save failed. Exception : ",e);
    	}
    	logger.info("Branch saved successfully");
    	return vo;
        
    }
}
