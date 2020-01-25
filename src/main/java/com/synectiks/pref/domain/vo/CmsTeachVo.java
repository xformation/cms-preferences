package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsTeachVo extends CmsCommonVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String desc;
    private CmsSubjectVo cmsSubjectVo;
    private CmsTeacherVo cmsTeacherVo;
    private Long subjectId;
    private Long teacherId;
    private List<CmsTeachVo> dataList = new ArrayList<CmsTeachVo>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public CmsSubjectVo getCmsSubjectVo() {
		return cmsSubjectVo;
	}
	public void setCmsSubjectVo(CmsSubjectVo cmsSubjectVo) {
		this.cmsSubjectVo = cmsSubjectVo;
	}
	public CmsTeacherVo getCmsTeacherVo() {
		return cmsTeacherVo;
	}
	public void setCmsTeacherVo(CmsTeacherVo cmsTeacherVo) {
		this.cmsTeacherVo = cmsTeacherVo;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public List<CmsTeachVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsTeachVo> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "CmsTeachVo [id=" + id + ", desc=" + desc + ", cmsSubjectVo=" + cmsSubjectVo + ", cmsTeacherVo="
				+ cmsTeacherVo + ", subjectId=" + subjectId + ", teacherId=" + teacherId + ", dataList=" + dataList
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()="
				+ getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()=" + getStatus()
				+ ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
    
	
}
