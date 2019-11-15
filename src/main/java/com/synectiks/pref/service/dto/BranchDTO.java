package com.synectiks.pref.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.pref.domain.Branch} entity.
 */
public class BranchDTO implements Serializable {

    private Long id;

    private String branchName;

    private String address;

    private String pinCode;

    private String branchHead;

    private String cellPhoneNo;

    private String landLinePhoneNo;

    private String emailId;

    private String faxNo;

    @Size(max = 3)
    private String isMainBranch;

    private LocalDate startDate;

    private String createdBy;

    private LocalDate createdOn;

    private String updatedBy;

    private LocalDate updatedOn;

    private String status;


    private Long collegeId;

    private Long cityId;

    private Long stateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getBranchHead() {
        return branchHead;
    }

    public void setBranchHead(String branchHead) {
        this.branchHead = branchHead;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getLandLinePhoneNo() {
        return landLinePhoneNo;
    }

    public void setLandLinePhoneNo(String landLinePhoneNo) {
        this.landLinePhoneNo = landLinePhoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getIsMainBranch() {
        return isMainBranch;
    }

    public void setIsMainBranch(String isMainBranch) {
        this.isMainBranch = isMainBranch;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BranchDTO branchDTO = (BranchDTO) o;
        if (branchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), branchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
            "id=" + getId() +
            ", branchName='" + getBranchName() + "'" +
            ", address='" + getAddress() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", branchHead='" + getBranchHead() + "'" +
            ", cellPhoneNo='" + getCellPhoneNo() + "'" +
            ", landLinePhoneNo='" + getLandLinePhoneNo() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", faxNo='" + getFaxNo() + "'" +
            ", isMainBranch='" + getIsMainBranch() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", status='" + getStatus() + "'" +
            ", college=" + getCollegeId() +
            ", city=" + getCityId() +
            ", state=" + getStateId() +
            "}";
    }
}
