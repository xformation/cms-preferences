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

import com.synectiks.pref.business.service.CmsTeachService;
import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.domain.vo.CmsTeachVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Teach.
 */
@RestController
@RequestMapping("/api")
public class TeachRestController {

    private final Logger logger = LoggerFactory.getLogger(TeachRestController.class);

    @Autowired
    private CmsTeachService cmsTeachService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteach-by-filters")
    public List<CmsTeachVo> getCmsTeachListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Teach based on filter criteria");
        List<CmsTeachVo> list = this.cmsTeachService.getCmsTeachListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/teach-by-filters")
    public List<Teach> getTeachListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Teach based on filter criteria");
        List<Teach> list = this.cmsTeachService.getTeachListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteach")
    public List<CmsTeachVo> getAllCmsTeach() throws Exception {
        logger.debug("REST request to get all Cms Teach");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsTeachService.getCmsTeachListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-teach")
    public List<Teach> getAllTeach() throws Exception {
        logger.debug("REST request to get all the Teach");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsTeachService.getTeachListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teach-by-id/{id}")
    public ResponseEntity<Teach> getTeach(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Teach : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsTeachService.getTeach(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsteach/{id}")
    public ResponseEntity<CmsTeachVo> getCmsTeach(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Teach : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsTeachService.getCmsTeach(id)));
    }
}
