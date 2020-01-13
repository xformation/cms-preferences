package com.synectiks.pref.graphql.types.course;

import com.synectiks.pref.domain.vo.CmsCourseVo;

public class CoursePayload {
    private final CmsCourseVo cmsCourseVo;

    public CoursePayload(CmsCourseVo course){
        this.cmsCourseVo = course;
    }

	public CmsCourseVo getCmsCourseVo() {
		return cmsCourseVo;
	}

    
}
