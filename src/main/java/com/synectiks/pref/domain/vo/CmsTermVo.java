package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CmsTermVo extends CmsCommonVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String description;
    private String termsDesc;

    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    private String comments;
    private String strStartDate;
    private String strEndDate;
    private Long academicYearId;
    private CmsAcademicYearVo cmsAcademicYearVo;
    private List<CmsTermVo> dataList = new ArrayList<CmsTermVo>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStrStartDate() {
		return strStartDate;
	}
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}
	public String getStrEndDate() {
		return strEndDate;
	}
	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public CmsAcademicYearVo getCmsAcademicYearVo() {
		return cmsAcademicYearVo;
	}
	public void setCmsAcademicYearVo(CmsAcademicYearVo cmsAcademicYearVo) {
		this.cmsAcademicYearVo = cmsAcademicYearVo;
	}
	public List<CmsTermVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsTermVo> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "CmsTermVo [id=" + id + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", comments=" + comments + ", strStartDate=" + strStartDate + ", strEndDate=" + strEndDate
				+ ", academicYearId=" + academicYearId + ", cmsAcademicYearVo=" + cmsAcademicYearVo + ", dataList="
				+ dataList + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()="
				+ getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public String getTermsDesc() {
		return termsDesc;
	}
	public void setTermsDesc(String termsDesc) {
		this.termsDesc = termsDesc;
	}
}
