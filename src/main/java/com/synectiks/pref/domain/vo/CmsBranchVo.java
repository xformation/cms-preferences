package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.State;

/**
 * A VO for the Branch entity.
 */
public class CmsBranchVo extends CmsCommonVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String branchName;
    private String address;
    private String pinCode;
    private String branchHead;
    private String cellPhoneNo;
    private String landLinePhoneNo;
    private String emailId;
    private String faxNo;
    private String isMainBranch;
    private LocalDate startDate;
    private College college;
    private City city;
    private State state;
    private String strStartDate;
    private Long collegeId;
    private Long cityId;
    private Long stateId;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getBranchHead() {
		return branchHead;
	}
	public void setBranchHead(String branchHead) {
		this.branchHead = branchHead;
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
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getIsMainBranch() {
		return isMainBranch;
	}
	public void setIsMainBranch(String isMainBranch) {
		this.isMainBranch = isMainBranch;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public String getStrStartDate() {
		return strStartDate;
	}
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isMainBranch == null) ? 0 : isMainBranch.hashCode());
		result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CmsBranchVo other = (CmsBranchVo) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isMainBranch == null) {
			if (other.isMainBranch != null)
				return false;
		} else if (!isMainBranch.equals(other.isMainBranch))
			return false;
		if (pinCode == null) {
			if (other.pinCode != null)
				return false;
		} else if (!pinCode.equals(other.pinCode))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CmsBranchVo [id=" + id + ", branchName=" + branchName + ", address=" + address + ", pinCode=" + pinCode
				+ ", branchHead=" + branchHead + ", cellPhoneNo=" + cellPhoneNo + ", landLinePhoneNo=" + landLinePhoneNo
				+ ", emailId=" + emailId + ", faxNo=" + faxNo + ", isMainBranch=" + isMainBranch + ", startDate="
				+ startDate + ", college=" + college + ", city=" + city + ", state=" + state + ", strStartDate="
				+ strStartDate + ", collegeId=" + collegeId + ", cityId=" + cityId + ", stateId=" + stateId + "]";
	}
	
	
    
}
