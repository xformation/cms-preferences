package com.synectiks.pref.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.pref.domain.LegalEntity} entity.
 */
public class LegalEntityDTO implements Serializable {

    private Long id;

    private String logoFilePath;

    private String logoFileName;

    private String logoFileExtension;

    private String legalNameOfCollege;

    private String typeOfCollege;

    private LocalDate dateOfIncorporation;

    private String registeredOfficeAddress;

    private String collegeIdentificationNumber;

    private String pan;

    private String tan;

    private String tanCircleNumber;

    private String citTdsLocation;

    private Long formSignatory;

    private String pfNumber;

    private LocalDate pfRegistrationDate;

    private Long pfSignatory;

    private String esiNumber;

    private LocalDate esiRegistrationDate;

    private Long esiSignatory;

    private String ptNumber;

    private LocalDate ptRegistrationDate;

    private Long ptSignatory;


    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getLogoFileExtension() {
        return logoFileExtension;
    }

    public void setLogoFileExtension(String logoFileExtension) {
        this.logoFileExtension = logoFileExtension;
    }

    public String getLegalNameOfCollege() {
        return legalNameOfCollege;
    }

    public void setLegalNameOfCollege(String legalNameOfCollege) {
        this.legalNameOfCollege = legalNameOfCollege;
    }

    public String getTypeOfCollege() {
        return typeOfCollege;
    }

    public void setTypeOfCollege(String typeOfCollege) {
        this.typeOfCollege = typeOfCollege;
    }

    public LocalDate getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(LocalDate dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
    }

    public String getCollegeIdentificationNumber() {
        return collegeIdentificationNumber;
    }

    public void setCollegeIdentificationNumber(String collegeIdentificationNumber) {
        this.collegeIdentificationNumber = collegeIdentificationNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getTan() {
        return tan;
    }

    public void setTan(String tan) {
        this.tan = tan;
    }

    public String getTanCircleNumber() {
        return tanCircleNumber;
    }

    public void setTanCircleNumber(String tanCircleNumber) {
        this.tanCircleNumber = tanCircleNumber;
    }

    public String getCitTdsLocation() {
        return citTdsLocation;
    }

    public void setCitTdsLocation(String citTdsLocation) {
        this.citTdsLocation = citTdsLocation;
    }

    public Long getFormSignatory() {
        return formSignatory;
    }

    public void setFormSignatory(Long formSignatory) {
        this.formSignatory = formSignatory;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public LocalDate getPfRegistrationDate() {
        return pfRegistrationDate;
    }

    public void setPfRegistrationDate(LocalDate pfRegistrationDate) {
        this.pfRegistrationDate = pfRegistrationDate;
    }

    public Long getPfSignatory() {
        return pfSignatory;
    }

    public void setPfSignatory(Long pfSignatory) {
        this.pfSignatory = pfSignatory;
    }

    public String getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(String esiNumber) {
        this.esiNumber = esiNumber;
    }

    public LocalDate getEsiRegistrationDate() {
        return esiRegistrationDate;
    }

    public void setEsiRegistrationDate(LocalDate esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
    }

    public Long getEsiSignatory() {
        return esiSignatory;
    }

    public void setEsiSignatory(Long esiSignatory) {
        this.esiSignatory = esiSignatory;
    }

    public String getPtNumber() {
        return ptNumber;
    }

    public void setPtNumber(String ptNumber) {
        this.ptNumber = ptNumber;
    }

    public LocalDate getPtRegistrationDate() {
        return ptRegistrationDate;
    }

    public void setPtRegistrationDate(LocalDate ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
    }

    public Long getPtSignatory() {
        return ptSignatory;
    }

    public void setPtSignatory(Long ptSignatory) {
        this.ptSignatory = ptSignatory;
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

        LegalEntityDTO legalEntityDTO = (LegalEntityDTO) o;
        if (legalEntityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), legalEntityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LegalEntityDTO{" +
            "id=" + getId() +
            ", logoFilePath='" + getLogoFilePath() + "'" +
            ", logoFileName='" + getLogoFileName() + "'" +
            ", logoFileExtension='" + getLogoFileExtension() + "'" +
            ", legalNameOfCollege='" + getLegalNameOfCollege() + "'" +
            ", typeOfCollege='" + getTypeOfCollege() + "'" +
            ", dateOfIncorporation='" + getDateOfIncorporation() + "'" +
            ", registeredOfficeAddress='" + getRegisteredOfficeAddress() + "'" +
            ", collegeIdentificationNumber='" + getCollegeIdentificationNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", tan='" + getTan() + "'" +
            ", tanCircleNumber='" + getTanCircleNumber() + "'" +
            ", citTdsLocation='" + getCitTdsLocation() + "'" +
            ", formSignatory=" + getFormSignatory() +
            ", pfNumber='" + getPfNumber() + "'" +
            ", pfRegistrationDate='" + getPfRegistrationDate() + "'" +
            ", pfSignatory=" + getPfSignatory() +
            ", esiNumber='" + getEsiNumber() + "'" +
            ", esiRegistrationDate='" + getEsiRegistrationDate() + "'" +
            ", esiSignatory=" + getEsiSignatory() +
            ", ptNumber='" + getPtNumber() + "'" +
            ", ptRegistrationDate='" + getPtRegistrationDate() + "'" +
            ", ptSignatory=" + getPtSignatory() +
            ", branch=" + getBranchId() +
            "}";
    }
}
