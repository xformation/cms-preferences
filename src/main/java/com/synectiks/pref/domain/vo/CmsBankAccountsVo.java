package com.synectiks.pref.domain.vo;

import java.io.Serializable;

import com.synectiks.pref.domain.Branch;

/**
 * A Vo for the BankAccounts entity.
 */
public class CmsBankAccountsVo extends CmsCommonVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4885536573081187243L;
	private Long id;
    private String bankName;
    private String accountNumber;
    private String typeOfAccount;
    private String ifscCode;
    private String address;
    private String corporateId;
    private Branch branch;
    private Long branchId;
    
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
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	@Override
	public String toString() {
		return "CmsBankAccountsVo [id=" + id + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", typeOfAccount=" + typeOfAccount + ", ifscCode=" + ifscCode + ", address=" + address
				+ ", corporateId=" + corporateId + ", branch=" + branch + ", branchId=" + branchId + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()=" + getStatus() + ", getStrCreatedOn()="
				+ getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
    
}
