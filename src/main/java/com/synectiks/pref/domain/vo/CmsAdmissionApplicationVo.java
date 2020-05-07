package com.synectiks.pref.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsAdmissionApplicationVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String sourceOfApplication;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate applicationDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate completionDate;

    private Long admissionNo;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate admissionDate;

    private String comments;
    private String applicationStatus;
    private Long branchId;
    private Long academicYearId;
    private Branch branch;
    private AcademicYear academicYear;


    private String strApplicationDate;
    private String strCompletionDate;
    private String strAdmissionDate;
    private Long admissionEnquiryId;
    private CmsAdmissionEnquiryVo admissionEnquiryVo;

    private List<CmsAdmissionApplicationVo> dataList = new ArrayList<CmsAdmissionApplicationVo>();
    private List<CmsAdmissionEnquiryVo> enquiryList = new ArrayList<CmsAdmissionEnquiryVo>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceOfApplication() {
        return sourceOfApplication;
    }

    public void setSourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Long getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public String getStrApplicationDate() {
        return strApplicationDate;
    }

    public void setStrApplicationDate(String strApplicationDate) {
        this.strApplicationDate = strApplicationDate;
    }

    public String getStrCompletionDate() {
        return strCompletionDate;
    }

    public void setStrCompletionDate(String strCompletionDate) {
        this.strCompletionDate = strCompletionDate;
    }

    public String getStrAdmissionDate() {
        return strAdmissionDate;
    }

    public void setStrAdmissionDate(String strAdmissionDate) {
        this.strAdmissionDate = strAdmissionDate;
    }

    public Long getAdmissionEnquiryId() {
        return admissionEnquiryId;
    }

    public void setAdmissionEnquiryId(Long admissionEnquiryId) {
        this.admissionEnquiryId = admissionEnquiryId;
    }

    public CmsAdmissionEnquiryVo getAdmissionEnquiryVo() {
        return admissionEnquiryVo;
    }

    public void setAdmissionEnquiryVo(CmsAdmissionEnquiryVo admissionEnquiryVo) {
        this.admissionEnquiryVo = admissionEnquiryVo;
    }

    public List<CmsAdmissionApplicationVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsAdmissionApplicationVo> dataList) {
        this.dataList = dataList;
    }

    public List<CmsAdmissionEnquiryVo> getEnquiryList() {
        return enquiryList;
    }

    public void setEnquiryList(List<CmsAdmissionEnquiryVo> enquiryList) {
        this.enquiryList = enquiryList;
    }

    @Override
    public String toString() {
        return "CmsAdmissionApplicationVo{" +
            "id=" + id +
            ", sourceOfApplication='" + sourceOfApplication + '\'' +
            ", applicationDate=" + applicationDate +
            ", completionDate=" + completionDate +
            ", admissionNo=" + admissionNo +
            ", admissionDate=" + admissionDate +
            ", comments='" + comments + '\'' +
            ", applicationStatus='" + applicationStatus + '\'' +
            ", branchId=" + branchId +
            ", academicYearId=" + academicYearId +
            ", branch=" + branch +
            ", academicYear=" + academicYear +
            ", strApplicationDate='" + strApplicationDate + '\'' +
            ", strCompletionDate='" + strCompletionDate + '\'' +
            ", strAdmissionDate='" + strAdmissionDate + '\'' +
            ", admissionEnquiryId=" + admissionEnquiryId +
            ", admissionEnquiryVo=" + admissionEnquiryVo +
            ", dataList=" + dataList +
            ", enquiryList=" + enquiryList +
            '}';
    }
}
