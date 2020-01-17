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

import com.synectiks.pref.business.service.CmsDepartmentService;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class DepartmentRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CmsDepartmentService cmsDepartmentService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmsdepartment-by-filters")
    public List<CmsDepartmentVo> getCmsDepartmentListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Departments based on filter criteria");
        List<CmsDepartmentVo> list = this.cmsDepartmentService.getCmsDepartmentListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/department-by-filters")
    public List<Department> getDepartmentListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Departments based on filter criteria");
        List<Department> list = this.cmsDepartmentService.getDepartmentListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsdepartment")
    public List<CmsDepartmentVo> getAllCmsDepartment() throws Exception {
        logger.debug("REST request to get all Cms Departments");
        return this.cmsDepartmentService.getCmsDepartmentList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-department")
    public List<Department> getAllDepartment() throws Exception {
        logger.debug("REST request to get all the Departments");
        return this.cmsDepartmentService.getDepartmentList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/department-by-id/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Department : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsDepartmentService.getDepartment(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsdepartment/{id}")
    public ResponseEntity<CmsDepartmentVo> getCmsDepartment(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Department : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsDepartmentService.getCmsDepartment(id)));
    }
    
}
