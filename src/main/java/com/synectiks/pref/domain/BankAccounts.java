package com.synectiks.pref.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.pref.utils.IESEntity;

/**
 * A BankAccounts.
 */
@Entity
@Table(name = "bank_accounts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAccounts implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "type_of_account")
    private String typeOfAccount;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "corporate_id")
    private String corporateId;

    @ManyToOne
    @JsonIgnoreProperties("bankAccounts")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public BankAccounts bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BankAccounts accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public BankAccounts typeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
        return this;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public BankAccounts ifscCode(String ifscCode) {
        this.ifscCode = ifscCode;
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public BankAccounts branchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
        return this;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public BankAccounts corporateId(String corporateId) {
        this.corporateId = corporateId;
        return this;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public Branch getBranch() {
        return branch;
    }

    public BankAccounts branch(Branch branch) {
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
        if (!(o instanceof BankAccounts)) {
            return false;
        }
        return id != null && id.equals(((BankAccounts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BankAccounts{" +
            "id=" + getId() +
            ", bankName='" + getBankName() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", typeOfAccount='" + getTypeOfAccount() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", branchAddress='" + getBranchAddress() + "'" +
            ", corporateId='" + getCorporateId() + "'" +
            "}";
    }
}
