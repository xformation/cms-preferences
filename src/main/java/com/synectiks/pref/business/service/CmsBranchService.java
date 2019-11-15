package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.repository.BranchRepository;

@Component
public class CmsBranchService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BranchRepository branchRepository;

    @Transactional(propagation=Propagation.REQUIRED)
    public Branch saveCollegeAsMainBranch(CmsCollegeVo vo, College college) {
    	logger.debug("Making college as main branch");
    	Branch branch = new Branch();
    	branch.setBranchName(vo.getCollegeName());
    	branch.setIsMainBranch(Constants.YES);
    	branch.setCreatedOn(LocalDate.now());
    	branch.setStatus(Constants.STATUS_ACTIVE);
    	branch.setCollege(college);
    	branch = this.branchRepository.save(branch);
    	logger.debug("Main branch college created successfully");
    	return branch;
    }
    
    public List<Branch> getAllBranches(String branchName, Long collegeId) {
        Branch branch = new Branch();
        if (branchName != null) {
            branch.setBranchName(branchName);
        }
        if(collegeId != null) {
            College college = new College();
            college.setId(collegeId);
            branch.setCollege(college);
        }
        Example<Branch> example = Example.of(branch);
        List<Branch> list = this.branchRepository.findAll(example);
        return list;
    }
}
