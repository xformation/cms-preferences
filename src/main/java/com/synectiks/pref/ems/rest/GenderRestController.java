package com.synectiks.pref.ems.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.config.Constants;

/**
 * REST controller to get Gender.
 */
@RestController
@RequestMapping("/api")
public class GenderRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET, value = "/cmsgender")
    public List<String> getGenderList() {
		logger.debug("REST request to get gender list.");
		return Constants.GENDER_LIST;
    }
	
}
