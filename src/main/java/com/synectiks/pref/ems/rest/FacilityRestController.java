package com.synectiks.pref.ems.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.synectiks.pref.business.service.CmsFacilityService;
import com.synectiks.pref.domain.Facility;
import com.synectiks.pref.domain.vo.CmsFacility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.business.service.CmsBatchService;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.vo.CmsBatchVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Batch.
 */
@RestController
@RequestMapping("/api")
public class FacilityRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CmsFacilityService cmsFacilityService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsfacility-by-filters")
    public List<CmsFacility> getCmsFacilityListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Facilities based on filter criteria");
        List<CmsFacility> list = this.cmsFacilityService.getCmsFacilityListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/facility-by-filters")
    public List<Facility> getFacilityListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Facilities based on filter criteria");
        List<Facility> list = this.cmsFacilityService.getFacilityListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsfacility")
    public List<CmsFacility> getAllCmsFacility() throws Exception {
        logger.debug("REST request to get all Cms Facilities");
        Map<String, String> dataMap = new HashMap<String, String>();
        return this.cmsFacilityService.getCmsFacilityListOnFilterCriteria(dataMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-facility")
    public List<Facility> getAllFacility() throws Exception {
        logger.debug("REST request to get all the Facilities");
        Map<String, String> dataMap = new HashMap<String, String>();
        return this.cmsFacilityService.getFacilityListOnFilterCriteria(dataMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/facility-by-id/{id}")
    public ResponseEntity<Facility> getFacility(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Facility : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsFacilityService.getFacility(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsfacility/{id}")
    public ResponseEntity<CmsFacility> getCmsFacility(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Facility : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsFacilityService.getCmsFacility(id)));
    }

}
