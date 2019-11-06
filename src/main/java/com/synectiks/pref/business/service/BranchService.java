package com.synectiks.pref.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.repository.BranchRepository;

@Component
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

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
