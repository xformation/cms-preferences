package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Course Vo.
 */

public class CmsCourseVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private String courseDuration;
    private String courseType;
    private String yearOrSemesterType;
    private Long totalFee;
    private Long yearlyFee;
    private Long perSemesterFee;
    private String comments;
    private Long departmentId;
    private CmsDepartmentVo cmsDepartmentVo;
    private Long branchId;
    private CmsBranchVo cmsBranchVo;
    private List<CmsCourseVo> dataList = new ArrayList<CmsCourseVo>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getYearOrSemesterType() {
		return yearOrSemesterType;
	}
	public void setYearOrSemesterType(String yearOrSemesterType) {
		this.yearOrSemesterType = yearOrSemesterType;
	}
	public Long getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	public Long getYearlyFee() {
		return yearlyFee;
	}
	public void setYearlyFee(Long yearlyFee) {
		this.yearlyFee = yearlyFee;
	}
	public Long getPerSemesterFee() {
		return perSemesterFee;
	}
	public void setPerSemesterFee(Long perSemesterFee) {
		this.perSemesterFee = perSemesterFee;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public CmsDepartmentVo getCmsDepartmentVo() {
		return cmsDepartmentVo;
	}
	public void setCmsDepartmentVo(CmsDepartmentVo cmsDepartmentVo) {
		this.cmsDepartmentVo = cmsDepartmentVo;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		this.cmsBranchVo = cmsBranchVo;
	}
	public List<CmsCourseVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsCourseVo> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "CmsCourseVo [id=" + id + ", name=" + name + ", description=" + description + ", courseDuration="
				+ courseDuration + ", courseType=" + courseType + ", yearOrSemesterType=" + yearOrSemesterType
				+ ", totalFee=" + totalFee + ", yearlyFee=" + yearlyFee + ", perSemesterFee=" + perSemesterFee
				+ ", comments=" + comments + ", departmentId=" + departmentId + ", cmsDepartmentVo=" + cmsDepartmentVo
				+ ", branchId=" + branchId + ", cmsBranchVo=" + cmsBranchVo + ", dataList=" + dataList
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()="
				+ getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()=" + getStatus()
				+ ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
}
