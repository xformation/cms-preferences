package com.synectiks.pref.domain.vo;

import com.synectiks.pref.domain.Branch;

import java.io.Serializable;
import java.time.LocalDate;

public class CmsFeeCategory extends CmsCommonVo implements Serializable {
    private Long id;
    private String categoryName;
    private String description;
    private String createdBy;
    private LocalDate createdOn;
    private String updatedBy;
    private LocalDate updatedOn;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long branchId;
    private Branch branch;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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

    @Override
    public String toString() {
        return "CmsFeeCategory{" +
            "id=" + id +
            ", categoryName='" + categoryName + '\'' +
            ", description='" + description + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", branchId=" + branchId +
            ", branch=" + branch +
            ",getStatus" + getStatus() +
            ", strCreatedOn='" + strCreatedOn + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            '}';
    }
}
