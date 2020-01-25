package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Vo for the Subject, Teacher and Teach entity.
 */

public class CmsSubjectVo extends CmsCommonVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String subjectCode;
    private String subjectType;
    private String subjectDesc;
    
    
    private Long departmentId;
    private Long subjectId;
    private Long teacherId;
    private Long batchId;
    private Long sectionId;
    private Long teachId;
    private Long attendanceMasterId;
    
    
    private CmsDepartmentVo cmsDepartmentVo;
    private CmsTeacherVo cmsTeacherVo;
    private CmsBatchVo cmsBatchVo;
    private CmsSectionVo cmsSectionVo; 
    private CmsTeachVo cmsTeachVo;
    private CmsAttendanceMasterVo cmsAttendanceMasterVo;
    
    private List<CmsSubjectVo> dataList = new ArrayList<CmsSubjectVo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public CmsDepartmentVo getCmsDepartmentVo() {
		return cmsDepartmentVo;
	}

	public void setCmsDepartmentVo(CmsDepartmentVo cmsDepartmentVo) {
		this.cmsDepartmentVo = cmsDepartmentVo;
	}

	public CmsBatchVo getCmsBatchVo() {
		return cmsBatchVo;
	}

	public void setCmsBatchVo(CmsBatchVo cmsBatchVo) {
		this.cmsBatchVo = cmsBatchVo;
	}

	public List<CmsSubjectVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<CmsSubjectVo> dataList) {
		this.dataList = dataList;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getTeachId() {
		return teachId;
	}

	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}

	public Long getAttendanceMasterId() {
		return attendanceMasterId;
	}

	public void setAttendanceMasterId(Long attendanceMasterId) {
		this.attendanceMasterId = attendanceMasterId;
	}

	public CmsTeacherVo getCmsTeacherVo() {
		return cmsTeacherVo;
	}

	public void setCmsTeacherVo(CmsTeacherVo cmsTeacherVo) {
		this.cmsTeacherVo = cmsTeacherVo;
	}

	public CmsSectionVo getCmsSectionVo() {
		return cmsSectionVo;
	}

	public void setCmsSectionVo(CmsSectionVo cmsSectionVo) {
		this.cmsSectionVo = cmsSectionVo;
	}

	public CmsTeachVo getCmsTeachVo() {
		return cmsTeachVo;
	}

	public void setCmsTeachVo(CmsTeachVo cmsTeachVo) {
		this.cmsTeachVo = cmsTeachVo;
	}

	public CmsAttendanceMasterVo getCmsAttendanceMasterVo() {
		return cmsAttendanceMasterVo;
	}

	public void setCmsAttendanceMasterVo(CmsAttendanceMasterVo cmsAttendanceMasterVo) {
		this.cmsAttendanceMasterVo = cmsAttendanceMasterVo;
	}

	@Override
	public String toString() {
		return "CmsSubjectVo [id=" + id + ", subjectCode=" + subjectCode + ", subjectType=" + subjectType
				+ ", subjectDesc=" + subjectDesc + ", departmentId=" + departmentId + ", teacherId=" + teacherId
				+ ", batchId=" + batchId + ", sectionId=" + sectionId + ", teachId=" + teachId + ", attendanceMasterId="
				+ attendanceMasterId + ", cmsDepartmentVo=" + cmsDepartmentVo + ", cmsTeacherVo=" + cmsTeacherVo
				+ ", cmsBatchVo=" + cmsBatchVo + ", cmsSectionVo=" + cmsSectionVo + ", cmsTeachVo=" + cmsTeachVo
				+ ", cmsAttendanceMasterVo=" + cmsAttendanceMasterVo + ", dataList=" + dataList + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()=" + getStatus() + ", getStrCreatedOn()="
				+ getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
    

    
    
}
