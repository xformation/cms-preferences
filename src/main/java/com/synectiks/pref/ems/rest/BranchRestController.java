package com.synectiks.pref.ems.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.business.service.CmsBranchService;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.vo.CmsBranchVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Branch.
 */
@RestController
@RequestMapping("/api")
public class BranchRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private CmsBranchService cmsBranchService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbranch-by-filters")
    public List<CmsBranchVo> getCmsBranchListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Branches based on filter criteria");
        List<CmsBranchVo> list = this.cmsBranchService.getCmsBranchListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/branch-by-filters")
    public List<Branch> getBranchListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Branches based on filter criteria");
        List<Branch> list = this.cmsBranchService.getBranchListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbranch")
    public List<CmsBranchVo> getAllCmsBranch() throws Exception {
        logger.debug("REST request to get all Cms Branch");
        return this.cmsBranchService.getCmsBranchList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-branch")
    public List<Branch> getAllBranch() throws Exception {
        logger.debug("REST request to get all the branches");
        return this.cmsBranchService.getBranchList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/branch-by-id/{id}")
    public ResponseEntity<Branch> getBranch(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Branch : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsBranchService.getBranch(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsbranch/{id}")
    public ResponseEntity<CmsBranchVo> getCmsBranch(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Branch : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsBranchService.getCmsBranch(id)));
    }
    
}
