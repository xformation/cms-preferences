package com.synectiks.pref.domain.vo;

import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.enumeration.Gender;
import com.synectiks.pref.domain.enumeration.StudentTypeEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class CmsFeeDetails extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String feeParticularsName;
    private String feeParticularDesc;
    private StudentTypeEnum studentType;
    private Gender gender;
    private Float amount;
    private String createdBy;
    private LocalDate createdOn;
    private String updatedBy;
    private LocalDate updatedOn;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long batchId;
    private Long departmentId;
    private Batch batch;
    private Department department;
    private CmsFacility cmsFacility;
    private CmsTransportVo cmsTransportVo;
    private CmsFeeCategory cmsFeeCategory;

    private String strCreatedOn;
    private String strUpdatedOn;
    private String strStartDate;
    private String strEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeParticularsName() {
        return feeParticularsName;
    }

    public void setFeeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
    }

    public String getFeeParticularDesc() {
        return feeParticularDesc;
    }

    public void setFeeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
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

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public CmsFacility getCmsFacility() {
        return cmsFacility;
    }

    public void setCmsFacility(CmsFacility cmsFacility) {
        this.cmsFacility = cmsFacility;
    }

    public CmsTransportVo getCmsTransportVo() {
        return cmsTransportVo;
    }

    public void setCmsTransportVo(CmsTransportVo cmsTransportVo) {
        this.cmsTransportVo = cmsTransportVo;
    }

    public CmsFeeCategory getCmsFeeCategory() {
        return cmsFeeCategory;
    }

    public void setCmsFeeCategory(CmsFeeCategory cmsFeeCategory) {
        this.cmsFeeCategory = cmsFeeCategory;
    }

    @Override
    public String getStrCreatedOn() {
        return strCreatedOn;
    }

    @Override
    public void setStrCreatedOn(String strCreatedOn) {
        this.strCreatedOn = strCreatedOn;
    }

    @Override
    public String getStrUpdatedOn() {
        return strUpdatedOn;
    }

    @Override
    public void setStrUpdatedOn(String strUpdatedOn) {
        this.strUpdatedOn = strUpdatedOn;
    }

    @Override
    public String toString() {
        return "CmsFeeDetails{" +
            "id=" + id +
            ", feeParticularsName='" + feeParticularsName + '\'' +
            ", feeParticularDesc='" + feeParticularDesc + '\'' +
            ", studentType=" + studentType +
            ", gender=" + gender +
            ", amount=" + amount +
            ", createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", batchId=" + batchId +
            ", departmentId=" + departmentId +
            ", batch=" + batch +
            ", department=" + department +
            ", cmsFacility=" + cmsFacility +
            ", cmsTransportVo=" + cmsTransportVo +
            ", cmsFeeCategory=" + cmsFeeCategory +
            ", strCreatedOn='" + strCreatedOn + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            '}';
    }
}
