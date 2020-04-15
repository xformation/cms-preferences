package com.synectiks.pref.domain.vo;

import com.synectiks.pref.domain.enumeration.RouteFrequency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Vo for the AcademicYear entity.
 */
public class CmsTransportVo extends CmsCommonVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 533914446871927557L;
    private Long id;
    private String routeName;
    private String routeDetails;
    private String routeMapUrl;
    private Integer noOfStops;
    private String routeFrequency;
    private List<CmsTransportVo> dataList = new ArrayList<CmsTransportVo>();

    private String strNoOfStops;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public String getRouteMapUrl() {
        return routeMapUrl;
    }

    public void setRouteMapUrl(String routeMapUrl) {
        this.routeMapUrl = routeMapUrl;
    }

    public Integer getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(Integer noOfStops) {
        this.noOfStops = noOfStops;
    }

    public String getRouteFrequency() {
        return routeFrequency;
    }

    public void setRouteFrequency(String routeFrequency) {
        this.routeFrequency = routeFrequency;
    }

    public List<CmsTransportVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsTransportVo> dataList) {
        this.dataList = dataList;
    }

    public String getStrNoOfStops() {
        return strNoOfStops;
    }

    public void setStrNoOfStops(String strNoOfStops) {
        this.strNoOfStops = strNoOfStops;
    }

    @Override
    public String toString() {
        return "CmsTransportVo{" +
            "id=" + id +
            ", routeName='" + routeName + '\'' +
            ", routeDetails='" + routeDetails + '\'' +
            ", routeMapUrl='" + routeMapUrl + '\'' +
            ", noOfStops=" + noOfStops +
            ", routeFrequency=" + routeFrequency +
            ", dataList=" + dataList +
            ", strNoOfStops='" + strNoOfStops + '\'' +
            '}';
    }
}
