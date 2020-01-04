package com.synectiks.pref.graphql.resolvers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.pref.business.service.CmsAuthorizedSignatoryService;
import com.synectiks.pref.business.service.CmsBranchService;
import com.synectiks.pref.business.service.CmsCollegeService;
import com.synectiks.pref.domain.vo.CmsAuthorizedSignatoryVo;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
import com.synectiks.pref.graphql.types.authorizedsignatory.AuthorizedSignatoryInput;
import com.synectiks.pref.graphql.types.authorizedsignatory.AuthorizedSignatoryPayload;
import com.synectiks.pref.graphql.types.branch.BranchInput;
import com.synectiks.pref.graphql.types.branch.BranchPayload;
import com.synectiks.pref.graphql.types.college.CollegeInput;
import com.synectiks.pref.graphql.types.college.CollegePayload;
import com.synectiks.pref.repository.UserPreferenceRepository;



@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private UserPreferenceRepository userPreferenceRepository;
    
    @Autowired
    CmsCollegeService cmsCollegeService;
    
    @Autowired
    CmsBranchService cmsBranchService;
    
    @Autowired
    CmsAuthorizedSignatoryService cmsAuthorizedSignatoryService;
    
    
    public Mutation(UserPreferenceRepository userPreferenceRepository) {
    	this.userPreferenceRepository = userPreferenceRepository;
    }
    
    public CollegePayload addCollege(CollegeInput cmsCollegeVo) {
    	CmsCollegeVo vo = this.cmsCollegeService.addCollege(cmsCollegeVo);
    	return new CollegePayload(vo);
    }

    public BranchPayload saveBranch(BranchInput cmsBranchVo) {
    	CmsBranchVo vo = this.cmsBranchService.saveBranch(cmsBranchVo);
    	return new BranchPayload(vo);
    }
    
    public AuthorizedSignatoryPayload saveAuthorizedSignatory(AuthorizedSignatoryInput cmsAuthorizedSignatoryVo) {
    	CmsAuthorizedSignatoryVo vo = this.cmsAuthorizedSignatoryService.saveAuthorizedSignatory(cmsAuthorizedSignatoryVo);
    	return new AuthorizedSignatoryPayload(vo);
    }
}
