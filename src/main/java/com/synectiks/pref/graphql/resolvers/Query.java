package com.synectiks.pref.graphql.resolvers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.pref.business.service.CmsAcademicYearService;
import com.synectiks.pref.business.service.CmsAuthorizedSignatoryService;
import com.synectiks.pref.business.service.CmsBankAccountsService;
import com.synectiks.pref.business.service.CmsBranchService;
import com.synectiks.pref.business.service.CmsCityService;
import com.synectiks.pref.business.service.CmsDepartmentService;
import com.synectiks.pref.business.service.CmsHolidayService;
import com.synectiks.pref.business.service.CmsLegalEntityService;
import com.synectiks.pref.business.service.CmsStateService;
import com.synectiks.pref.business.service.CmsTermService;
import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.State;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsAuthorizedSignatoryVo;
import com.synectiks.pref.domain.vo.CmsBankAccountsVo;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.domain.vo.CmsHolidayVo;
import com.synectiks.pref.domain.vo.CmsLegalEntityVo;
import com.synectiks.pref.domain.vo.CmsTermVo;
import com.synectiks.pref.repository.UserPreferenceRepository;

/**
 * Query Resolver for preference queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);
    
	private UserPreferenceRepository userPreferenceRepository;
    
	@Autowired
	CmsBranchService cmsBranchService;
	
	@Autowired
	CmsStateService cmsStateService;
	
	@Autowired
	CmsCityService cmsCityService;
	
	@Autowired
    CmsAuthorizedSignatoryService cmsAuthorizedSignatoryService;
	
	@Autowired
    CmsBankAccountsService cmsBankAccountsService;
	
	@Autowired
    CmsLegalEntityService cmsLegalEntityService;
	
	@Autowired
    CmsAcademicYearService cmsAcademicYearService;
	
	@Autowired
    CmsHolidayService cmsHolidayService;
	
	@Autowired
    CmsTermService cmsTermService;
	
	@Autowired
    CmsDepartmentService cmsDepartmentService;
	
	public Query(UserPreferenceRepository userPreferenceRepository) {
		this.userPreferenceRepository = userPreferenceRepository;
	}
    
	public List<CmsBranchVo> getBranchList() throws Exception {
    	logger.debug("Query - getBranchList :");
    	return this.cmsBranchService.getBranchList();
    }
	
	public List<State> getStateList() throws Exception {
    	logger.debug("Query - getStateList :");
    	return this.cmsStateService.getStateList();
    }
	
	public List<City> getCityList() throws Exception {
    	logger.debug("Query - getCityList :");
    	return this.cmsCityService.getCityList();
    }
	
	public List<CmsAuthorizedSignatoryVo> getAuthorizedSignatoryList() throws Exception {
    	logger.debug("Query - getAuthorizedSignatoryList :");
    	return this.cmsAuthorizedSignatoryService.getAuthorizedSignatoryList();
    }
	
	public List<CmsBankAccountsVo> getBankAccountsList() throws Exception {
    	logger.debug("Query - getBankAccountsList :");
    	return this.cmsBankAccountsService.getBankAccountsList();
    }
	
	public List<CmsLegalEntityVo> getLegalEntityList() throws Exception {
    	logger.debug("Query - getLegalEntity :");
    	return this.cmsLegalEntityService.getLegalEntityList();
    }
	
	public List<CmsAcademicYearVo> getAcademicYearList() throws Exception {
    	logger.debug("Query - getAcademicYear :");
    	return this.cmsAcademicYearService.getAcademicYearList();
    }
	
	public List<CmsHolidayVo> getHolidayList() throws Exception {
    	logger.debug("Query - getHoliday :");
    	return this.cmsHolidayService.getHolidayList();
    }
	
	public List<CmsTermVo> getTermList() throws Exception {
    	logger.debug("Query - getTerm :");
    	return this.cmsTermService.getTermList();
    }
	
	public List<CmsDepartmentVo> getDepartmentList() throws Exception {
    	logger.debug("Query - getDepartment :");
    	return this.cmsDepartmentService.getDepartmentList();
    }
}
