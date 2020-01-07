package com.synectiks.pref.graphql.types.legalentity;

import com.synectiks.pref.domain.vo.CmsLegalEntityVo;

public class LegalEntityPayload {
    private final CmsLegalEntityVo cmsLegalEntityVo;

    public LegalEntityPayload(CmsLegalEntityVo cmsLegalEntityVo){
        this.cmsLegalEntityVo = cmsLegalEntityVo;
    }

	public CmsLegalEntityVo getLegalEntityVo() {
		return cmsLegalEntityVo;
	}

    
}
