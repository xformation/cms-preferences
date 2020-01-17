package com.synectiks.pref.domain;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_middle_name")
    private String employeeMiddleName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_middle_name")
    private String fatherMiddleName;

    @Column(name = "father_last_name")
    private String fatherLastName;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "spouse_middle_name")
    private String spouseMiddleName;

    @Column(name = "spouse_last_name")
    private String spouseLastName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_middle_name")
    private String motherMiddleName;

    @Column(name = "mother_last_name")
    private String motherLastName;

    @Column(name = "date_of_birth")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "religion")
    private String religion;

    @Column(name = "caste")
    private String caste;

    @Column(name = "sub_caste")
    private String subCaste;

    @Column(name = "gender")
    private String gender;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "relation_of_emergency_contact")
    private String relationOfEmergencyContact;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_middle_name")
    private String emergencyContactMiddleName;

    @Column(name = "emergency_contact_last_name")
    private String emergencyContactLastName;

    @Column(name = "emergency_contact_no")
    private String emergencyContactNo;

    @Column(name = "emergency_contact_email_address")
    private String emergencyContactEmailAddress;

    @Column(name = "status")
    private String status;

    @Column(name = "staff_type")
    private String staffType;

    @Column(name = "designation")
    private String designation;

    @Column(name = "joining_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate joiningDate;

    @Column(name = "job_end_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate jobEndDate;

    @Column(name = "resignation_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate resignationDate;

    @Column(name = "resignation_acceptance_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate resignationAcceptanceDate;

    @Column(name = "aadhar_no")
    private String aadharNo;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "primary_contact_no")
    private String primaryContactNo;

    @Column(name = "secondary_contact_no")
    private String secondaryContactNo;

    @Column(name = "primary_address")
    private String primaryAddress;

    @Column(name = "secondary_address")
    private String secondaryAddress;

    @Column(name = "personal_mail_id")
    private String personalMailId;

    @Column(name = "official_mail_id")
    private String officialMailId;

    @Column(name = "driving_licence_no")
    private String drivingLicenceNo;

    @Column(name = "type_of_employment")
    private String typeOfEmployment;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "marital_status")
    private String maritalStatus;

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Employee employeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public Employee employeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
        return this;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public Employee employeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
        return this;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Employee fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public Employee fatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
        return this;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public Employee fatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
        return this;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public Employee spouseName(String spouseName) {
        this.spouseName = spouseName;
        return this;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseMiddleName() {
        return spouseMiddleName;
    }

    public Employee spouseMiddleName(String spouseMiddleName) {
        this.spouseMiddleName = spouseMiddleName;
        return this;
    }

    public void setSpouseMiddleName(String spouseMiddleName) {
        this.spouseMiddleName = spouseMiddleName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public Employee spouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
        return this;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public Employee motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public Employee motherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
        return this;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public Employee motherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
        return this;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Employee dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public Employee placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getReligion() {
        return religion;
    }

    public Employee religion(String religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public Employee caste(String caste) {
        this.caste = caste;
        return this;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public Employee subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public String getGender() {
        return gender;
    }

    public Employee gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public Employee bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPinCode() {
        return pinCode;
    }

    public Employee pinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getRelationOfEmergencyContact() {
        return relationOfEmergencyContact;
    }

    public Employee relationOfEmergencyContact(String relationOfEmergencyContact) {
        this.relationOfEmergencyContact = relationOfEmergencyContact;
        return this;
    }

    public void setRelationOfEmergencyContact(String relationOfEmergencyContact) {
        this.relationOfEmergencyContact = relationOfEmergencyContact;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public Employee emergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
        return this;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactMiddleName() {
        return emergencyContactMiddleName;
    }

    public Employee emergencyContactMiddleName(String emergencyContactMiddleName) {
        this.emergencyContactMiddleName = emergencyContactMiddleName;
        return this;
    }

    public void setEmergencyContactMiddleName(String emergencyContactMiddleName) {
        this.emergencyContactMiddleName = emergencyContactMiddleName;
    }

    public String getEmergencyContactLastName() {
        return emergencyContactLastName;
    }

    public Employee emergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
        return this;
    }

    public void setEmergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public Employee emergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
        return this;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getEmergencyContactEmailAddress() {
        return emergencyContactEmailAddress;
    }

    public Employee emergencyContactEmailAddress(String emergencyContactEmailAddress) {
        this.emergencyContactEmailAddress = emergencyContactEmailAddress;
        return this;
    }

    public void setEmergencyContactEmailAddress(String emergencyContactEmailAddress) {
        this.emergencyContactEmailAddress = emergencyContactEmailAddress;
    }

    public String getStatus() {
        return status;
    }

    public Employee status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStaffType() {
        return staffType;
    }

    public Employee staffType(String staffType) {
        this.staffType = staffType;
        return this;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getDesignation() {
        return designation;
    }

    public Employee designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public Employee joiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
        return this;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getJobEndDate() {
        return jobEndDate;
    }

    public Employee jobEndDate(LocalDate jobEndDate) {
        this.jobEndDate = jobEndDate;
        return this;
    }

    public void setJobEndDate(LocalDate jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public Employee resignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
        return this;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public LocalDate getResignationAcceptanceDate() {
        return resignationAcceptanceDate;
    }

    public Employee resignationAcceptanceDate(LocalDate resignationAcceptanceDate) {
        this.resignationAcceptanceDate = resignationAcceptanceDate;
        return this;
    }

    public void setResignationAcceptanceDate(LocalDate resignationAcceptanceDate) {
        this.resignationAcceptanceDate = resignationAcceptanceDate;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public Employee aadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public Employee panNo(String panNo) {
        this.panNo = panNo;
        return this;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public Employee passportNo(String passportNo) {
        this.passportNo = passportNo;
        return this;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPrimaryContactNo() {
        return primaryContactNo;
    }

    public Employee primaryContactNo(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
        return this;
    }

    public void setPrimaryContactNo(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
    }

    public String getSecondaryContactNo() {
        return secondaryContactNo;
    }

    public Employee secondaryContactNo(String secondaryContactNo) {
        this.secondaryContactNo = secondaryContactNo;
        return this;
    }

    public void setSecondaryContactNo(String secondaryContactNo) {
        this.secondaryContactNo = secondaryContactNo;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public Employee primaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
        return this;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public Employee secondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
        return this;
    }

    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getPersonalMailId() {
        return personalMailId;
    }

    public Employee personalMailId(String personalMailId) {
        this.personalMailId = personalMailId;
        return this;
    }

    public void setPersonalMailId(String personalMailId) {
        this.personalMailId = personalMailId;
    }

    public String getOfficialMailId() {
        return officialMailId;
    }

    public Employee officialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
        return this;
    }

    public void setOfficialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public Employee drivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
        return this;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public String getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public Employee typeOfEmployment(String typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
        return this;
    }

    public void setTypeOfEmployment(String typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public Long getManagerId() {
        return managerId;
    }

    public Employee managerId(Long managerId) {
        this.managerId = managerId;
        return this;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Employee maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Branch getBranch() {
        return branch;
    }

    public Employee branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", employeeName='" + getEmployeeName() + "'" +
            ", employeeMiddleName='" + getEmployeeMiddleName() + "'" +
            ", employeeLastName='" + getEmployeeLastName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", fatherMiddleName='" + getFatherMiddleName() + "'" +
            ", fatherLastName='" + getFatherLastName() + "'" +
            ", spouseName='" + getSpouseName() + "'" +
            ", spouseMiddleName='" + getSpouseMiddleName() + "'" +
            ", spouseLastName='" + getSpouseLastName() + "'" +
            ", motherName='" + getMotherName() + "'" +
            ", motherMiddleName='" + getMotherMiddleName() + "'" +
            ", motherLastName='" + getMotherLastName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", placeOfBirth='" + getPlaceOfBirth() + "'" +
            ", religion='" + getReligion() + "'" +
            ", caste='" + getCaste() + "'" +
            ", subCaste='" + getSubCaste() + "'" +
            ", gender='" + getGender() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", relationOfEmergencyContact='" + getRelationOfEmergencyContact() + "'" +
            ", emergencyContactName='" + getEmergencyContactName() + "'" +
            ", emergencyContactMiddleName='" + getEmergencyContactMiddleName() + "'" +
            ", emergencyContactLastName='" + getEmergencyContactLastName() + "'" +
            ", emergencyContactNo='" + getEmergencyContactNo() + "'" +
            ", emergencyContactEmailAddress='" + getEmergencyContactEmailAddress() + "'" +
            ", status='" + getStatus() + "'" +
            ", staffType='" + getStaffType() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", joiningDate='" + getJoiningDate() + "'" +
            ", jobEndDate='" + getJobEndDate() + "'" +
            ", resignationDate='" + getResignationDate() + "'" +
            ", resignationAcceptanceDate='" + getResignationAcceptanceDate() + "'" +
            ", aadharNo='" + getAadharNo() + "'" +
            ", panNo='" + getPanNo() + "'" +
            ", passportNo='" + getPassportNo() + "'" +
            ", primaryContactNo='" + getPrimaryContactNo() + "'" +
            ", secondaryContactNo='" + getSecondaryContactNo() + "'" +
            ", primaryAddress='" + getPrimaryAddress() + "'" +
            ", secondaryAddress='" + getSecondaryAddress() + "'" +
            ", personalMailId='" + getPersonalMailId() + "'" +
            ", officialMailId='" + getOfficialMailId() + "'" +
            ", drivingLicenceNo='" + getDrivingLicenceNo() + "'" +
            ", typeOfEmployment='" + getTypeOfEmployment() + "'" +
            ", managerId=" + getManagerId() +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            "}";
    }
}
