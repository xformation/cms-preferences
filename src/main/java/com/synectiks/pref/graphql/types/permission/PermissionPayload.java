package com.synectiks.pref.graphql.types.permission;

import com.synectiks.pref.domain.vo.CmsPermissionVo;

public class PermissionPayload {
    private final CmsPermissionVo cmsPermissionVo;

    public PermissionPayload(CmsPermissionVo cmsPermissionVo) {
        this.cmsPermissionVo = cmsPermissionVo;
    }

    public CmsPermissionVo getCmsPermissionVo() {
        return cmsPermissionVo;
    }
}
