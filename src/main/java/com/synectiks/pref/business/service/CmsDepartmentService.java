package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.graphql.types.department.DepartmentInput;
import com.synectiks.pref.repository.AcademicYearRepository;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.repository.DepartmentRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsDepartmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    AcademicYearRepository academicYearRepository;

    @Autowired
    BranchRepository branchRepository;

    public List<CmsDepartmentVo> getDepartmentList(String status) {
    	Department department = new Department();
        department.setStatus(status);
        List<Department> list = this.departmentRepository.findAll(Example.of(department));
        List<CmsDepartmentVo> ls = changeDepartmentToCmsDepartmentList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<CmsDepartmentVo> getDepartmentList(){
    	List<Department> list = this.departmentRepository.findAll();
    	List<CmsDepartmentVo> ls = changeDepartmentToCmsDepartmentList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    private List<CmsDepartmentVo> changeDepartmentToCmsDepartmentList(List<Department> list){
    	List<CmsDepartmentVo> ls = new ArrayList<>();
    	for(Department o: list) {
    		CmsDepartmentVo vo = CommonUtil.createCopyProperties(o, CmsDepartmentVo.class);
    		if(o.getCreatedOn() != null) {
            	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(o.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(o.getUpdatedOn() != null) {
            	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(o.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(o.getAcademicYear() != null) {
            	vo.setAcademicYearId(o.getAcademicYear().getId());
            	CmsAcademicYearVo cmsAvo =CommonUtil.createCopyProperties(o.getAcademicYear(), CmsAcademicYearVo.class); 
        		vo.setCmsAcademicYearVo(cmsAvo);
            }
            if(o.getBranch() != null) {
            	vo.setBranchId(o.getBranch().getId());
            	CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(o.getBranch(), CmsBranchVo.class); 
        		vo.setCmsBranchVo(cmsBvo);
            }
    		ls.add(vo);
    	}
    	return ls;
    }
    
    public CmsDepartmentVo saveDepartment(DepartmentInput input) {
    	logger.info("Saving department");
    	CmsDepartmentVo vo = null;
    	try {
    		Department department = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new department");
    			department = CommonUtil.createCopyProperties(input, Department.class);
    			department.setCreatedOn(LocalDate.now());
    			department.setComments(input.getComments());
    		}else {
    			logger.debug("Updating existing department");
    			department = this.departmentRepository.findById(input.getId()).get();
    			department.setUpdatedOn(LocalDate.now());
    			if(input.getComments() != null) {
    				department.setComments(input.getComments());
    			}
    		}
    		AcademicYear ay = this.academicYearRepository.findById(input.getAcademicYearId()).get();
			department.setAcademicYear(ay);
    		
			Branch b = this.branchRepository.findById(input.getBranchId()).get();
			department.setBranch(b);
    		
			department.setName(input.getName());
			department.setDeptHead(input.getDeptHead());
			department.setStatus(input.getStatus());
    		
    		department = departmentRepository.save(department);
        	
        	vo = CommonUtil.createCopyProperties(department, CmsDepartmentVo.class);
        	vo.setStrCreatedOn(department.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(department.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(department.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(department.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("Department is added successfully");
        		logger.debug("Department is added successfully");
        	}else {
        		vo.setExitDescription("Department is updated successfully");
        		logger.debug("Department is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsDepartmentVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, department data not be saved");
    		logger.error("Department save failed. Exception : ",e);
    	}
    	logger.info("Department saved successfully");
    	List ls =  getDepartmentList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
