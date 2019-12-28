package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.pref.base64.file.Base64FileProcessor;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.config.Constants;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.exceptions.FileNameNotFoundException;
import com.synectiks.pref.exceptions.FilePathNotFoundException;
import com.synectiks.pref.repository.CollegeRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsCollegeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
	private Base64FileProcessor base64FileProcessor;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private CmsBranchService cmsBranchService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    public CmsCollegeVo addCollege(CmsCollegeVo cmsCollegeVo) {
    	CmsCollegeVo vo = null;
    	if(isCollegeExists()) {
    		vo = new CmsCollegeVo();
    		vo.setExitCode(100L);
    		vo.setExitDescription(Constants.ERROR_COLLEGE_ALREADY_EXISTS);
    		logger.error(Constants.VALIDATION_FAILURE + Constants.ERROR_COLLEGE_ALREADY_EXISTS);
    		return vo;
    	}
    	vo = cmsCollegeVo;
    	logger.info("Saving college");
    	try {
    		saveCollegeLogo(vo);
    		College college = saveCollege(vo);
    		saveCollegeAsMainBranch(vo, college);
    		vo.setExitCode(0L);
        	vo.setExitDescription("College is added successfully. It is created as default main branch also");
        }catch(Exception e) {
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, college data could not be saved");
    		logger.error("College save failed. Exception : ",e);
    		return vo;
    	}
    	logger.info("College saved successfully");
    	return vo;
    }
    
	@Transactional(propagation=Propagation.REQUIRED)
    public void saveCollegeLogo(CmsCollegeVo vo) throws FilePathNotFoundException, FileNameNotFoundException {
    	if(!CommonUtil.isNullOrEmpty(vo.getLogoFile())) {
    		logger.debug("Saving college logo. File path : "+applicationProperties.getImagePath());
			String ext = base64FileProcessor.getFileExtensionFromBase64Srting(vo.getLogoFile().split(",")[0]);
			base64FileProcessor.createFileFromBase64String(vo.getLogoFile(), applicationProperties.getImagePath(), 
					Constants.CMS_COLLEGE_LOGO_FILE_NAME, ext);
			vo.setLogoFileName(Constants.CMS_COLLEGE_LOGO_FILE_NAME);
			vo.setLogoFileExtension(ext);
			vo.setLogoFilePath(applicationProperties.getImagePath());
			logger.debug("College logo saved.");
		}
    }
    
	@Transactional(propagation=Propagation.REQUIRED)
    public College saveCollege(CmsCollegeVo vo) {
    	logger.debug("Saving college");
    	College college = CommonUtil.createCopyProperties(vo, College.class);
    	college.setCreatedOn(LocalDate.now());
    	college.setStatus(Constants.STATUS_ACTIVE);
    	college = collegeRepository.save(college);
    	vo.setStrCreatedOn(college.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(college.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
    	vo.setStrUpdatedOn(college.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(college.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
    	vo.setCreatedOn(null);
    	vo.setUpdatedOn(null);
    	logger.debug("College saved successfully");
    	return college;
    }
    
	@Transactional(propagation=Propagation.REQUIRED)
    public void saveCollegeAsMainBranch(CmsCollegeVo vo, College college) {
    	logger.debug("Saving college as main branch in branch table");
    	this.cmsBranchService.saveCollegeAsMainBranch(vo, college);
    	logger.debug("College made main branch successfully");
    }
    
    private boolean isCollegeExists() {
    	List<College> existingCollegeList = collegeRepository.findAll();
		if(existingCollegeList.size() >= 1) {
			return true;
		}
		return false;
    }
    
    public College getCollege() {
    	List<College> list = collegeRepository.findAll();
    	if(list.size() > 0) {
    		return list.get(0);
    	}
    	return null;
    }
}
