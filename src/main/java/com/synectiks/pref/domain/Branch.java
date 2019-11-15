package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Branch.
 */
@Entity
@Table(name = "branch")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "branch")
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "address")
    private String address;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "branch_head")
    private String branchHead;

    @Column(name = "cell_phone_no")
    private String cellPhoneNo;

    @Column(name = "land_line_phone_no")
    private String landLinePhoneNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "fax_no")
    private String faxNo;

    @Size(max = 3)
    @Column(name = "is_main_branch", length = 3)
    private String isMainBranch;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("branches")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("branches")
    private City city;

    @ManyToOne
    @JsonIgnoreProperties("branches")
    private State state;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public Branch branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public Branch address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public Branch pinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getBranchHead() {
        return branchHead;
    }

    public Branch branchHead(String branchHead) {
        this.branchHead = branchHead;
        return this;
    }

    public void setBranchHead(String branchHead) {
        this.branchHead = branchHead;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public Branch cellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
        return this;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getLandLinePhoneNo() {
        return landLinePhoneNo;
    }

    public Branch landLinePhoneNo(String landLinePhoneNo) {
        this.landLinePhoneNo = landLinePhoneNo;
        return this;
    }

    public void setLandLinePhoneNo(String landLinePhoneNo) {
        this.landLinePhoneNo = landLinePhoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public Branch emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public Branch faxNo(String faxNo) {
        this.faxNo = faxNo;
        return this;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getIsMainBranch() {
        return isMainBranch;
    }

    public Branch isMainBranch(String isMainBranch) {
        this.isMainBranch = isMainBranch;
        return this;
    }

    public void setIsMainBranch(String isMainBranch) {
        this.isMainBranch = isMainBranch;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Branch startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Branch createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Branch createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Branch updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public Branch updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public Branch status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public College getCollege() {
        return college;
    }

    public Branch college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public City getCity() {
        return city;
    }

    public Branch city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public Branch state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branch)) {
            return false;
        }
        return id != null && id.equals(((Branch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Branch{" +
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
            "}";
    }
}
