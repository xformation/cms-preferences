package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CmsHolidayVo extends CmsCommonVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String description;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate holidayDate;
    
    private String comments;
    private CmsAcademicYearVo cmsAcademicYearVo;
    private Long academicYearId;
    private List<CmsHolidayVo> dataList = new ArrayList<CmsHolidayVo>();
    private String strHolidayDate;
    
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
	public LocalDate getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public CmsAcademicYearVo getCmsAcademicYearVo() {
		return cmsAcademicYearVo;
	}
	public void setCmsAcademicYearVo(CmsAcademicYearVo cmsAcademicYearVo) {
		this.cmsAcademicYearVo = cmsAcademicYearVo;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public List<CmsHolidayVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsHolidayVo> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "CmsHolidayVo [id=" + id + ", description=" + description + ", holidayDate=" + holidayDate
				+ ", comments=" + comments + ", cmsAcademicYearVo=" + cmsAcademicYearVo + ", academicYearId="
				+ academicYearId + ", dataList=" + dataList + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()="
				+ getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn()
				+ ", getStatus()=" + getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()="
				+ getStrUpdatedOn() + ", getExitCode()=" + getExitCode() + ", getExitDescription()="
				+ getExitDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public String getStrHolidayDate() {
		return strHolidayDate;
	}
	public void setStrHolidayDate(String strHolidayDate) {
		this.strHolidayDate = strHolidayDate;
	}
    
}
