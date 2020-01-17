package com.synectiks.pref.ems.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.base64.file.Base64FileProcessor;
import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.repository.CollegeRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing College.
 */
@RestController
@RequestMapping("/api")
public class CollegeRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private Base64FileProcessor base64FileProcessor;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmscollege")
    public List<CmsCollegeVo> getAllColleges() {
		logger.debug("REST request to get all the college records.");
		List<CmsCollegeVo> ls = new ArrayList<>();
		List<College> list = collegeRepository.findAll();
		for(College college: list) {
			CmsCollegeVo vo = CommonUtil.createCopyProperties(college, CmsCollegeVo.class);
			if(!CommonUtil.isNullOrEmpty(college.getLogoFilePath())){
				String filePath = college.getLogoFilePath()+File.separatorChar+college.getLogoFileName()
									+"."+college.getLogoFileExtension();
				vo.setLogoFile(this.base64FileProcessor.createBase64StringFromFile(filePath));
			}
			if(college.getCreatedOn() != null) {
				vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(college.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
			}
			if(college.getUpdatedOn() != null) {
				vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(college.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
			}
			ls.add(vo);
		}
        return ls;
    }
	
	/**
     * {@code GET  /cmscollege/:id} : get the "id" college.
     *
     * @param id the id of the CmsCollegeVo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the CmsCollegeVo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cmscollege/{id}")
    public ResponseEntity<CmsCollegeVo> getCollege(@PathVariable Long id) {
        logger.debug("REST request to get a College by id : ", id);
        Optional<College> oClg = collegeRepository.findById(id);
        CmsCollegeVo vo = null;
        if(oClg.isPresent()) {
        	College college = oClg.get();
        	vo = CommonUtil.createCopyProperties(college, CmsCollegeVo.class);
			if(!CommonUtil.isNullOrEmpty(college.getLogoFilePath())){
				String filePath = college.getLogoFilePath()+File.separatorChar+college.getLogoFileName()
									+"."+college.getLogoFileExtension();
				vo.setLogoFile(this.base64FileProcessor.createBase64StringFromFile(filePath));
			}
			if(college.getCreatedOn() != null) {
				vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(college.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
			}
			if(college.getUpdatedOn() != null) {
				vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(college.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
			}
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }
	
}
