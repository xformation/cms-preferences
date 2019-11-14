package com.synectiks.pref.graphql.types.college;

import com.synectiks.pref.domain.vo.CmsCollegeVo;

public class CollegePayload {
    private final CmsCollegeVo cmsCollegeVo;

    public CollegePayload(CmsCollegeVo college){
        this.cmsCollegeVo = college;
    }

	public CmsCollegeVo getCmsCollegeVo() {
		return cmsCollegeVo;
	}

    
}
