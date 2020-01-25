package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.synectiks.pref.domain.enumeration.SectionEnum;

/**
 * A DTO for the Section entity.
 */
public class CmsSectionVo extends CmsCommonVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private SectionEnum section;
    private Long batchId;
    private CmsBatchVo cmsBatchVo;
    private String description;
    private List<CmsSectionVo> dataList = new ArrayList<CmsSectionVo>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SectionEnum getSection() {
		return section;
	}
	public void setSection(SectionEnum section) {
		this.section = section;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public CmsBatchVo getCmsBatchVo() {
		return cmsBatchVo;
	}
	public void setCmsBatchVo(CmsBatchVo cmsBatchVo) {
		this.cmsBatchVo = cmsBatchVo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CmsSectionVo [id=" + id + ", section=" + section + ", batchId=" + batchId + ", cmsBatchVo=" + cmsBatchVo
				+ ", description=" + description + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()="
				+ getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn()
				+ ", getStatus()=" + getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()="
				+ getStrUpdatedOn() + ", getExitCode()=" + getExitCode() + ", getExitDescription()="
				+ getExitDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public List<CmsSectionVo> getDataList() {
		return dataList;
	}
	public void setDataList(List<CmsSectionVo> dataList) {
		this.dataList = dataList;
	}
    
    

    
}
