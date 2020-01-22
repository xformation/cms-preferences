package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CmsEmployeeVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String employeeName;
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
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    
    private String aadharNo;
    private String panNo;
    private String passportNo;
    private String primaryContactNo;
    private String secondaryContactNo;
    private String employeeFatherName;
    private String employeeMotherName;
    private String primaryAddress;
    private String secondaryAddress;
    private String employeeAddress;
    private String personalMailId;
    private String officialMailId;
    private String disability;
    private String drivingLicenceNo;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate drivingLicenceValidity;
    
    private String gender;
    private String typeOfEmployment;
    private Long managerId;
    private String status;
    private String maritalStatus;
    
    private String strJoiningDate;
    private String strJobEndDate;
    private String strResignationDate;
    private String strResignationAcceptanceDate;
    private String strDrivingLicenceValidity;
    private String strDateOfBirth;
    private String staffType;
    
    private CmsDepartmentVo cmsDepartmentVo;
    private CmsBranchVo cmsBranchVo;
    private Long departmentId;
    private Long branchId;
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
	public String getEmployeeFatherName() {
		return employeeFatherName;
	}
	public void setEmployeeFatherName(String employeeFatherName) {
		this.employeeFatherName = employeeFatherName;
	}
	public String getEmployeeMotherName() {
		return employeeMotherName;
	}
	public void setEmployeeMotherName(String employeeMotherName) {
		this.employeeMotherName = employeeMotherName;
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
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
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
	public String getDisability() {
		return disability;
	}
	public void setDisability(String disability) {
		this.disability = disability;
	}
	public String getDrivingLicenceNo() {
		return drivingLicenceNo;
	}
	public void setDrivingLicenceNo(String drivingLicenceNo) {
		this.drivingLicenceNo = drivingLicenceNo;
	}
	public LocalDate getDrivingLicenceValidity() {
		return drivingLicenceValidity;
	}
	public void setDrivingLicenceValidity(LocalDate drivingLicenceValidity) {
		this.drivingLicenceValidity = drivingLicenceValidity;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	public String getStrDrivingLicenceValidity() {
		return strDrivingLicenceValidity;
	}
	public void setStrDrivingLicenceValidity(String strDrivingLicenceValidity) {
		this.strDrivingLicenceValidity = strDrivingLicenceValidity;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public List<CmsEmployeeVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsEmployeeVo> dataList) {
		this.dataList = dataList;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getStrDateOfBirth() {
		return strDateOfBirth;
	}
	public void setStrDateOfBirth(String strDateOfBirth) {
		this.strDateOfBirth = strDateOfBirth;
	}
	public CmsDepartmentVo getCmsDepartmentVo() {
		return cmsDepartmentVo;
	}
	public void setCmsDepartmentVo(CmsDepartmentVo cmsDepartmentVo) {
		this.cmsDepartmentVo = cmsDepartmentVo;
	}
	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		this.cmsBranchVo = cmsBranchVo;
	}
	@Override
	public String toString() {
		return "CmsEmployeeVo [id=" + id + ", employeeName=" + employeeName + ", designation=" + designation
				+ ", joiningDate=" + joiningDate + ", jobEndDate=" + jobEndDate + ", resignationDate=" + resignationDate
				+ ", resignationAcceptanceDate=" + resignationAcceptanceDate + ", dateOfBirth=" + dateOfBirth
				+ ", aadharNo=" + aadharNo + ", panNo=" + panNo + ", passportNo=" + passportNo + ", primaryContactNo="
				+ primaryContactNo + ", secondaryContactNo=" + secondaryContactNo + ", employeeFatherName="
				+ employeeFatherName + ", employeeMotherName=" + employeeMotherName + ", primaryAddress="
				+ primaryAddress + ", secondaryAddress=" + secondaryAddress + ", employeeAddress=" + employeeAddress
				+ ", personalMailId=" + personalMailId + ", officialMailId=" + officialMailId + ", disability="
				+ disability + ", drivingLicenceNo=" + drivingLicenceNo + ", drivingLicenceValidity="
				+ drivingLicenceValidity + ", gender=" + gender + ", typeOfEmployment=" + typeOfEmployment
				+ ", managerId=" + managerId + ", status=" + status + ", maritalStatus=" + maritalStatus
				+ ", strJoiningDate=" + strJoiningDate + ", strJobEndDate=" + strJobEndDate + ", strResignationDate="
				+ strResignationDate + ", strResignationAcceptanceDate=" + strResignationAcceptanceDate
				+ ", strDrivingLicenceValidity=" + strDrivingLicenceValidity + ", strDateOfBirth=" + strDateOfBirth
				+ ", cmsDepartmentVo=" + cmsDepartmentVo + ", cmsBranchVo=" + cmsBranchVo + ", departmentId="
				+ departmentId + ", branchId=" + branchId + ", dataList=" + dataList + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedOn()=" + getUpdatedOn() + ", getStrCreatedOn()=" + getStrCreatedOn()
				+ ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
    
	
    
}
