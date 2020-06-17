package com.synectiks.pref.graphql.types.notifications;

import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsNotificationsVo;

public class NotificationsPayload {
    private final CmsNotificationsVo cmsNotificationsVo;

    public NotificationsPayload(CmsNotificationsVo notifications){
        this.cmsNotificationsVo = notifications;
    }

    public CmsNotificationsVo getCmsNotificationsVo() {
        return cmsNotificationsVo;
    }

}
