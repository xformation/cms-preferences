package com.synectiks.pref.ems.rest;

import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.base64.file.Base64FileProcessor;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.config.Constants;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.repository.CollegeRepository;

/**
 * REST controller for managing College.
 */
@RestController
@RequestMapping("/api")
public class CollegeRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
    private String applicationName;
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private Base64FileProcessor base64FileProcessor;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmscollege")
	public int createCollege(@RequestBody CmsCollegeVo cmsCollegeVo) {
		logger.info("REST request to create a new college.");
		List<College> existingCollegeList = collegeRepository.findAll();
		if(existingCollegeList.size() >= 1) {
			return 500;
		}
		int status = 400;
		College college = new College();
		college.collegeName(cmsCollegeVo.getCollegeName());
//        college.setInstructionInformation(cmsCollegeVo.getInstructionInformation());
        college = collegeRepository.save(college);
		try {
			String filePath = Paths.get("", applicationProperties.getImagePath()).toString();
//			if(cmsCollegeVo.getBgImage() != null) {
////				String filePath = Paths.get("", Constants.CMS_IMAGE_FILE_PATH+File.separator+college.getId()).toString();
//				college.setBackgroundImagePath(filePath);
//				String fileName = Constants.CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME;
//				String ext = base64FileProcessor.getFileExtensionFromBase64Srting(cmsCollegeVo.getBgImage().split(",")[0]);
//				college.setBackgroundImageFileName(Constants.CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME+"."+ext);
//				base64FileProcessor.createFileFromBase64String(cmsCollegeVo.getBgImage(), filePath, fileName, null);
//			}
			if(cmsCollegeVo.getLogoFile() != null) {
//				String filePath = Paths.get("", Constants.CMS_IMAGE_FILE_PATH+File.separator+college.getId()).toString();
				college.setLogoFilePath(filePath);
				String fileName = Constants.CMS_COLLEGE_LOGO_FILE_NAME;
				String ext = base64FileProcessor.getFileExtensionFromBase64Srting(cmsCollegeVo.getLogoFile().split(",")[0]);
				college.setLogoFileName(Constants.CMS_COLLEGE_LOGO_FILE_NAME+"."+ext);
				base64FileProcessor.createFileFromBase64String(cmsCollegeVo.getLogoFile(), filePath, fileName, null);
			}
			
	        logger.info("REST request to create a new college completed successfully.");
	        status = 200;
		}catch(Exception e) {
			logger.error("Exception while saving college data: ",e);
		}
		return status;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmscollege")
    public List<College> getAllColleges() {
		logger.debug("REST request to get all the college records.");
        return collegeRepository.findAll();
    }
	
	
}
