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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    	branch.setStatus(CmsConstants.STATUS_ACTIVE);
    	branch.setCollege(college);
    	branch = this.branchRepository.save(branch);
    	logger.debug("Main branch college created successfully");
    	return branch;
    }
    
    public List<CmsBranchVo> getCmsBranchListOnFilterCriteria(Map<String, String> criteriaMap){
    	Branch ay = new Branch();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		ay.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		ay.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchName") != null) {
    		ay.setBranchName(criteriaMap.get("branchName"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("address") != null) {
    		ay.setAddress(criteriaMap.get("address"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("pinCode") != null) {
    		ay.setPinCode(criteriaMap.get("pinCode"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchHead") != null) {
    		ay.setBranchHead(criteriaMap.get("branchHead"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("cellPhoneNo") != null) {
    		ay.setCellPhoneNo(criteriaMap.get("cellPhoneNo"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("landLinePhoneNo") != null) {
    		ay.setLandLinePhoneNo(criteriaMap.get("landLinePhoneNo"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("emailId") != null) {
    		ay.setEmailId(criteriaMap.get("emailId"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("faxNo") != null) {
    		ay.setFaxNo(criteriaMap.get("faxNo"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("isMainBranch") != null) {
    		ay.setIsMainBranch(criteriaMap.get("isMainBranch"));
    		isFilter = true;
    	}
    	
    	List<Branch> list = null;
    	if(isFilter) {
    		list = this.branchRepository.findAll(Example.of(ay), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.branchRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsBranchVo> ls = changeBranchToCmsBranchList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Branch> getBranchListOnFilterCriteria(Map<String, String> criteriaMap){
    	Branch ay = new Branch();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		ay.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		ay.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchName") != null) {
    		ay.setBranchName(criteriaMap.get("branchName"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("address") != null) {
    		ay.setAddress(criteriaMap.get("address"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("pinCode") != null) {
    		ay.setPinCode(criteriaMap.get("pinCode"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchHead") != null) {
    		ay.setBranchHead(criteriaMap.get("branchHead"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("cellPhoneNo") != null) {
    		ay.setCellPhoneNo(criteriaMap.get("cellPhoneNo"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("landLinePhoneNo") != null) {
    		ay.setLandLinePhoneNo(criteriaMap.get("landLinePhoneNo"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("emailId") != null) {
    		ay.setEmailId(criteriaMap.get("emailId"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("faxNo") != null) {
    		ay.setFaxNo(criteriaMap.get("faxNo"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("isMainBranch") != null) {
    		ay.setIsMainBranch(criteriaMap.get("isMainBranch"));
    		isFilter = true;
    	}
    	
    	List<Branch> list = null;
    	if(isFilter) {
    		list = this.branchRepository.findAll(Example.of(ay), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.branchRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public List<CmsBranchVo> getCmsBranchList(String status) {
        Branch branch = new Branch();
        branch.setStatus(status);
        List<Branch> list = this.branchRepository.findAll(Example.of(branch));
        List<CmsBranchVo> ls = changeBranchToCmsBranchList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<Branch> getBranchList(String status) {
        Branch branch = new Branch();
        branch.setStatus(status);
        List<Branch> list = this.branchRepository.findAll(Example.of(branch));
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }
    
    public List<CmsBranchVo> getCmsBranchList(){
    	List<Branch> list = this.branchRepository.findAll();
    	List<CmsBranchVo> ls = changeBranchToCmsBranchList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Branch> getBranchList(){
    	List<Branch> list = this.branchRepository.findAll();
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public CmsBranchVo getCmsBranch(Long id){
    	Optional<Branch> br = this.branchRepository.findById(id);
    	if(br.isPresent()) {
    		CmsBranchVo vo = CommonUtil.createCopyProperties(br.get(), CmsBranchVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsBranch for given id : "+id+". CmsBranch object : "+vo);
        	return vo;
    	}
    	logger.debug("Branch object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Branch getBranch(Long id){
    	Optional<Branch> br = this.branchRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Branch object not found for the given id. "+id+". Returning null");
        return null;
    }
    private List<CmsBranchVo> changeBranchToCmsBranchList(List<Branch> list){
    	List<CmsBranchVo> ls = new ArrayList<>();
    	for(Branch br: list) {
    		CmsBranchVo vo = CommonUtil.createCopyProperties(br, CmsBranchVo.class);
    		convertDatesAndProvideDependencies(br, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Branch br, CmsBranchVo vo) {
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
	}
    
    public CmsBranchVo saveBranch(BranchInput cmsBranchVo) {
    	logger.info("Saving branch");
    	CmsBranchVo vo = null;
    	try {
    		Branch branch = null;
    		if(cmsBranchVo.getId() == null) {
    			logger.debug("Adding new branch");
    			branch = CommonUtil.createCopyProperties(cmsBranchVo, Branch.class);
    			branch.setCreatedOn(LocalDate.now());
    			branch.setIsMainBranch(CmsConstants.NO);
    		}else {
    			logger.debug("Updating existing branch");
    			branch = this.branchRepository.findById(cmsBranchVo.getId()).get();
    			branch.setUpdatedOn(LocalDate.now());
    			branch.setBranchName(cmsBranchVo.getBranchName());
    			branch.setAddress(cmsBranchVo.getAddress());
    			branch.setPinCode(cmsBranchVo.getPinCode());
    			branch.setBranchHead(cmsBranchVo.getBranchHead());
    			branch.setStatus(cmsBranchVo.getStatus());
    		}
        	State state = cmsStateService.getState(cmsBranchVo.getStateId());
        	City city = cmsCityService.getCity(cmsBranchVo.getCityId());
        	College college = cmsCollegeService.getCollege();
        	branch.setState(state);
        	branch.setCity(city);
        	branch.setCollege(college);
        	
        	branch = branchRepository.save(branch);
        	vo = CommonUtil.createCopyProperties(branch, CmsBranchVo.class);
        	vo.setStrCreatedOn(branch.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(branch.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(branch.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(branch.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	if(cmsBranchVo.getId() == null) {
        		vo.setExitDescription("Branch is added successfully");
        		logger.debug("Branch is added successfully");
        	}else {
        		vo.setExitDescription("Branch is updated successfully");
        		logger.debug("Branch is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsBranchVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, branch data not be saved");
    		logger.error("Branch save failed. Exception : ",e);
    	}
    	logger.info("Branch saved successfully");
    	List ls =  getCmsBranchList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
