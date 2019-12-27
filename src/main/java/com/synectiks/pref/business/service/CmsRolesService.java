package com.synectiks.pref.business.service;

import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.domain.vo.CmsRolesVo;
import com.synectiks.pref.graphql.types.roles.RolesInput;
import com.synectiks.pref.graphql.types.roles.RolesPayload;
import com.synectiks.pref.service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;

@Service
@Transactional
public class CmsRolesService {
    private final static Logger logger = LoggerFactory.getLogger(CmsRolesService.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    public RolesPayload saveRoles(RolesInput input) throws Exception {
        if (input.getId() == null) {
            return addRole(input);
        } else {
            return updateRoles(input);
        }
    }

    public RolesPayload addRole(RolesInput input) throws Exception {
        RolesInput inp = CommonUtil.createCopyProperties(input, RolesInput.class);
        CmsRolesVo vo= new CmsRolesVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/roles/create";
        try {
            vo = restTemplate.postForObject(url, inp, CmsRolesVo.class);
        }catch(Exception e) {
            logger.error("Roles record could not be saved. Exception : ", e);
            throw e;
        }
        logger.info("Roles  data : "+vo);
        return new RolesPayload(vo);
    }

    public RolesPayload updateRoles(RolesInput input) throws Exception {
        RolesInput inp = CommonUtil.createCopyProperties(input, RolesInput.class);
        CmsRolesVo vo= new CmsRolesVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/roles/update";
        try {
            restTemplate.put(url,inp);
        }catch(Exception e) {
            logger.error("Roles record could not be updated. Exception : ", e);
            throw e;
        }
        logger.info("roles  data : "+vo);
        return new RolesPayload(vo);
    }

}
