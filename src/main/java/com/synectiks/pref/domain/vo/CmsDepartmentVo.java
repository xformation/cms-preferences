package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsDepartmentVo extends CmsCommonVo implements Serializable {


    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String description;
    private String deptHead;
    private String comments;
    private Long branchId;
    private Long academicYearId;
    private CmsBranchVo cmsBranchVo;
    private CmsAcademicYearVo cmsAcademicYearVo;
    private List<CmsDepartmentVo> dataList = new ArrayList<CmsDepartmentVo>();
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
	public String getDeptHead() {
		return deptHead;
	}
	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		this.cmsBranchVo = cmsBranchVo;
	}
	public CmsAcademicYearVo getCmsAcademicYearVo() {
		return cmsAcademicYearVo;
	}
	public void setCmsAcademicYearVo(CmsAcademicYearVo cmsAcademicYearVo) {
		this.cmsAcademicYearVo = cmsAcademicYearVo;
	}
	public List<CmsDepartmentVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsDepartmentVo> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "CmsDepartmentVo [id=" + id + ", name=" + name + ", description=" + description + ", deptHead="
				+ deptHead + ", comments=" + comments + ", branchId=" + branchId + ", academicyearId=" + academicYearId
				+ ", cmsBranchVo=" + cmsBranchVo + ", cmsAcademicYearVo=" + cmsAcademicYearVo + ", dataList=" + dataList
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()="
				+ getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()=" + getStatus()
				+ ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
    
}
