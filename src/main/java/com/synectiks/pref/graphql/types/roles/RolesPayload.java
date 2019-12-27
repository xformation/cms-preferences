package com.synectiks.pref.graphql.types.roles;

import com.synectiks.pref.domain.vo.CmsRolesVo;

public class RolesPayload {
    private final CmsRolesVo cmsRolesVo;

    public RolesPayload(CmsRolesVo cmsRolesVo) {
        this.cmsRolesVo = cmsRolesVo;
    }

    public CmsRolesVo getCmsRolesVo() {
        return cmsRolesVo;
    }
}
