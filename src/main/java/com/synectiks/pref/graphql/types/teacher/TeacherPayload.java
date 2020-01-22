package com.synectiks.pref.graphql.types.teacher;

import com.synectiks.pref.domain.vo.CmsTeacherVo;

public class TeacherPayload {
    private final CmsTeacherVo cmsTeacherVo;

    public TeacherPayload(CmsTeacherVo teacher){
        this.cmsTeacherVo = teacher;
    }

	public CmsTeacherVo getTeacherVo() {
		return cmsTeacherVo;
	}

    
}
