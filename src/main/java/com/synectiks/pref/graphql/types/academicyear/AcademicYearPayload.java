package com.synectiks.pref.graphql.types.academicyear;

import com.synectiks.pref.domain.vo.CmsAcademicYearVo;

public class AcademicYearPayload {
    private final CmsAcademicYearVo cmsAcademicYearVo;

    public AcademicYearPayload(CmsAcademicYearVo academicYear){
        this.cmsAcademicYearVo = academicYear;
    }

	public CmsAcademicYearVo getCmsAcademicYearVo() {
		return cmsAcademicYearVo;
	}

    
}
