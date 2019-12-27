package com.synectiks.pref.business.service;

import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.config.Constants;
import com.synectiks.pref.domain.vo.CmsUserVo;
import com.synectiks.pref.graphql.types.user.UserInput;
import com.synectiks.pref.graphql.types.user.UserPayload;
import com.synectiks.pref.service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class CmsUserService {
    private final static Logger logger = LoggerFactory.getLogger(CmsUserService.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    public UserPayload saveUser(UserInput input) throws Exception {
        if (input.getId() == null) {
            return addUser(input);
        } else {
            return updateUser(input);
        }
    }

    public UserPayload addUser(UserInput input) throws Exception {
        UserInput inp = CommonUtil.createCopyProperties(input, UserInput.class);

        CmsUserVo vo= new CmsUserVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/users/create";
        try {
            vo = restTemplate.postForObject(url, inp, CmsUserVo.class);
        }catch(Exception e) {
            logger.error("User record could not be saved. Exception : ", e);
            throw e;
        }
        logger.info("User  data : "+vo);
        return new UserPayload(vo);
    }

    public UserPayload updateUser(UserInput input) throws Exception {
        UserInput inp = CommonUtil.createCopyProperties(input, UserInput.class);
        CmsUserVo vo= new CmsUserVo();
        String url = applicationProperties.getSecSrvUrl()+"/security/users/update";
        try {
            restTemplate.put(url,inp);
        }catch(Exception e) {
            logger.error("User record could not be updated. Exception : ", e);
            throw e;
        }
        logger.info("roles  data : "+vo);
        return new UserPayload(vo);
    }

}
