package com.synectiks.pref.graphql.types.authorizedsignatory;

import com.synectiks.pref.domain.vo.CmsAuthorizedSignatoryVo;

public class AuthorizedSignatoryPayload {
    private final CmsAuthorizedSignatoryVo cmsAuthorizedSignatoryVo;

    public AuthorizedSignatoryPayload(CmsAuthorizedSignatoryVo authorizedSignatory){
        this.cmsAuthorizedSignatoryVo = authorizedSignatory;
    }

	public CmsAuthorizedSignatoryVo getCmsAuthorizedSignatoryVo() {
		return cmsAuthorizedSignatoryVo;
	}

    
}
