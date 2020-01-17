package com.synectiks.pref.ems.rest;


import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.domain.vo.CmsHolidayVo;
import com.synectiks.pref.repository.AcademicYearRepository;
import com.synectiks.pref.repository.HolidayRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class HolidayRestController {

    private final Logger logger = LoggerFactory.getLogger(HolidayRestController.class);
    private static final String ENTITY_NAME = "holiday";

    
    private String applicationName;
    
    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/cmsholidays")
    public ResponseEntity<CmsHolidayVo> createHoliday(@Valid @RequestBody CmsHolidayVo cmsHolidayVo) throws Exception {
        logger.debug("REST request to save an Holiday : {}", cmsHolidayVo);
        if (cmsHolidayVo.getId() != null) {
            throw new BadRequestAlertException("A new holiday cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        if(cmsHolidayVo.getStatus() == null) {
            cmsHolidayVo.setStatus(CmsConstants.STATUS_DEACTIVE);
        }
        Holiday hd = CommonUtil.createCopyProperties(cmsHolidayVo, Holiday.class);
        hd.setHolidayDate(DateFormatUtil.getLocalDateFromString(cmsHolidayVo.getStrHolidayDate()));
        
        hd = holidayRepository.save(hd);

        cmsHolidayVo.setId(hd.getId());
        cmsHolidayVo.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(hd.getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//        cmsHolidayVo.setStrHolidayDate(DateFormatUtil.changeDateFormat(Constants.DATE_FORMAT_dd_MM_yyyy, Constants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(Constants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(hd.getHolidayDate()))));

        return ResponseEntity.created(new URI("/api/holidays/" + cmsHolidayVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cmsHolidayVo.getId().toString()))
            .body(cmsHolidayVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsholidays")
    public ResponseEntity<CmsHolidayVo> updateHoliday(@Valid @RequestBody CmsHolidayVo cmsHolidayVo) throws Exception {
        logger.debug("REST request to update an Holiday : {}", cmsHolidayVo);
        if (cmsHolidayVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if(cmsHolidayVo.getStatus() == null) {
            cmsHolidayVo.setStatus(CmsConstants.STATUS_DEACTIVE);
        }
        
        Holiday hd = CommonUtil.createCopyProperties(cmsHolidayVo, Holiday.class);
        hd.setHolidayDate(DateFormatUtil.getLocalDateFromString(cmsHolidayVo.getStrHolidayDate()));
        
        hd = holidayRepository.save(hd);

        cmsHolidayVo.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(hd.getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cmsHolidayVo.getId().toString()))
            .body(cmsHolidayVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsholidays")
    public List<CmsHolidayVo> getAllHolidays() throws Exception {
        logger.debug("REST request to get all the Holidays");
        List<Holiday> list = holidayRepository.findAll();
        List<CmsHolidayVo> ls = new ArrayList<>();
        for(Holiday hd: list) {
            CmsHolidayVo chd = CommonUtil.createCopyProperties(hd, CmsHolidayVo.class);
            chd.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(hd.getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            ls.add(chd);
        }
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsholidays/{id}")
    public ResponseEntity<CmsHolidayVo> getHoliday(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an Holiday : {}", id);
        Optional<Holiday> hd = holidayRepository.findById(id);
        CmsHolidayVo chd = new CmsHolidayVo();
        if(hd.isPresent()) {
            chd = CommonUtil.createCopyProperties(hd.get(), CmsHolidayVo.class);
            chd.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(hd.get().getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(chd));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsholidays-by_academicyearid")
    public List<CmsHolidayVo> getAllHolidaysByAcademicYearId(@RequestParam Map<String, String> dataMap) throws Exception{
        if(!dataMap.containsKey("academicYearId")) {
            logger.warn("Academic year id is not provided. Returning empty holiday list");
            return Collections.emptyList();
        }
        List<CmsHolidayVo> ls = new ArrayList<>();
        Long id = Long.valueOf(dataMap.get("academicYearId"));
        Optional<AcademicYear> oay = this.academicYearRepository.findById(id);

        if(oay.isPresent()) {
            logger.debug("Holidays based on academic year. AcademicYear :"+oay.get());
            Holiday holiday = new Holiday();
            holiday.setAcademicYear(oay.get());
            Example<Holiday> exm = Example.of(holiday);
            List<Holiday> list = this.holidayRepository.findAll(exm);
            for(Holiday hd: list) {
                CmsHolidayVo chd = CommonUtil.createCopyProperties(hd, CmsHolidayVo.class);
                chd.setStrHolidayDate(DateFormatUtil.changeLocalDateFormat(hd.getHolidayDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                
                ls.add(chd);
            }
        }
        logger.debug("Total holidays retrieved: "+ls.size());
        return ls;

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsholidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        logger.debug("REST request to delete an Holiday : {}", id);
        holidayRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
