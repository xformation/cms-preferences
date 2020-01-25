package com.synectiks.pref.graphql.types.subject;

import com.synectiks.pref.domain.vo.CmsSubjectVo;

public class SubjectPayload {
    private final CmsSubjectVo cmsSubjectVo;

    public SubjectPayload(CmsSubjectVo subject){
        this.cmsSubjectVo = subject;
    }

	public CmsSubjectVo getSubjectVo() {
		return cmsSubjectVo;
	}

    
}
