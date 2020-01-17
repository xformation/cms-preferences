package com.synectiks.pref.graphql.types.employee;

import com.synectiks.pref.domain.vo.CmsEmployeeVo;

public class EmployeePayload {
    private final CmsEmployeeVo cmsEmployeeVo;

    public EmployeePayload(CmsEmployeeVo cmsEmployeeVo){
        this.cmsEmployeeVo = cmsEmployeeVo;
    }

	public CmsEmployeeVo getEmployeeVo() {
		return cmsEmployeeVo;
	}
    
}
