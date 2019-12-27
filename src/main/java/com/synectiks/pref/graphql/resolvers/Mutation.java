package com.synectiks.pref.graphql.resolvers;

import com.synectiks.pref.business.service.CmsPermissionService;
import com.synectiks.pref.business.service.CmsRolesService;
import com.synectiks.pref.business.service.CmsUserService;
import com.synectiks.pref.graphql.types.permission.PermissionInput;
import com.synectiks.pref.graphql.types.permission.PermissionPayload;
import com.synectiks.pref.graphql.types.roles.RolesInput;
import com.synectiks.pref.graphql.types.roles.RolesPayload;
import com.synectiks.pref.graphql.types.user.UserInput;
import com.synectiks.pref.graphql.types.user.UserPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.pref.business.service.CmsCollegeService;
import com.synectiks.pref.domain.vo.CmsCollegeVo;
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
    CmsPermissionService cmsPermissionService;

    @Autowired
    CmsRolesService cmsRolesService;

    @Autowired
    CmsUserService cmsUserService;

    public Mutation(UserPreferenceRepository userPreferenceRepository) {
    	this.userPreferenceRepository = userPreferenceRepository;
    }

    public CollegePayload addCollege(CollegeInput cmsCollegeVo) {
    	CmsCollegeVo vo = this.cmsCollegeService.addCollege(cmsCollegeVo);
    	return new CollegePayload(vo);
    }


    public PermissionPayload savePermission(PermissionInput permissionInput) throws Exception {
        logger.debug("Mutation - savePermission() - permissionInput : "+permissionInput);
        return cmsPermissionService.savePermission(permissionInput);
    }

    public RolesPayload saveRoles(RolesInput rolesInput) throws Exception {
        logger.debug("Mutation - saveRoles() - rolesInput : "+rolesInput);
        return cmsRolesService.saveRoles(rolesInput);
    }

    public UserPayload saveUser(UserInput userInput) throws Exception {
        logger.debug("Mutation - saveUser() - userInput : "+userInput);
        return cmsUserService.saveUser(userInput);
    }

    public PermissionPayload deletePermission(Long permissionId) throws Exception {
        logger.debug("Mutation - savePermission() - permissionId : "+permissionId);
        return cmsPermissionService.deletePermission(permissionId);
    }


}
