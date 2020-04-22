package com.synectiks.pref.ems.rest;


import java.util.HashMap;
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

import com.synectiks.pref.business.service.CmsTeacherService;
import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.domain.vo.CmsTeacherVo;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class TeacherRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CmsTeacherService cmsTeacherService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteacher-by-filters")
    public List<CmsTeacherVo> getCmsTeacherListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Teachers based on filter criteria");
        List<CmsTeacherVo> list = this.cmsTeacherService.getCmsTeacherListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/teacher-by-filters")
    public List<Teacher> getTeacherListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Teachers based on filter criteria");
        List<Teacher> list = this.cmsTeacherService.getTeacherListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteacher")
    public List<CmsTeacherVo> getAllCmsTeacher() throws Exception {
        logger.debug("REST request to get all Cms Teachers");
        return this.cmsTeacherService.getCmsTeacherList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-teacher")
    public List<Teacher> getAllTeacher() throws Exception {
        logger.debug("REST request to get all the Teachers");
        return this.cmsTeacherService.getTeacherList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teacher-by-id/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Teacher : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsTeacherService.getTeacher(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsteacher/{id}")
    public ResponseEntity<CmsTeacherVo> getCmsTeacher(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Teacher : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsTeacherService.getCmsTeacher(id)));
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteachers-qryprms")
    public List<CmsTeacherVo> getAllTeachersByDeptBranchId(@RequestParam Map<String, String> dataMap) {
        logger.debug("REST request to get all the Teachers");
        
        Map<String, String> criteriaMap = new HashMap<String, String>();
        if (dataMap.containsKey("deptId")) {
        	criteriaMap.put("departmentId", dataMap.get("deptId"));
        }
        if (dataMap.containsKey("branchId")) {
        	criteriaMap.put("branchId", dataMap.get("branchId"));
        }
        List<CmsTeacherVo> list = this.cmsTeacherService.getCmsTeacherListOnFilterCriteria(criteriaMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/send-teacher-to-kafka")
    public void pushToKafka() {
    	logger.info("Start pushing teacher entities to kafka"); 
    	this.cmsTeacherService.pushToKafka();
    	logger.info("All teacher entities successfully uploaded to kafka");
    }
}
