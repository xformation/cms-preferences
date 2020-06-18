package com.synectiks.pref.domain.vo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.enumeration.Status;


public class CmsNotificationsVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String messageCode;
    private String message;
    private String status;
    private String createdBy;
    private LocalDate createdOn;
    private String updatedBy;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate updatedOn;
    private String startTime;
    private String endTime;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private CmsAcademicYearVo cmsAcademicYearVo;
    private Long academicYearId;
    private String strCreatedOn;
    private String strUpdatedOn;
    private String strStartDate;
    private String strEndDate;
    private List<CmsNotificationsVo> dataList = new ArrayList<CmsNotificationsVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public CmsNotificationsVo messageCode(String messageCode) {
        this.messageCode = messageCode;
        return this;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public CmsNotificationsVo message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public CmsNotificationsVo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public CmsNotificationsVo createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public CmsNotificationsVo updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public CmsNotificationsVo updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }
    public String getStartTime() {
        return startTime;
    }

    public CmsNotificationsVo startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public CmsNotificationsVo endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public CmsNotificationsVo startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public CmsNotificationsVo endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public List<CmsNotificationsVo> getDataList() {
        return dataList;
    }
    public void setDataList(List<CmsNotificationsVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CmsNotificationsVo)) return false;
        CmsNotificationsVo that = (CmsNotificationsVo) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getMessageCode(), that.getMessageCode()) &&
            Objects.equals(getMessage(), that.getMessage()) &&
            getStatus() == that.getStatus() &&
            Objects.equals(getCreatedBy(), that.getCreatedBy()) &&
            Objects.equals(getCreatedOn(), that.getCreatedOn()) &&
            Objects.equals(getUpdatedBy(), that.getUpdatedBy()) &&
            Objects.equals(getUpdatedOn(), that.getUpdatedOn()) &&
            Objects.equals(getStartTime(), that.getStartTime()) &&
            Objects.equals(getEndTime(), that.getEndTime()) &&
            Objects.equals(getStartDate(), that.getStartDate()) &&
            Objects.equals(getEndDate(), that.getEndDate()) &&
            Objects.equals(cmsAcademicYearVo, that.cmsAcademicYearVo) &&
            Objects.equals(getAcademicYearId(), that.getAcademicYearId()) &&
            Objects.equals(getStrCreatedOn(), that.getStrCreatedOn()) &&
            Objects.equals(getStrUpdatedOn(), that.getStrUpdatedOn()) &&
            Objects.equals(getStrStartDate(), that.getStrStartDate()) &&
            Objects.equals(getStrEndDate(), that.getStrEndDate()) &&
            Objects.equals(getDataList(), that.getDataList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMessageCode(), getMessage(), getStatus(), getCreatedBy(), getCreatedOn(), getUpdatedBy(), getUpdatedOn(), getStartTime(), getEndTime(), getStartDate(), getEndDate(), cmsAcademicYearVo, getAcademicYearId(), getStrCreatedOn(), getStrUpdatedOn(), getStrStartDate(), getStrEndDate(), getDataList());
    }

    @Override
    public String toString() {
        return "CmsNotificationsVo{" +
            "id=" + id +
            ", messageCode='" + messageCode + '\'' +
            ", message='" + message + '\'' +
            ", status=" + status +
            ", createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", cmsAcademicYearVo=" + cmsAcademicYearVo +
            ", academicYearId=" + academicYearId +
            ", strCreatedOn='" + strCreatedOn + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            ", dataList=" + dataList +

            '}';
    }


    public String getStrCreatedOn() {
		return strCreatedOn;
	}

	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}

	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}

	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
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
}
