package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * A VO for the LegalEntity entity.
 */
public class CmsLegalEntityVo extends CmsCommonVo implements Serializable {

	private Long id;
	private String logoFile;
    private String logoFilePath;
    private String logoFileName;
    private String logoFileExtension;
    private String legalNameOfCollege;
    private String typeOfCollege;

    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfIncorporation;

    private String registeredOfficeAddress;
    private String collegeIdentificationNumber;
    private String pan;
    private String tan;
    private String tanCircleNumber;
    private String citTdsLocation;
    private Long formSignatory;
    private String pfNumber;

    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate pfRegistrationDate;
    
    private Long pfSignatory;
    private String esiNumber;

    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate esiRegistrationDate;
    
    private Long esiSignatory;
    private String ptNumber;

    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate ptRegistrationDate;

    private Long ptSignatory;

    private CmsBranchVo cmsBranchVo;
    private Long branchId;
    private String strDateOfIncorporation;
    private String strPfRegistrationDate;
    private String strEsiRegistrationDate;
    private String strPtRegistrationDate;

    
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
	public String toString() {
		return "CmsLegalEntityVo [id=" + id + ", logoFilePath=" + logoFilePath + ", logoFileName=" + logoFileName
				+ ", logoFileExtension=" + logoFileExtension + ", legalNameOfCollege=" + legalNameOfCollege
				+ ", typeOfCollege=" + typeOfCollege + ", dateOfIncorporation=" + dateOfIncorporation
				+ ", registeredOfficeAddress=" + registeredOfficeAddress + ", collegeIdentificationNumber="
				+ collegeIdentificationNumber + ", pan=" + pan + ", tan=" + tan + ", tanCircleNumber=" + tanCircleNumber
				+ ", citTdsLocation=" + citTdsLocation + ", formSignatory=" + formSignatory + ", pfNumber=" + pfNumber
				+ ", pfRegistrationDate=" + pfRegistrationDate + ", pfSignatory=" + pfSignatory + ", esiNumber="
				+ esiNumber + ", esiRegistrationDate=" + esiRegistrationDate + ", esiSignatory=" + esiSignatory
				+ ", ptNumber=" + ptNumber + ", ptRegistrationDate=" + ptRegistrationDate + ", ptSignatory="
				+ ptSignatory + ", branchId=" + branchId + ", strDateOfIncorporation="
				+ strDateOfIncorporation + ", strPfRegistrationDate=" + strPfRegistrationDate
				+ ", strEsiRegistrationDate=" + strEsiRegistrationDate + ", strPtRegistrationDate="
				+ strPtRegistrationDate + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()="
				+ getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	public String getStrDateOfIncorporation() {
		return strDateOfIncorporation;
	}
	public void setStrDateOfIncorporation(String strDateOfIncorporation) {
		this.strDateOfIncorporation = strDateOfIncorporation;
	}
	public String getStrPfRegistrationDate() {
		return strPfRegistrationDate;
	}
	public void setStrPfRegistrationDate(String strPfRegistrationDate) {
		this.strPfRegistrationDate = strPfRegistrationDate;
	}
	public String getStrEsiRegistrationDate() {
		return strEsiRegistrationDate;
	}
	public void setStrEsiRegistrationDate(String strEsiRegistrationDate) {
		this.strEsiRegistrationDate = strEsiRegistrationDate;
	}
	public String getStrPtRegistrationDate() {
		return strPtRegistrationDate;
	}
	public void setStrPtRegistrationDate(String strPtRegistrationDate) {
		this.strPtRegistrationDate = strPtRegistrationDate;
	}
	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		this.cmsBranchVo = cmsBranchVo;
	}
	
	
	
	
	
}
