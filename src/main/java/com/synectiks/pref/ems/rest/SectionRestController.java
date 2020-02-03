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

import com.synectiks.pref.business.service.CmsSectionService;
import com.synectiks.pref.domain.Section;
import com.synectiks.pref.domain.vo.CmsSectionVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Section.
 */
@RestController
@RequestMapping("/api")
public class SectionRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private CmsSectionService cmsSectionService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmssection-by-filters")
    public List<CmsSectionVo> getCmsSectionListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Sections based on filter criteria");
        List<CmsSectionVo> list = this.cmsSectionService.getCmsSectionListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/section-by-filters")
    public List<Section> getSectionListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Sectiones based on filter criteria");
        List<Section> list = this.cmsSectionService.getSectionListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssection")
    public List<CmsSectionVo> getAllCmsSection() throws Exception {
        logger.debug("REST request to get all Cms Sections");
        Map<String, String> dataMap = new HashMap<String, String>();
        return this.cmsSectionService.getCmsSectionListOnFilterCriteria(dataMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-section")
    public List<Section> getAllSection() throws Exception {
        logger.debug("REST request to get all the Sections");
        Map<String, String> dataMap = new HashMap<String, String>();
        return this.cmsSectionService.getSectionListOnFilterCriteria(dataMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/section-by-id/{id}")
    public ResponseEntity<Section> getSection(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Section : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsSectionService.getSection(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmssection/{id}")
    public ResponseEntity<CmsSectionVo> getCmsSection(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Cms Section : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsSectionService.getCmsSection(id)));
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssections-batchid/{id}")
	public List<CmsSectionVo> getAllSectionsByBatchId(@PathVariable Long id){
		
    	Map<String, String> criteriaMap = new HashMap<String, String>();
    	criteriaMap.put("batchId", String.valueOf(id));
    	List<CmsSectionVo> list = this.cmsSectionService.getCmsSectionListOnFilterCriteria(criteriaMap);
    	return list;
    	
	}
    
}
