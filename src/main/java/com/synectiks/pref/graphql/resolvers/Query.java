package com.synectiks.pref.graphql.resolvers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.synectiks.pref.business.service.*;
import com.synectiks.pref.domain.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.State;
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

	@Autowired
    CmsCourseService cmsCourseService;

	@Autowired
    CmsEmployeeService cmsEmployeeService;

	@Autowired
    CmsTeacherService cmsTeacherService;

	@Autowired
    CmsSubjectService cmsSubjectService;

	@Autowired
    CmsBatchService cmsBatchService;

	@Autowired
    CmsSectionService cmsSectionService;

	@Autowired
    CmsFacilityService cmsFacilityService;

	public Query(UserPreferenceRepository userPreferenceRepository) {
		this.userPreferenceRepository = userPreferenceRepository;
	}

	public List<CmsBranchVo> getBranchList() throws Exception {
    	logger.debug("Query - getBranchList :");
    	return this.cmsBranchService.getCmsBranchList();
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
    	logger.debug("Query - getLegalEntityList :");
    	return this.cmsLegalEntityService.getLegalEntityList();
    }

	public List<CmsAcademicYearVo> getAcademicYearList() throws Exception {
    	logger.debug("Query - getAcademicYearList :");
    	return this.cmsAcademicYearService.getCmsAcademicYearList();
    }

	public List<CmsHolidayVo> getHolidayList() throws Exception {
    	logger.debug("Query - getHolidayList :");
    	return this.cmsHolidayService.getHolidayList();
    }

    public List<CmsFacility> getFacilityList() throws Exception {
        logger.debug("Query - getFacilityList :");
        return this.cmsFacilityService.getFacilityList();
    }

	public List<CmsTermVo> getTermList() throws Exception {
    	logger.debug("Query - getTermList :");
    	return this.cmsTermService.getCmsTermList();
    }

	public List<CmsDepartmentVo> getDepartmentList() throws Exception {
    	logger.debug("Query - getDepartmentList :");
    	return this.cmsDepartmentService.getCmsDepartmentList();
    }

	public List<CmsCourseVo> getCourseList() throws Exception {
    	logger.debug("Query - getCourseList :");
    	return this.cmsCourseService.getCourseList();
    }

	public List<CmsEmployeeVo> getEmployeeList() throws Exception {
    	logger.debug("Query - getEmployeeList :");
    	return this.cmsEmployeeService.getCmsEmployeeList();
    }

	public List<CmsTeacherVo> getTeacherList() throws Exception {
    	logger.debug("Query - getTeacherList :");

    	List<CmsTeacherVo> ls =  this.cmsTeacherService.getCmsTeacherList();
    	List<CmsEmployeeVo> evoList = cmsEmployeeService.getCmsEmployeeList();
    	for(CmsEmployeeVo tempEvo: evoList) {
    		if(CmsConstants.STAFF_TYPE_NONTEACHING.equalsIgnoreCase(tempEvo.getStaffType())) {
    			CmsTeacherVo tempTvo = this.cmsTeacherService.convertCmsEmployeeToCmsTeacher(tempEvo);
        		ls.add(tempTvo);
        	}
    	}
    	return ls;
    }

	public List<CmsSubjectVo> getSubjectList() throws Exception {
    	logger.debug("Query - getSubjectList :");
    	Map<String, String> m = new HashMap<String, String>();
    	return this.cmsSubjectService.getMergedSubjectAndAttendanceMasterList();// getCmsSubjectListOnFilterCriteria(m);
    }

	public List<CmsBatchVo> getBatchList() throws Exception {
    	logger.debug("Query - getBatchList :");
    	Map<String, String> m = new HashMap<String, String>();
    	return this.cmsBatchService.getCmsBatchListOnFilterCriteria(m);
    }

	public List<CmsSectionVo> getSectionList() throws Exception {
    	logger.debug("Query - getSectionList :");
    	Map<String, String> m = new HashMap<String, String>();
    	return this.cmsSectionService.getCmsSectionListOnFilterCriteria(m);
    }

	public List<String> getTableList() throws Exception {
    	logger.debug("Query - getTableList :");
    	return CmsConstants.TABLE_LIST;
    }

}
