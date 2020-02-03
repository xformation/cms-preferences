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

import com.synectiks.pref.business.service.CmsBatchService;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.vo.CmsBatchVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Batch.
 */
@RestController
@RequestMapping("/api")
public class BatchRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private CmsBatchService cmsBatchService; 
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbatch-by-filters")
    public List<CmsBatchVo> getCmsBatchListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Batches based on filter criteria");
        List<CmsBatchVo> list = this.cmsBatchService.getCmsBatchListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/batch-by-filters")
    public List<Batch> getBatchListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Batches based on filter criteria");
        List<Batch> list = this.cmsBatchService.getBatchListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbatch")
    public List<CmsBatchVo> getAllCmsBatch() throws Exception {
        logger.debug("REST request to get all Cms Batches");
        Map<String, String> dataMap = new HashMap<String, String>();
        return this.cmsBatchService.getCmsBatchListOnFilterCriteria(dataMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-batch")
    public List<Batch> getAllBatch() throws Exception {
        logger.debug("REST request to get all the Batches");
        Map<String, String> dataMap = new HashMap<String, String>();
        return this.cmsBatchService.getBatchListOnFilterCriteria(dataMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/batch-by-id/{id}")
    public ResponseEntity<Batch> getBatch(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Batch : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsBatchService.getBatch(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsbatch/{id}")
    public ResponseEntity<CmsBatchVo> getCmsBatch(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Batch : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsBatchService.getCmsBatch(id)));
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbatches-departmentid/{id}")
	public List<CmsBatchVo> getAllBatchesByDepartmentId(@PathVariable Long id){
    	
    	Map<String, String> criteriaMap = new HashMap<String, String>();
    	criteriaMap.put("departmentId", String.valueOf(id));
    	
    	List<CmsBatchVo> ls = cmsBatchService.getCmsBatchListOnFilterCriteria(criteriaMap);
        return ls;
	}
}
