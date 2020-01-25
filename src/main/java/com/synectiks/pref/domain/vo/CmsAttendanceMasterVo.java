package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsAttendanceMasterVo extends CmsCommonVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String desc;
    private CmsBatchVo cmsBatchVo;
    private CmsSectionVo cmsSectionVo;
    private CmsTeachVo cmsTeachVo;
    
    private Long batchId;
    private Long sectionId;
    private Long teachId;
    
    private List<CmsAttendanceMasterVo> dataList = new ArrayList<CmsAttendanceMasterVo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public CmsBatchVo getCmsBatchVo() {
		return cmsBatchVo;
	}

	public void setCmsBatchVo(CmsBatchVo cmsBatchVo) {
		this.cmsBatchVo = cmsBatchVo;
	}

	public CmsSectionVo getCmsSectionVo() {
		return cmsSectionVo;
	}

	public void setCmsSectionVo(CmsSectionVo cmsSectionVo) {
		this.cmsSectionVo = cmsSectionVo;
	}

	public CmsTeachVo getCmsTeachVo() {
		return cmsTeachVo;
	}

	public void setCmsTeachVo(CmsTeachVo cmsTeachVo) {
		this.cmsTeachVo = cmsTeachVo;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getTeachId() {
		return teachId;
	}

	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}

	public List<CmsAttendanceMasterVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<CmsAttendanceMasterVo> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "CmsAttendanceMasterVo [id=" + id + ", desc=" + desc + ", cmsBatchVo=" + cmsBatchVo + ", cmsSectionVo="
				+ cmsSectionVo + ", cmsTeachVo=" + cmsTeachVo + ", batchId=" + batchId + ", sectionId=" + sectionId
				+ ", teachId=" + teachId + ", dataList=" + dataList + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()="
				+ getUpdatedOn() + ", getStatus()=" + getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn()
				+ ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
    
	
    
	
}
