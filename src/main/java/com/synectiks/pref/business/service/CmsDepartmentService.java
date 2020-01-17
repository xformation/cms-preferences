package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.graphql.types.department.DepartmentInput;
import com.synectiks.pref.repository.DepartmentRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsDepartmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    CmsBranchService cmsBranchService;

    @Autowired
    CmsAcademicYearService cmsAcademicYearService;
    
    public List<CmsDepartmentVo> getCmsDepartmentListOnFilterCriteria(Map<String, String> criteriaMap){
    	Department dp = new Department();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		dp.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		dp.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("name") != null) {
    		dp.setName(criteriaMap.get("name"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		dp.setDescription(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("deptHead") != null) {
    		dp.setDeptHead(criteriaMap.get("deptHead"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("comments") != null) {
    		dp.setComments(criteriaMap.get("comments"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchId") != null) {
    		Branch br = this.cmsBranchService.getBranch(Long.parseLong(criteriaMap.get("branchId")));
    		dp.setBranch(br);
    		isFilter = true;
    	}
    	
    	if(criteriaMap.get("academicYearId") != null) {
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
    		dp.setAcademicYear(ay);
    		isFilter = true;
    	}
    	
    	List<Department> list = null;
    	if(isFilter) {
    		list = this.departmentRepository.findAll(Example.of(dp), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.departmentRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsDepartmentVo> ls = changeDepartmentToCmsDepartmentList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Department> getDepartmentListOnFilterCriteria(Map<String, String> criteriaMap){
    	Department dp = new Department();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		dp.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		dp.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("name") != null) {
    		dp.setName(criteriaMap.get("name"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		dp.setDescription(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("deptHead") != null) {
    		dp.setDeptHead(criteriaMap.get("deptHead"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("comments") != null) {
    		dp.setComments(criteriaMap.get("comments"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchId") != null) {
    		Branch br = this.cmsBranchService.getBranch(Long.parseLong(criteriaMap.get("branchId")));
    		dp.setBranch(br);
    		isFilter = true;
    	}
    	
    	if(criteriaMap.get("academicYearId") != null) {
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
    		dp.setAcademicYear(ay);
    		isFilter = true;
    	}
    	
    	List<Department> list = null;
    	if(isFilter) {
    		list = this.departmentRepository.findAll(Example.of(dp), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.departmentRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public List<CmsDepartmentVo> getCmsDepartmentList(String status) {
    	Department department = new Department();
        department.setStatus(status);
        List<Department> list = this.departmentRepository.findAll(Example.of(department));
        List<CmsDepartmentVo> ls = changeDepartmentToCmsDepartmentList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    public List<Department> getDepartmentList(String status) {
    	Department department = new Department();
        department.setStatus(status);
        List<Department> list = this.departmentRepository.findAll(Example.of(department));
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }
    
    public List<CmsDepartmentVo> getCmsDepartmentList(){
    	List<Department> list = this.departmentRepository.findAll();
    	List<CmsDepartmentVo> ls = changeDepartmentToCmsDepartmentList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    public List<Department> getDepartmentList(){
    	List<Department> list = this.departmentRepository.findAll();
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    public CmsDepartmentVo getCmsDepartment(Long id){
    	Optional<Department> br = this.departmentRepository.findById(id);
    	if(br.isPresent()) {
    		CmsDepartmentVo vo = CommonUtil.createCopyProperties(br.get(), CmsDepartmentVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsDepartment for given id : "+id+". CmsDepartment object : "+vo);
        	return vo;
    	}
    	logger.debug("Department object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Department getDepartment(Long id){
    	Optional<Department> br = this.departmentRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Department object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    private List<CmsDepartmentVo> changeDepartmentToCmsDepartmentList(List<Department> list){
    	List<CmsDepartmentVo> ls = new ArrayList<>();
    	for(Department o: list) {
    		CmsDepartmentVo vo = CommonUtil.createCopyProperties(o, CmsDepartmentVo.class);
    		convertDatesAndProvideDependencies(o, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Department o, CmsDepartmentVo vo) {
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
    		AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(input.getAcademicYearId());
			department.setAcademicYear(ay);
    		
			Branch b = this.cmsBranchService.getBranch(input.getBranchId());
			department.setBranch(b);
    		
			department.setName(input.getName());
			department.setDeptHead(input.getDeptHead());
			department.setStatus(input.getStatus());
    		
    		department = departmentRepository.save(department);
        	
        	vo = CommonUtil.createCopyProperties(department, CmsDepartmentVo.class);
        	vo.setStrCreatedOn(department.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(department.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(department.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(department.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
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
    	List ls =  getCmsDepartmentList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
