package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * A Vo for the Teacher entity.
 */
public class CmsTeacherVo extends CmsCommonVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String uploadPhoto;
    private String logoFilePath;
    private String logoFileName;
    private String logoFileExtension;
    private String logoFile; // base64 encoded string
    
	private String teacherName;
    private String teacherMiddleName;
    private String teacherLastName;
    private String fatherName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String spouseName;
    private String spouseMiddleName;
    private String spouseLastName;
    private String motherName;
    private String motherMiddleName;
    private String motherLastName;
    private String aadharNo;
    private String panNo;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    
    private String placeOfBirth;
    private String religion;
    private String caste;
    private String subCaste;
    private Integer age;
    private String sex;
    private String bloodGroup;
    private String address;
    private String town;
    private String state;
    private String country;
    private String pinCode;
    private String teacherContactNumber;
    private String alternateContactNumber;
    private String teacherEmailAddress;
    private String alternateEmailAddress;
    private String relationWithStaff;
    private String emergencyContactName;
    private String emergencyContactMiddleName;
    private String emergencyContactLastName;
    private String emergencyContactNo;
    private String emergencyContactEmailAddress;
    
    private String status;
    private Long employeeId;
    private String designation;
    private String staffType;
    
    private CmsDepartmentVo cmsDepartmentVo;
    private CmsBranchVo cmsBranchVo;
    private Long departmentId;
    private Long branchId;
    
    private String strDateOfBirth;
    private List<CmsTeacherVo> dataList = new ArrayList<CmsTeacherVo>();
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherMiddleName() {
		return teacherMiddleName;
	}

	public void setTeacherMiddleName(String teacherMiddleName) {
		this.teacherMiddleName = teacherMiddleName;
	}

	public String getTeacherLastName() {
		return teacherLastName;
	}

	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getTeacherContactNumber() {
		return teacherContactNumber;
	}

	public void setTeacherContactNumber(String teacherContactNumber) {
		this.teacherContactNumber = teacherContactNumber;
	}

	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getTeacherEmailAddress() {
		return teacherEmailAddress;
	}

	public void setTeacherEmailAddress(String teacherEmailAddress) {
		this.teacherEmailAddress = teacherEmailAddress;
	}

	public String getAlternateEmailAddress() {
		return alternateEmailAddress;
	}

	public void setAlternateEmailAddress(String alternateEmailAddress) {
		this.alternateEmailAddress = alternateEmailAddress;
	}

	public String getRelationWithStaff() {
		return relationWithStaff;
	}

	public void setRelationWithStaff(String relationWithStaff) {
		this.relationWithStaff = relationWithStaff;
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

	public String getUploadPhoto() {
		return uploadPhoto;
	}

	public void setUploadPhoto(String uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
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

	public String getStrDateOfBirth() {
		return strDateOfBirth;
	}

	public void setStrDateOfBirth(String strDateOfBirth) {
		this.strDateOfBirth = strDateOfBirth;
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

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	@Override
	public String toString() {
		return "CmsTeacherVo [id=" + id + ", uploadPhoto=" + uploadPhoto + ", logoFilePath=" + logoFilePath
				+ ", logoFileName=" + logoFileName + ", logoFileExtension=" + logoFileExtension + ", logoFile="
				+ logoFile + ", teacherName=" + teacherName + ", teacherMiddleName=" + teacherMiddleName
				+ ", teacherLastName=" + teacherLastName + ", fatherName=" + fatherName + ", fatherMiddleName="
				+ fatherMiddleName + ", fatherLastName=" + fatherLastName + ", spouseName=" + spouseName
				+ ", spouseMiddleName=" + spouseMiddleName + ", spouseLastName=" + spouseLastName + ", motherName="
				+ motherName + ", motherMiddleName=" + motherMiddleName + ", motherLastName=" + motherLastName
				+ ", aadharNo=" + aadharNo + ", panNo=" + panNo + ", dateOfBirth=" + dateOfBirth + ", placeOfBirth="
				+ placeOfBirth + ", religion=" + religion + ", caste=" + caste + ", subCaste=" + subCaste + ", age="
				+ age + ", sex=" + sex + ", bloodGroup=" + bloodGroup + ", address=" + address + ", town=" + town
				+ ", state=" + state + ", country=" + country + ", pinCode=" + pinCode + ", teacherContactNumber="
				+ teacherContactNumber + ", alternateContactNumber=" + alternateContactNumber + ", teacherEmailAddress="
				+ teacherEmailAddress + ", alternateEmailAddress=" + alternateEmailAddress + ", relationWithStaff="
				+ relationWithStaff + ", emergencyContactName=" + emergencyContactName + ", emergencyContactMiddleName="
				+ emergencyContactMiddleName + ", emergencyContactLastName=" + emergencyContactLastName
				+ ", emergencyContactNo=" + emergencyContactNo + ", emergencyContactEmailAddress="
				+ emergencyContactEmailAddress + ", status=" + status + ", employeeId=" + employeeId + ", designation="
				+ designation + ", staffType=" + staffType + ", cmsDepartmentVo=" + cmsDepartmentVo + ", cmsBranchVo="
				+ cmsBranchVo + ", departmentId=" + departmentId + ", branchId=" + branchId + ", strDateOfBirth="
				+ strDateOfBirth + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStrCreatedOn()="
				+ getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public List<CmsTeacherVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<CmsTeacherVo> dataList) {
		this.dataList = dataList;
	}
  
}
