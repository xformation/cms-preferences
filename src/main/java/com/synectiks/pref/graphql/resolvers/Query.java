/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.synectiks.pref.graphql.resolvers;

import com.synectiks.pref.business.service.CmsPermissionService;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.domain.vo.CmsPermissionVo;
import com.synectiks.pref.domain.vo.CmsRolesVo;
import com.synectiks.pref.domain.vo.CmsUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.pref.repository.UserPreferenceRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Query Resolver for preference queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);

	private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    CmsPermissionService cmsPermissionService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

	public Query(UserPreferenceRepository userPreferenceRepository) {
		this.userPreferenceRepository = userPreferenceRepository;
	}

	public Long getAbc() {
		return null;
	}

    public List<CmsPermissionVo> getPermissionList() throws Exception, Exception {
        logger.debug("Query - getRolesList :");
        String url = applicationProperties.getSecSrvUrl()+"/security/permissions/listAll";
        List<CmsPermissionVo> ls = new ArrayList<>();
        try {
            CmsPermissionVo[] temp = restTemplate.getForObject(url, CmsPermissionVo[].class);
            ls = Arrays.asList(temp);
        }catch(Exception e) {
            logger.error("Permission list could not be retrieved. Exception : ", e);
            throw e;
        }
        return ls;
    }

    public List<CmsRolesVo> getRolesList() throws Exception, Exception {
        logger.debug("Query - getRolesList :");
        String url = applicationProperties.getSecSrvUrl()+"/security/roles/listAll";
        List<CmsRolesVo> ls = new ArrayList<>();
        try {
            CmsRolesVo[] temp = restTemplate.getForObject(url, CmsRolesVo[].class);
            ls = Arrays.asList(temp);
        }catch(Exception e) {
            logger.error("Roles list could not be retrieved. Exception : ", e);
            throw e;
        }
        return ls;
    }

    public List<CmsUserVo> getUsersList() throws Exception, Exception {
        logger.debug("Query - getUsersList :");
        String url = applicationProperties.getSecSrvUrl()+"/security/users/listAll";
        List<CmsUserVo> ls = new ArrayList<>();
        try {
            CmsUserVo[] temp = restTemplate.getForObject(url, CmsUserVo[].class);
            ls = Arrays.asList(temp);
        }catch(Exception e) {
            logger.error("User list could not be retrieved. Exception : ", e);
            throw e;
        }
        return ls;
    }
}
