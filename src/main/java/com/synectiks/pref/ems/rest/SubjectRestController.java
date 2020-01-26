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

import com.synectiks.pref.business.service.CmsSubjectService;
import com.synectiks.pref.domain.Subject;
import com.synectiks.pref.domain.vo.CmsSubjectVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Subject and Teach.
 */
@RestController
@RequestMapping("/api")
public class SubjectRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private CmsSubjectService cmsSubjectService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmssubject-by-filters")
    public List<CmsSubjectVo> getCmsSubjectListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Subjects based on filter criteria");
        List<CmsSubjectVo> list = this.cmsSubjectService.getCmsSubjectListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/subject-by-filters")
    public List<Subject> getSubjectListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Subjects based on filter criteria");
        List<Subject> list = this.cmsSubjectService.getSubjectListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssubject")
    public List<CmsSubjectVo> getAllCmsSubject() throws Exception {
        logger.debug("REST request to get all Cms Subjects");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsSubjectService.getCmsSubjectListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-subject")
    public List<Subject> getAllSubject() throws Exception {
        logger.debug("REST request to get all the Subjects");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsSubjectService.getSubjectListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subject-by-id/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Subject : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsSubjectService.getSubject(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmssubject/{id}")
    public ResponseEntity<CmsSubjectVo> getCmsSubject(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Subject : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsSubjectService.getCmsSubject(id)));
    }
}
