package com.synectiks.pref.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.pref.domain.AuthorizedSignatory} entity.
 */
public class AuthorizedSignatoryDTO implements Serializable {

    private Long id;

    private String name;

    private String fatherName;

    private String designation;

    private String address;

    private String email;

    private String panNo;


    private Long branchId;

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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorizedSignatoryDTO authorizedSignatoryDTO = (AuthorizedSignatoryDTO) o;
        if (authorizedSignatoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorizedSignatoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorizedSignatoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", panNo='" + getPanNo() + "'" +
            ", branch=" + getBranchId() +
            "}";
    }
}
