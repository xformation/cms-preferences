package com.synectiks.pref.graphql.types.facility;

import com.synectiks.pref.domain.vo.CmsFacility;

public class FacilityPayload {
    private final CmsFacility cmsFacility;

    public FacilityPayload(CmsFacility cmsFacility) {
        this.cmsFacility = cmsFacility;
    }

    public CmsFacility getCmsFacility() {
        return cmsFacility;
    }
}
