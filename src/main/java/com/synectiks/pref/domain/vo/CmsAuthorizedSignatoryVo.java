package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.synectiks.pref.domain.Branch;

/**
 * A VO for the AuthorizedSignatory entity.
 */
public class CmsAuthorizedSignatoryVo extends CmsCommonVo implements Serializable {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 555954366066641205L;
	private Long id;
    private String name;
    private String fatherName;
    private String designation;
    private String address;
    private String emailId;
    private String cellPhoneNumber;
    private String panNo;
    private CmsBranchVo cmsBranchVo;
    private Long branchId;
    private List<CmsAuthorizedSignatoryVo> dataList = new ArrayList<CmsAuthorizedSignatoryVo>();
    
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
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
	
	
	public List<CmsAuthorizedSignatoryVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsAuthorizedSignatoryVo> dataList) {
		this.dataList = dataList;
	}
	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		this.cmsBranchVo = cmsBranchVo;
	}
	@Override
	public String toString() {
		return "CmsAuthorizedSignatoryVo [id=" + id + ", name=" + name + ", fatherName=" + fatherName + ", designation="
				+ designation + ", address=" + address + ", emailId=" + emailId + ", cellPhoneNumber=" + cellPhoneNumber
				+ ", panNo=" + panNo + ", cmsBranchVo=" + cmsBranchVo + ", branchId=" + branchId + ", dataList="
				+ dataList + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()="
				+ getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
    
    
}
