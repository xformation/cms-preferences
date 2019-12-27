package com.synectiks.pref.business.service;

import javax.transaction.Transactional;

import com.synectiks.pref.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synectiks.pref.domain.vo.CmsPermissionVo;
import com.synectiks.pref.graphql.types.permission.PermissionInput;
import com.synectiks.pref.graphql.types.permission.PermissionPayload;
import com.synectiks.pref.service.util.CommonUtil;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class CmsPermissionService {

    private final static Logger logger = LoggerFactory.getLogger(CmsPermissionService.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    public PermissionPayload savePermission(PermissionInput input) throws Exception {
        if (input.getId() == null) {
            return addPermission(input);
        } else {
            return updatePermission(input);
        }
    }

    public PermissionPayload addPermission(PermissionInput input) throws Exception {
        PermissionInput inp = CommonUtil.createCopyProperties(input, PermissionInput.class);
        CmsPermissionVo vo= new CmsPermissionVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/permissions/create";
        try {
            vo = restTemplate.postForObject(url, inp, CmsPermissionVo.class);
        }catch(Exception e) {
            logger.error("Permission record could not be saved. Exception : ", e);
            throw e;
        }
        logger.info("Permission  data : "+vo);
        return new PermissionPayload(vo);
    }

    public PermissionPayload updatePermission(PermissionInput input) throws Exception {
        PermissionInput inp = CommonUtil.createCopyProperties(input, PermissionInput.class);
        CmsPermissionVo vo= new CmsPermissionVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/permissions/update";
        try {
            restTemplate.put(url,inp);
        }catch(Exception e) {
            logger.error("Permission record could not be updated. Exception : ", e);
            throw e;
        }
        logger.info("Permission  data : "+vo);
        return new PermissionPayload(vo);
    }


    public PermissionPayload deletePermission(Long permissionId) {
        logger.info("Start deleting permission record");
        CmsPermissionVo vo= new CmsPermissionVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/permissions/delete/{id}";
        try {
            restTemplate.delete(url,permissionId);
        }catch(Exception e) {
            logger.error("Permission record could not be deleted. Exception : ", e);
        }
        logger.info("Permission record deleted successfully");
        return new PermissionPayload (vo);
    }

}


