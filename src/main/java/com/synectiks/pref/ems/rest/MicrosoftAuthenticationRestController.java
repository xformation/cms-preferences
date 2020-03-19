package com.synectiks.pref.ems.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.synectiks.pref.config.ApplicationProperties;

/**
 * REST controller for managing AcademicYear.
 */
@RestController
@RequestMapping("/api")
public class MicrosoftAuthenticationRestController {

    private final Logger logger = LoggerFactory.getLogger(MicrosoftAuthenticationRestController.class);

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    RestTemplate restTemplate;
     
    @RequestMapping(method = RequestMethod.GET, value = "/cms-ms-authenticate")
    public String getMicrosoftAzureAccessToken(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get ms access token");
        String clientId = dataMap.get("CLIENT_ID");
        String clientSecrateKey = dataMap.get("CLIENT_SECRATE");
        String scope = dataMap.get("SCOPE");
        String tenantId = dataMap.get("TENANT_ID");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("client_id", clientId);
        params.add("scope", scope);
        params.add("client_secret", clientSecrateKey);
        params.add("grant_type", "client_credentials");
        
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        
        String url = applicationProperties.getMsAuthenticationUrl().replaceAll("TENANT_ID", tenantId);
        
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String accessToken = response.getBody();
        logger.trace("Access Token : "+accessToken);
        return accessToken;
    }
    
    

}
