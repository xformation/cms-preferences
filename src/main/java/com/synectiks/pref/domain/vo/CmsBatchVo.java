package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.synectiks.pref.domain.enumeration.BatchEnum;

/**
 * A Vo for the Batch entity.
 */
public class CmsBatchVo extends CmsCommonVo implements Serializable {

    private Long id;
    private BatchEnum batch;
    private String description;
    private Long departmentId;
    private CmsDepartmentVo cmsDepartmentVo;
    private List<CmsBatchVo> dataList = new ArrayList<CmsBatchVo>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BatchEnum getBatch() {
        return batch;
    }

    public void setBatch(BatchEnum batch) {
        this.batch = batch;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CmsDepartmentVo getCmsDepartmentVo() {
		return cmsDepartmentVo;
	}

	public void setCmsDepartmentVo(CmsDepartmentVo cmsDepartmentVo) {
		this.cmsDepartmentVo = cmsDepartmentVo;
	}

	public List<CmsBatchVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<CmsBatchVo> dataList) {
		this.dataList = dataList;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "CmsBatchVo [id=" + id + ", batch=" + batch + ", description=" + description + ", departmentId="
				+ departmentId + ", cmsDepartmentVo=" + cmsDepartmentVo + ", dataList=" + dataList + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedOn()=" + getUpdatedOn() + ", getStatus()=" + getStatus() + ", getStrCreatedOn()="
				+ getStrCreatedOn() + ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
