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

import com.synectiks.pref.business.service.CmsHolidayService;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.domain.vo.CmsHolidayVo;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class HolidayRestController {

    private final Logger logger = LoggerFactory.getLogger(HolidayRestController.class);
    
    @Autowired
    private CmsHolidayService cmsHolidayService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmsholiday-by-filters")
    public List<CmsHolidayVo> getCmsHolidayListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Holiday based on filter criteria");
        List<CmsHolidayVo> list = this.cmsHolidayService.getCmsHolidayListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/holiday-by-filters")
    public List<Holiday> getHolidayListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Holiday based on filter criteria");
        List<Holiday> list = this.cmsHolidayService.getHolidayListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsholiday")
    public List<CmsHolidayVo> getAllCmsHoliday() throws Exception {
        logger.debug("REST request to get all Cms Holiday");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsHolidayService.getCmsHolidayListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-holiday")
    public List<Holiday> getAllHoliday() throws Exception {
        logger.debug("REST request to get all the Holiday");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsHolidayService.getHolidayListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/holiday-by-id/{id}")
    public ResponseEntity<Holiday> getHoliday(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Holiday : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsHolidayService.getHoliday(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsholiday/{id}")
    public ResponseEntity<CmsHolidayVo> getCmsHoliday(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Holiday : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsHolidayService.getCmsHoliday(id)));
    }
    
}
