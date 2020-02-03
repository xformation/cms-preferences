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

import com.synectiks.pref.business.service.CmsAcademicYearService;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing AcademicYear.
 */
@RestController
@RequestMapping("/api")
public class AcademicYearRestController {

    private final Logger logger = LoggerFactory.getLogger(AcademicYearRestController.class);

    @Autowired
    private CmsAcademicYearService cmsAcademicYearService; 
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-years-by-filters")
    public List<CmsAcademicYearVo> getCmsAcademicYearListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms AcademicYears based on filter criteria");
        List<CmsAcademicYearVo> list = this.cmsAcademicYearService.getCmsAcademicYearListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/academic-years-by-filters")
    public List<AcademicYear> getAcademicYearListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of AcademicYears based on filter criteria");
        List<AcademicYear> list = this.cmsAcademicYearService.getAcademicYearListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-years")
    public List<CmsAcademicYearVo> getAllCmsAcademicYears() throws Exception {
        logger.debug("REST request to get all Cms AcademicYears");
        return this.cmsAcademicYearService.getCmsAcademicYearList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-academic-years")
    public List<AcademicYear> getAllAcademicYears() throws Exception {
        logger.debug("REST request to get all the AcademicYears");
        return this.cmsAcademicYearService.getAcademicYearList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/academic-years-by-id/{id}")
    public ResponseEntity<AcademicYear> getAcademicYear(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an AcademicYear : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsAcademicYearService.getAcademicYear(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-years/{id}")
    public ResponseEntity<CmsAcademicYearVo> getCmsAcademicYear(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an Cms AcademicYear : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsAcademicYearService.getCmsAcademicYear(id)));
    }
    
//    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-years/{id}")
//    public ResponseEntity<CmsAcademicYearVo> getAcademicYear(@PathVariable Long id) throws Exception {
//        logger.debug("REST request to get an AcademicYear : {}", id);
//        Optional<AcademicYear> ay = academicYearRepository.findById(id);
//        CmsAcademicYearVo cay = new CmsAcademicYearVo();
//        if(ay.isPresent()) {
//            cay = CommonUtil.createCopyProperties(ay.get(), CmsAcademicYearVo.class);
//            cay.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ay.get().getStartDate()))));
//            cay.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ay.get().getEndDate()))));
//        }
//        return ResponseUtil.wrapOrNotFound(Optional.of(cay));
//    }

}
