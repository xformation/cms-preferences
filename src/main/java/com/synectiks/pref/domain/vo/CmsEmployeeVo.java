package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CmsEmployeeVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String employeeName;
    private String employeeMiddleName;
    private String employeeLastName;
    private String fatherName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String spouseName;
    private String spouseMiddleName;
    private String spouseLastName;
    private String motherName;
    private String motherMiddleName;
    private String motherLastName;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String religion;
    private String caste;
    private String subCaste;
    private String gender;
    private String bloodGroup;
    private String pinCode;
    private String relationOfEmergencyContact;
    private String emergencyContactName;
    private String emergencyContactMiddleName;
    private String emergencyContactLastName;
    private String emergencyContactNo;
    private String emergencyContactEmailAddress;
    private String status;
    private String staffType;
    private String designation;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate joiningDate;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate jobEndDate;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate resignationDate;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate resignationAcceptanceDate;
    private String aadharNo;
    private String panNo;
    private String passportNo;
    private String primaryContactNo;
    private String secondaryContactNo;
    private String primaryAddress;
    private String secondaryAddress;
    private String personalMailId;
    private String officialMailId;
    private String drivingLicenceNo;
    private String typeOfEmployment;
    private Long managerId;
    private String maritalStatus;
   
    private Long departmentId;
    private CmsDepartmentVo CmsDepartmentVo;
    
    private Long branchId;
    private CmsBranchVo CmsBranchVo;
    
    private String strDateOfBirth;
    private String strJoiningDate;
    private String strJobEndDate;
    private String strResignationDate;
    private String strResignationAcceptanceDate;
    private List<CmsEmployeeVo> dataList = new ArrayList<>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeMiddleName() {
		return employeeMiddleName;
	}
	public void setEmployeeMiddleName(String employeeMiddleName) {
		this.employeeMiddleName = employeeMiddleName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherMiddleName() {
		return fatherMiddleName;
	}
	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}
	public String getFatherLastName() {
		return fatherLastName;
	}
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpouseMiddleName() {
		return spouseMiddleName;
	}
	public void setSpouseMiddleName(String spouseMiddleName) {
		this.spouseMiddleName = spouseMiddleName;
	}
	public String getSpouseLastName() {
		return spouseLastName;
	}
	public void setSpouseLastName(String spouseLastName) {
		this.spouseLastName = spouseLastName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherMiddleName() {
		return motherMiddleName;
	}
	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getSubCaste() {
		return subCaste;
	}
	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getRelationOfEmergencyContact() {
		return relationOfEmergencyContact;
	}
	public void setRelationOfEmergencyContact(String relationOfEmergencyContact) {
		this.relationOfEmergencyContact = relationOfEmergencyContact;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactMiddleName() {
		return emergencyContactMiddleName;
	}
	public void setEmergencyContactMiddleName(String emergencyContactMiddleName) {
		this.emergencyContactMiddleName = emergencyContactMiddleName;
	}
	public String getEmergencyContactLastName() {
		return emergencyContactLastName;
	}
	public void setEmergencyContactLastName(String emergencyContactLastName) {
		this.emergencyContactLastName = emergencyContactLastName;
	}
	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}
	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}
	public String getEmergencyContactEmailAddress() {
		return emergencyContactEmailAddress;
	}
	public void setEmergencyContactEmailAddress(String emergencyContactEmailAddress) {
		this.emergencyContactEmailAddress = emergencyContactEmailAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public LocalDate getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(LocalDate jobEndDate) {
		this.jobEndDate = jobEndDate;
	}
	public LocalDate getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}
	public LocalDate getResignationAcceptanceDate() {
		return resignationAcceptanceDate;
	}
	public void setResignationAcceptanceDate(LocalDate resignationAcceptanceDate) {
		this.resignationAcceptanceDate = resignationAcceptanceDate;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getPrimaryContactNo() {
		return primaryContactNo;
	}
	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}
	public String getSecondaryContactNo() {
		return secondaryContactNo;
	}
	public void setSecondaryContactNo(String secondaryContactNo) {
		this.secondaryContactNo = secondaryContactNo;
	}
	public String getPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	public String getSecondaryAddress() {
		return secondaryAddress;
	}
	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}
	public String getPersonalMailId() {
		return personalMailId;
	}
	public void setPersonalMailId(String personalMailId) {
		this.personalMailId = personalMailId;
	}
	public String getOfficialMailId() {
		return officialMailId;
	}
	public void setOfficialMailId(String officialMailId) {
		this.officialMailId = officialMailId;
	}
	public String getDrivingLicenceNo() {
		return drivingLicenceNo;
	}
	public void setDrivingLicenceNo(String drivingLicenceNo) {
		this.drivingLicenceNo = drivingLicenceNo;
	}
	public String getTypeOfEmployment() {
		return typeOfEmployment;
	}
	public void setTypeOfEmployment(String typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public CmsDepartmentVo getCmsDepartmentVo() {
		return CmsDepartmentVo;
	}
	public void setCmsDepartmentVo(CmsDepartmentVo cmsDepartmentVo) {
		CmsDepartmentVo = cmsDepartmentVo;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public CmsBranchVo getCmsBranchVo() {
		return CmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		CmsBranchVo = cmsBranchVo;
	}
	public String getStrDateOfBirth() {
		return strDateOfBirth;
	}
	public void setStrDateOfBirth(String strDateOfBirth) {
		this.strDateOfBirth = strDateOfBirth;
	}
	public String getStrJoiningDate() {
		return strJoiningDate;
	}
	public void setStrJoiningDate(String strJoiningDate) {
		this.strJoiningDate = strJoiningDate;
	}
	public String getStrJobEndDate() {
		return strJobEndDate;
	}
	public void setStrJobEndDate(String strJobEndDate) {
		this.strJobEndDate = strJobEndDate;
	}
	public String getStrResignationDate() {
		return strResignationDate;
	}
	public void setStrResignationDate(String strResignationDate) {
		this.strResignationDate = strResignationDate;
	}
	public String getStrResignationAcceptanceDate() {
		return strResignationAcceptanceDate;
	}
	public void setStrResignationAcceptanceDate(String strResignationAcceptanceDate) {
		this.strResignationAcceptanceDate = strResignationAcceptanceDate;
	}
	@Override
	public String toString() {
		return "CmsEmployeeVo [id=" + id + ", employeeName=" + employeeName + ", employeeMiddleName="
				+ employeeMiddleName + ", employeeLastName=" + employeeLastName + ", fatherName=" + fatherName
				+ ", fatherMiddleName=" + fatherMiddleName + ", fatherLastName=" + fatherLastName + ", spouseName="
				+ spouseName + ", spouseMiddleName=" + spouseMiddleName + ", spouseLastName=" + spouseLastName
				+ ", motherName=" + motherName + ", motherMiddleName=" + motherMiddleName + ", motherLastName="
				+ motherLastName + ", dateOfBirth=" + dateOfBirth + ", placeOfBirth=" + placeOfBirth + ", religion="
				+ religion + ", caste=" + caste + ", subCaste=" + subCaste + ", gender=" + gender + ", bloodGroup="
				+ bloodGroup + ", pinCode=" + pinCode + ", relationOfEmergencyContact=" + relationOfEmergencyContact
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactMiddleName="
				+ emergencyContactMiddleName + ", emergencyContactLastName=" + emergencyContactLastName
				+ ", emergencyContactNo=" + emergencyContactNo + ", emergencyContactEmailAddress="
				+ emergencyContactEmailAddress + ", status=" + status + ", staffType=" + staffType + ", designation="
				+ designation + ", joiningDate=" + joiningDate + ", jobEndDate=" + jobEndDate + ", resignationDate="
				+ resignationDate + ", resignationAcceptanceDate=" + resignationAcceptanceDate + ", aadharNo="
				+ aadharNo + ", panNo=" + panNo + ", passportNo=" + passportNo + ", primaryContactNo="
				+ primaryContactNo + ", secondaryContactNo=" + secondaryContactNo + ", primaryAddress=" + primaryAddress
				+ ", secondaryAddress=" + secondaryAddress + ", personalMailId=" + personalMailId + ", officialMailId="
				+ officialMailId + ", drivingLicenceNo=" + drivingLicenceNo + ", typeOfEmployment=" + typeOfEmployment
				+ ", managerId=" + managerId + ", maritalStatus=" + maritalStatus + ", departmentId=" + departmentId
				+ ", CmsDepartmentVo=" + CmsDepartmentVo + ", branchId=" + branchId + ", CmsBranchVo=" + CmsBranchVo
				+ ", strDateOfBirth=" + strDateOfBirth + ", strJoiningDate=" + strJoiningDate + ", strJobEndDate="
				+ strJobEndDate + ", strResignationDate=" + strResignationDate + ", strResignationAcceptanceDate="
				+ strResignationAcceptanceDate + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()="
				+ getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn()
				+ ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public List<CmsEmployeeVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsEmployeeVo> dataList) {
		this.dataList = dataList;
	}
    
    
    
}
