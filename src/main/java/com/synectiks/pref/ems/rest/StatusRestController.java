package com.synectiks.pref.ems.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;

/**
 * REST controller to get Status.
 */
@RestController
@RequestMapping("/api")
public class StatusRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET, value = "/cmsstatus")
    public List<String> getStatusList() {
		logger.debug("REST request to get status list.");
		return CmsConstants.STATUS_LIST;
    }
	
}
