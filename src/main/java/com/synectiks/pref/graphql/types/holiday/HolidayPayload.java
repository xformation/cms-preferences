package com.synectiks.pref.graphql.types.holiday;

import com.synectiks.pref.domain.vo.CmsHolidayVo;

public class HolidayPayload {
    private final CmsHolidayVo cmsHolidayVo;

    public HolidayPayload(CmsHolidayVo holiday){
        this.cmsHolidayVo = holiday;
    }

	public CmsHolidayVo getCmsHolidayVo() {
		return cmsHolidayVo;
	}

    
}
