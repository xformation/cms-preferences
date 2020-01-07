package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Vo for the BankAccounts entity.
 */
public class CmsBankAccountsVo extends CmsCommonVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3917727524853509670L;
	private Long id;
    private String bankName;
    private String accountNumber;
    private String typeOfAccount;
    private String ifscCode;
    private String address;
    private String corporateId;
    private CmsBranchVo cmsBranchVo;
    private Long branchId;
    private List<CmsBankAccountsVo> dataList = new ArrayList<CmsBankAccountsVo>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCorporateId() {
		return corporateId;
	}
	public void setCorporateId(String corporateId) {
		this.corporateId = corporateId;
	}
	public CmsBranchVo getCmsBranchVo() {
		return cmsBranchVo;
	}
	public void setCmsBranchVo(CmsBranchVo cmsBranchVo) {
		this.cmsBranchVo = cmsBranchVo;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public List<CmsBankAccountsVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsBankAccountsVo> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "CmsBankAccountsVo [id=" + id + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", typeOfAccount=" + typeOfAccount + ", ifscCode=" + ifscCode + ", address=" + address
				+ ", corporateId=" + corporateId + ", cmsBranchVo=" + cmsBranchVo + ", branchId=" + branchId
				+ ", dataList=" + dataList + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()="
				+ getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn()
				+ ", getExitCode()=" + getExitCode() + ", getExitDescription()=" + getExitDescription()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
	
    
}
