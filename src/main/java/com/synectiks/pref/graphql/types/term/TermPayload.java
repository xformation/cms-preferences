package com.synectiks.pref.graphql.types.term;

import com.synectiks.pref.domain.vo.CmsTermVo;

public class TermPayload {
    private final CmsTermVo cmsTermVo;

    public TermPayload(CmsTermVo term){
        this.cmsTermVo = term;
    }

	public CmsTermVo getTermVo() {
		return cmsTermVo;
	}

    
}
