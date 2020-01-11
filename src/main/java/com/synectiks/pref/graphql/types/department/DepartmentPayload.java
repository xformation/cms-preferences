package com.synectiks.pref.graphql.types.department;

import com.synectiks.pref.domain.vo.CmsDepartmentVo;

public class DepartmentPayload {
    private final CmsDepartmentVo cmsDepartmentVo;

    public DepartmentPayload(CmsDepartmentVo department){
        this.cmsDepartmentVo = department;
    }

	public CmsDepartmentVo getDepartmentVo() {
		return cmsDepartmentVo;
	}

    
}
