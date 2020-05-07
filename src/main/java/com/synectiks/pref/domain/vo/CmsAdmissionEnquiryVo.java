package com.synectiks.pref.domain.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.synectiks.pref.domain.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CmsAdmissionEnquiryVo extends CmsCommonVo implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String studentName;
    private String studentMiddleName;
    private String studentLastName;
    private String cellPhoneNo;
    private String landLinePhoneNo;
    private String emailId;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;

    private String gender;
    private String highestQualification;
    private String modeOfEnquiry;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate enquiryDate;

    private String comments;
    private Long branchId;
    private Long departmentId;
    private Long courseId;
    private Long semesterId;
    private Long batchId;
    private Long stateId;
    private Long cityId;
    private Long academicYearId;
    private Branch branch;
    private Department department;
    private Course course;
    private CmsSemesterVo semester;
    private Batch batch;
    private State state;
    private City city;
    private AcademicYear academicYear;

    private String enquiryStatus;

    private String strDateOfBirth;
    private String strEnquiryDate;

    private List<String> genderList;
    private List<String> modeOfEnquiryList;
    private List<String> enquiryStatusList;
    private String transactionSource;
    private String sourceOfApplication;
    private List<CmsAdmissionEnquiryVo> dataList = new ArrayList<CmsAdmissionEnquiryVo>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getModeOfEnquiry() {
        return modeOfEnquiry;
    }

    public void setModeOfEnquiry(String modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
    }

    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CmsSemesterVo getSemester() {
        return semester;
    }

    public void setSemester(CmsSemesterVo semester) {
        this.semester = semester;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }

    public String getStrDateOfBirth() {
        return strDateOfBirth;
    }

    public void setStrDateOfBirth(String strDateOfBirth) {
        this.strDateOfBirth = strDateOfBirth;
    }

    public String getStrEnquiryDate() {
        return strEnquiryDate;
    }

    public void setStrEnquiryDate(String strEnquiryDate) {
        this.strEnquiryDate = strEnquiryDate;
    }

    public List<String> getGenderList() {
        return genderList;
    }

    public void setGenderList(List<String> genderList) {
        this.genderList = genderList;
    }

    public List<String> getModeOfEnquiryList() {
        return modeOfEnquiryList;
    }

    public void setModeOfEnquiryList(List<String> modeOfEnquiryList) {
        this.modeOfEnquiryList = modeOfEnquiryList;
    }

    public List<String> getEnquiryStatusList() {
        return enquiryStatusList;
    }

    public void setEnquiryStatusList(List<String> enquiryStatusList) {
        this.enquiryStatusList = enquiryStatusList;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public String getSourceOfApplication() {
        return sourceOfApplication;
    }

    public void setSourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
    }

    public List<CmsAdmissionEnquiryVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsAdmissionEnquiryVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsAdmissionEnquiryVo{" +
            "id=" + id +
            ", studentName='" + studentName + '\'' +
            ", studentMiddleName='" + studentMiddleName + '\'' +
            ", studentLastName='" + studentLastName + '\'' +
            ", cellPhoneNo='" + cellPhoneNo + '\'' +
            ", landLinePhoneNo='" + landLinePhoneNo + '\'' +
            ", emailId='" + emailId + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", gender='" + gender + '\'' +
            ", highestQualification='" + highestQualification + '\'' +
            ", modeOfEnquiry='" + modeOfEnquiry + '\'' +
            ", enquiryDate=" + enquiryDate +
            ", comments='" + comments + '\'' +
            ", branchId=" + branchId +
            ", departmentId=" + departmentId +
            ", courseId=" + courseId +
            ", semesterId=" + semesterId +
            ", batchId=" + batchId +
            ", stateId=" + stateId +
            ", cityId=" + cityId +
            ", academicYearId=" + academicYearId +
            ", branch=" + branch +
            ", department=" + department +
            ", course=" + course +
            ", semester=" + semester +
            ", batch=" + batch +
            ", state=" + state +
            ", city=" + city +
            ", academicYear=" + academicYear +
            ", enquiryStatus='" + enquiryStatus + '\'' +
            ", strDateOfBirth='" + strDateOfBirth + '\'' +
            ", strEnquiryDate='" + strEnquiryDate + '\'' +
            ", genderList=" + genderList +
            ", modeOfEnquiryList=" + modeOfEnquiryList +
            ", enquiryStatusList=" + enquiryStatusList +
            ", transactionSource='" + transactionSource + '\'' +
            ", sourceOfApplication='" + sourceOfApplication + '\'' +
            ", dataList=" + dataList +
            '}';
    }
}
