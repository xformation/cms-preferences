package com.synectiks.pref.domain.vo;

import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CmsFacility extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate suspandStartDate;
    private LocalDate suspandEndDate;
    private Long academicYearId;
    private Long branchId;
    private AcademicYear academicYear;
    private Branch branch;

    private CmsAcademicYearVo cmsAcademicYearVo;
    private CmsBranchVo cmsBranchVo;

    private String strStartDate;
    private String strEndDate;
    private String strSuspandStartDate;
    private String strSuspandEndDate;
    private Long amount;
    private String strAmount;
    private List<CmsFacility> dataList = new ArrayList<CmsFacility>();

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSuspandStartDate() {
        return suspandStartDate;
    }

    public void setSuspandStartDate(LocalDate suspandStartDate) {
        this.suspandStartDate = suspandStartDate;
    }

    public LocalDate getSuspandEndDate() {
        return suspandEndDate;
    }

    public void setSuspandEndDate(LocalDate suspandEndDate) {
        this.suspandEndDate = suspandEndDate;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public String getStrSuspandStartDate() {
        return strSuspandStartDate;
    }

    public void setStrSuspandStartDate(String strSuspandStartDate) {
        this.strSuspandStartDate = strSuspandStartDate;
    }

    public String getStrSuspandEndDate() {
        return strSuspandEndDate;
    }

    public void setStrSuspandEndDate(String strSuspandEndDate) {
        this.strSuspandEndDate = strSuspandEndDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStrAmount() {
        return strAmount;
    }

    public void setStrAmount(String strAmount) {
        this.strAmount = strAmount;
    }

    public CmsAcademicYearVo getCmsAcademicYearVo() {
        return cmsAcademicYearVo;
    }

    public void setCmsAcademicYearVo(CmsAcademicYearVo cmsAcademicYearVo) {
        this.cmsAcademicYearVo = cmsAcademicYearVo;
    }

    public CmsBranchVo getCmsBranchVo() {
        return cmsBranchVo;
    }

    public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
        this.cmsBranchVo = cmsBranchVo;
    }

    public List<CmsFacility> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsFacility> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsFacility{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", suspandStartDate=" + suspandStartDate +
            ", suspandEndDate=" + suspandEndDate +
            ", academicYearId=" + academicYearId +
            ", branchId=" + branchId +
            ", academicYear=" + academicYear +
            ", branch=" + branch +
            ", cmsAcademicYearVo=" + cmsAcademicYearVo +
            ", cmsBranchVo=" + cmsBranchVo +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            ", strSuspandStartDate='" + strSuspandStartDate + '\'' +
            ", strSuspandEndDate='" + strSuspandEndDate + '\'' +
            ", amount=" + amount +
            ", strAmount='" + strAmount + '\'' +
            '}';
    }
}
