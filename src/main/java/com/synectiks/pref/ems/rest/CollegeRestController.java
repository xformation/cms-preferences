package com.synectiks.pref.ems.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.business.service.CmsCollegeService;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.vo.CmsCollegeVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing College.
 */
@RestController
@RequestMapping("/api")
public class CollegeRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private CmsCollegeService cmsCollegeService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/college")
    public ResponseEntity<College> getCollege() throws Exception {
        logger.debug("REST request to get a College : {}");
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsCollegeService.getCollege()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmscollege")
    public ResponseEntity<CmsCollegeVo> getCmsCollege() throws Exception {
        logger.debug("REST request to get a CmsCollege : {}");
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsCollegeService.getCmsCollege()));
    }
	
}
