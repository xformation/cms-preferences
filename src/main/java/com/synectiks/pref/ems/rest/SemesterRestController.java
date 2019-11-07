package com.synectiks.pref.ems.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.business.service.CommonService;

/**
 * REST controller for managing Semester.
 */
@RestController
@RequestMapping("/api")
public class SemesterRestController {

    private final Logger logger = LoggerFactory.getLogger(SemesterRestController.class);

    private static final String ENTITY_NAME = "semester";
    
    
    private String applicationName;
    
    @Autowired
    private CommonService commonService;
    
    
//    @RequestMapping(method = RequestMethod.GET, value = "/cmssemesters")
//    public List<CmsSemesterVo> getAllSemesters() throws Exception {
//        logger.debug("REST request to get all the "+ENTITY_NAME);
//        return this.commonService.getAllSemesters();
//    }
//
//    
//    @RequestMapping(method = RequestMethod.GET, value = "/cmssemesters/{id}")
//    public ResponseEntity<CmsSemesterVo> getSemester(@PathVariable Long id) throws Exception {
//        logger.debug("REST request to get a semester : {}", id);
//        CmsSemesterVo vo = this.commonService.getSemester(id);
//        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
//    }

}
