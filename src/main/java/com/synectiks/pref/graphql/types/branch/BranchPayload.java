package com.synectiks.pref.graphql.types.branch;

import com.synectiks.pref.domain.vo.CmsBranchVo;

public class BranchPayload {
    private final CmsBranchVo cmsBranchVo;

    public BranchPayload(CmsBranchVo branch){
        this.cmsBranchVo = branch;
    }

	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}

    
}
