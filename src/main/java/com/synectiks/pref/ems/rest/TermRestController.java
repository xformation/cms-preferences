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

import com.synectiks.pref.business.service.CmsTermService;
import com.synectiks.pref.domain.Term;
import com.synectiks.pref.domain.vo.CmsTermVo;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class TermRestController {

    private final Logger logger = LoggerFactory.getLogger(TermRestController.class);

    @Autowired
    private CmsTermService cmsTermService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsterm-by-filters")
    public List<CmsTermVo> getCmsTermListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Terms based on filter criteria");
        List<CmsTermVo> list = this.cmsTermService.getCmsTermListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/term-by-filters")
    public List<Term> getTermListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Terms based on filter criteria");
        List<Term> list = this.cmsTermService.getTermListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsterm")
    public List<CmsTermVo> getAllCmsTerm() throws Exception {
        logger.debug("REST request to get all Cms Terms");
        return this.cmsTermService.getCmsTermList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-term")
    public List<Term> getAllTerm() throws Exception {
        logger.debug("REST request to get all the Terms");
        return this.cmsTermService.getTermList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/term-by-id/{id}")
    public ResponseEntity<Term> getTerm(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Term : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsTermService.getTerm(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsterm/{id}")
    public ResponseEntity<CmsTermVo> getCmsTerm(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Term : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsTermService.getCmsTerm(id)));
    }

}

