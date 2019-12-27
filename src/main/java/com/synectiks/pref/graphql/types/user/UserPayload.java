package com.synectiks.pref.graphql.types.user;

import com.synectiks.pref.domain.vo.CmsUserVo;

public class UserPayload {
    private final CmsUserVo cmsUserVo;

    public UserPayload(CmsUserVo cmsUserVo) {
        this.cmsUserVo = cmsUserVo;
    }

    public CmsUserVo getCmsUserVo() {
        return cmsUserVo;
    }
}
