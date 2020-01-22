package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.Employee;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.domain.vo.CmsEmployeeVo;
import com.synectiks.pref.graphql.types.employee.EmployeeInput;
import com.synectiks.pref.graphql.types.teacher.TeacherInput;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.repository.DepartmentRepository;
import com.synectiks.pref.repository.EmployeeRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsEmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    EmployeeRepository employeeRepository;
	
	@Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    BranchRepository branchRepository;
    
    @Autowired
    CmsBranchService cmsBranchService; 

    @Autowired
    CmsDepartmentService cmsDepartmentService; 

    
    public List<CmsEmployeeVo> getCmsEmployeeList(String status) {
    	Employee employee = new Employee();
        employee.setStatus(status);
        List<Employee> list = this.employeeRepository.findAll(Example.of(employee));
        List<CmsEmployeeVo> ls = changeEmployeeToCmsEmployeeList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<CmsEmployeeVo> getEmployeeList(){
    	List<Employee> list = this.employeeRepository.findAll();
    	List<CmsEmployeeVo> ls = changeEmployeeToCmsEmployeeList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public CmsEmployeeVo getCmsEmployee(Long id){
    	Optional<Employee> ole = this.employeeRepository.findById(id);
    	CmsEmployeeVo vo = null;
    	if(ole.isPresent()) {
    		vo = CommonUtil.createCopyProperties(ole.get(), CmsEmployeeVo.class);
    		convertDatesAndProvideDependencies(ole.get(), vo);
    	}
    	return vo;
    }
    
    private List<CmsEmployeeVo> changeEmployeeToCmsEmployeeList(List<Employee> list){
    	List<CmsEmployeeVo> ls = new ArrayList<>();
    	for(Employee o: list) {
    		CmsEmployeeVo vo = CommonUtil.createCopyProperties(o, CmsEmployeeVo.class);
    		convertDatesAndProvideDependencies(o, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Employee o, CmsEmployeeVo vo) {
//		if(o.getDateOfBirth() != null) {
//			vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(o.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//		}
		if(o.getJoiningDate() != null) {
			vo.setStrJoiningDate(DateFormatUtil.changeLocalDateFormat(o.getJoiningDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getJobEndDate() != null) {
			vo.setStrJobEndDate(DateFormatUtil.changeLocalDateFormat(o.getJobEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getResignationDate() != null) {
			vo.setStrResignationDate(DateFormatUtil.changeLocalDateFormat(o.getResignationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getResignationAcceptanceDate() != null) {
			vo.setStrResignationAcceptanceDate(DateFormatUtil.changeLocalDateFormat(o.getResignationAcceptanceDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getDepartment() != null) {
			vo.setDepartmentId(o.getDepartment().getId());
			CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(o.getDepartment(), CmsDepartmentVo.class); 
			vo.setCmsDepartmentVo(cmsDvo);
		}
		if(o.getBranch() != null) {
			vo.setBranchId(o.getBranch().getId());
			CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(o.getBranch(), CmsBranchVo.class); 
			vo.setCmsBranchVo(cmsBvo);
		}
	}
    
    public CmsEmployeeVo saveEmployee(EmployeeInput input) {
    	logger.info("Saving employee");
    	CmsEmployeeVo vo = null;
    	try {
    		Employee employee = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new employee");
    			employee = CommonUtil.createCopyProperties(input, Employee.class);
    			if(!CommonUtil.isNullOrEmpty(input.getStrDateOfBirth())) {
    				employee.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(input.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			}
    			if(!CommonUtil.isNullOrEmpty(input.getStrJoiningDate())) {
    				employee.setJoiningDate(DateFormatUtil.convertStringToLocalDate(input.getStrJoiningDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			}else {
    				employee.setJoiningDate(LocalDate.now());
    			}
//    			if(!CommonUtil.isNullOrEmpty(input.getStrJobEndDate())) {
//    				vo.setJobEndDate(DateFormatUtil.convertStringToLocalDate(input.getStrJobEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//        		}
//    			if(!CommonUtil.isNullOrEmpty(input.getStrResignationDate())) {
//    				vo.setResignationDate(DateFormatUtil.convertStringToLocalDate(input.getStrResignationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//    			}
//    			if(!CommonUtil.isNullOrEmpty(input.getStrResignationAcceptanceDate())) {
//    				vo.setResignationAcceptanceDate(DateFormatUtil.convertStringToLocalDate(input.getStrResignationAcceptanceDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//        		}
    			
    			if(input.getDepartmentId() != null) {
    				Department dp = this.departmentRepository.findById(input.getDepartmentId()).get();
    				employee.setDepartment(dp);
    			}
    			if(input.getBranchId() != null) {
    				Branch b = this.branchRepository.findById(input.getBranchId()).get();
    				employee.setBranch(b);
    			}
    		}else {
    			logger.debug("Updating existing course");
    			employee = this.employeeRepository.findById(input.getId()).get();
    			
    			if(input.getEmployeeName() != null) {
    				employee.setEmployeeName(input.getEmployeeName());
    			}
//    			if(input.getEmployeeMiddleName() != null) {
//    				employee.setEmployeeMiddleName(input.getEmployeeMiddleName());
//    			}
//    			if(input.getEmployeeLastName() != null) {
//    				employee.setEmployeeLastName(input.getEmployeeLastName());
//    			}
    			
    			if(input.getEmployeeFatherName() != null) {
    				employee.setEmployeeFatherName(input.getEmployeeFatherName());
    			}
//    			if(input.getFatherMiddleName() != null) {
//    				employee.setFatherMiddleName(input.getFatherMiddleName());
//    			}
//    			if(input.getFatherLastName() != null) {
//    				employee.setFatherLastName(input.getFatherLastName());
//    			}
    			
    			if(input.getEmployeeMotherName() != null) {
    				employee.setEmployeeMotherName(input.getEmployeeMotherName());
    			}
//    			if(input.getMotherMiddleName() != null) {
//    				employee.setMotherMiddleName(input.getMotherMiddleName());
//    			}
//    			if(input.getMotherLastName() != null) {
//    				employee.setMotherLastName(input.getMotherLastName());
//    			}
//    			
//    			if(input.getSpouseName() != null) {
//    				employee.setSpouseName(input.getSpouseName());
//    			}
//    			if(input.getSpouseMiddleName() != null) {
//    				employee.setSpouseMiddleName(input.getSpouseMiddleName());
//    			}
//    			if(input.getSpouseLastName() != null) {
//    				employee.setSpouseLastName(input.getSpouseLastName());
//    			}
    			
//    			if(input.getPlaceOfBirth() != null) {
//    				employee.setPlaceOfBirth(input.getPlaceOfBirth());
//    			}
//    			if(input.getReligion() != null) {
//    				employee.setReligion(input.getReligion());
//    			}
//    			if(input.getCaste() != null) {
//    				employee.setCaste(input.getCaste());
//    			}
//    			
//    			if(input.getSubCaste() != null) {
//    				employee.setSubCaste(input.getSubCaste());
//    			}
//    			if(input.getGender() != null) {
//    				employee.setGender(input.getGender());
//    			}
//    			if(input.getBloodGroup() != null) {
//    				employee.setBloodGroup(input.getBloodGroup());
//    			}
    			
//    			if(input.getPinCode() != null) {
//    				employee.setPinCode(input.getPinCode());
//    			}
//    			if(input.getRelationOfEmergencyContact() != null) {
//    				employee.setRelationOfEmergencyContact(input.getRelationOfEmergencyContact());
//    			}
//    			if(input.getEmergencyContactName() != null) {
//    				employee.setEmergencyContactName(input.getEmergencyContactName());
//    			}
//    			if(input.getEmergencyContactMiddleName() != null) {
//    				employee.setEmergencyContactMiddleName(input.getEmergencyContactMiddleName());
//    			}
//    			if(input.getEmergencyContactLastName() != null) {
//    				employee.setEmergencyContactLastName(input.getEmergencyContactLastName());
//    			}
//    			if(input.getEmergencyContactNo() != null) {
//    				employee.setEmergencyContactNo(input.getEmergencyContactNo());
//    			}
//    			if(input.getEmergencyContactEmailAddress() != null) {
//    				employee.setEmergencyContactEmailAddress(input.getEmergencyContactEmailAddress());
//    			}
    			
//    			if(input.getStatus() != null) {
//    				employee.setStatus(input.getStatus());
//    			}
//    			if(input.getStaffType() != null) {
//    				employee.setStaffType(input.getStaffType());
//    			}
    			if(input.getDesignation() != null) {
    				employee.setDesignation(input.getDesignation());
    			}
    			
    			if(input.getAadharNo() != null) {
    				employee.setAadharNo(input.getAadharNo());
    			}
    			if(input.getPanNo() != null) {
    				employee.setPanNo(input.getPanNo());
    			}
    			if(input.getPassportNo() != null) {
    				employee.setPassportNo(input.getPassportNo());
    			}
    			
    			if(input.getPrimaryContactNo() != null) {
    				employee.setPrimaryContactNo(input.getPrimaryContactNo());
    			}
    			if(input.getSecondaryContactNo() != null) {
    				employee.setSecondaryContactNo(input.getSecondaryContactNo());
    			}
    			if(input.getPrimaryAddress() != null) {
    				employee.setPrimaryAddress(input.getPrimaryAddress());
    			}
    			
    			if(input.getSecondaryAddress() != null) {
    				employee.setSecondaryAddress(input.getSecondaryAddress());
    			}
    			if(input.getPersonalMailId() != null) {
    				employee.setPersonalMailId(input.getPersonalMailId());
    			}
    			if(input.getOfficialMailId() != null) {
    				employee.setOfficialMailId(input.getOfficialMailId());
    			}
    			
    			if(input.getDrivingLicenceNo() != null) {
    				employee.setDrivingLicenceNo(input.getDrivingLicenceNo());
    			}
    			if(input.getTypeOfEmployment() != null) {
    				employee.setTypeOfEmployment(input.getTypeOfEmployment());
    			}
    			if(input.getManagerId() != null) {
    				employee.setManagerId(input.getManagerId());
    			}
    			
    			if(input.getMaritalStatus() != null) {
    				employee.setMaritalStatus(input.getMaritalStatus());
    			}
    			vo.setDateOfBirth(input.getStrDateOfBirth() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
    			vo.setJoiningDate(input.getStrJoiningDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrJoiningDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
//    			vo.setJobEndDate(input.getStrJobEndDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrJobEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
//    			vo.setResignationDate(input.getStrResignationDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrResignationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
//    			vo.setResignationAcceptanceDate(input.getStrResignationAcceptanceDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrResignationAcceptanceDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
//    			
    			if(input.getDepartmentId() != null) {
    				Department dp = this.departmentRepository.findById(input.getDepartmentId()).get();
    				employee.setDepartment(dp);
    			}
    			if(input.getBranchId() != null) {
    				Branch b = this.branchRepository.findById(input.getBranchId()).get();
    				employee.setBranch(b);
    			}
    		}
    		
    		employee = this.employeeRepository.save(employee);
        	
        	vo = CommonUtil.createCopyProperties(employee, CmsEmployeeVo.class);
        	
//        	vo.setStrDateOfBirth(employee.getDateOfBirth() != null ? DateFormatUtil.changeLocalDateFormat(employee.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
			vo.setStrJoiningDate(input.getJoiningDate() != null ? DateFormatUtil.changeLocalDateFormat(employee.getJoiningDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
			vo.setStrJobEndDate(input.getJobEndDate() != null ? DateFormatUtil.changeLocalDateFormat(employee.getJobEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
			vo.setStrResignationDate(input.getResignationDate() != null ? DateFormatUtil.changeLocalDateFormat(employee.getResignationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
			vo.setStrResignationAcceptanceDate(input.getResignationAcceptanceDate() != null ? DateFormatUtil.changeLocalDateFormat(employee.getResignationAcceptanceDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
		
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setDateOfBirth(null);
        	vo.setJoiningDate(null);
        	vo.setJobEndDate(null);
        	vo.setResignationDate(null);
        	vo.setResignationAcceptanceDate(null);
        	
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("Employee is added successfully");
        		logger.debug("Employee is added successfully");
        	}else {
        		vo.setExitDescription("Employee is updated successfully");
        		logger.debug("Employee is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsEmployeeVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, employee data not be saved");
    		logger.error("Employee save failed. Exception : ",e);
    	}
    	logger.info("Employee saved successfully");
    	List ls =  getEmployeeList();
        vo.setDataList(ls);
    	return vo;
        
    }
    
    EmployeeInput convertCmsTeacherToCmsEmployee(TeacherInput tInp) {
    	EmployeeInput eInp = new EmployeeInput();
    	eInp.setId(tInp.getId());
    	String empName = tInp.getTeacherName();
    	if(!CommonUtil.isNullOrEmpty(tInp.getTeacherMiddleName())){
    		empName = empName + " "+ tInp.getTeacherMiddleName();
    	}
    	if(!CommonUtil.isNullOrEmpty(tInp.getTeacherLastName())){
    		empName = empName + " "+ tInp.getTeacherLastName();
    	}
    	eInp.setEmployeeName(empName);
    	
    	if(!CommonUtil.isNullOrEmpty(tInp.getDesignation())) {
    		eInp.setDesignation(tInp.getDesignation());
    	}
    	
        eInp.setJoiningDate(LocalDate.now());
        if(!CommonUtil.isNullOrEmpty(tInp.getStrDateOfBirth())) {
        	eInp.setStrDateOfBirth(tInp.getStrDateOfBirth());
    	}
        if(!CommonUtil.isNullOrEmpty(tInp.getStrDateOfBirth())) {
        	eInp.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(tInp.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    	}
        if(!CommonUtil.isNullOrEmpty(tInp.getAadharNo())) {
        	eInp.setAadharNo(tInp.getAadharNo());
    	}
        if(!CommonUtil.isNullOrEmpty(tInp.getPanNo())) {
        	eInp.setPanNo(tInp.getPanNo());
    	}
        
//        vo.setPassportNo(null);
        if(!CommonUtil.isNullOrEmpty(tInp.getTeacherContactNumber())) {
        	eInp.setPrimaryContactNo(tInp.getTeacherContactNumber());
    	}
        if(!CommonUtil.isNullOrEmpty(tInp.getAlternateContactNumber())) {
        	eInp.setSecondaryContactNo(tInp.getAlternateContactNumber());
    	}
        String eFname = tInp.getFatherName();
        if(!CommonUtil.isNullOrEmpty(tInp.getFatherMiddleName())) {
        	eFname = eFname + " "+tInp.getFatherMiddleName();
    	}
        if(!CommonUtil.isNullOrEmpty(tInp.getFatherLastName())) {
        	eFname = eFname + " "+tInp.getFatherLastName();
    	}
        eInp.setEmployeeFatherName(eFname);
        
        String eMname = tInp.getMotherName();
        if(!CommonUtil.isNullOrEmpty(tInp.getFatherMiddleName())) {
        	eMname = eMname + " "+tInp.getMotherMiddleName();
    	}
        if(!CommonUtil.isNullOrEmpty(tInp.getMotherLastName())) {
        	eMname = eMname + " "+tInp.getMotherLastName();
    	}
        eInp.setEmployeeMotherName(eMname);
        
        if(!CommonUtil.isNullOrEmpty(tInp.getAddress())) {
        	eInp.setPrimaryAddress(tInp.getAddress());
    	}
        
//        vo.setSecondaryAddress(null);
//        private String employeeAddress;
        if(!CommonUtil.isNullOrEmpty(tInp.getTeacherEmailAddress())) {
        	eInp.setPersonalMailId(tInp.getTeacherEmailAddress());
    	}
        
//        private String officialMailId;
//        private String disability;
//        private String drivingLicenceNo;
//        private LocalDate drivingLicenceValidity;
        if(!CommonUtil.isNullOrEmpty(tInp.getSex())) {
        	eInp.setGender(tInp.getSex());
    	}
        
//        private String typeOfEmployment;
//        private Long managerId;
        if(!CommonUtil.isNullOrEmpty(tInp.getStatus())) {
        	eInp.setStatus(tInp.getStatus());
    	}
        
//        private String maritalStatus;
//        private String strJobEndDate;
//        private String strResignationDate;
//        private String strResignationAcceptanceDate;
//        private String strDrivingLicenceValidity;
        if(!CommonUtil.isNullOrEmpty(tInp.getStaffType())) {
        	eInp.setStaffType(tInp.getStaffType());
    	}
        
        if(tInp.getBranchId() != null) {
			eInp.setBranchId(tInp.getBranchId());
			Branch branch = cmsBranchService.getBranch(tInp.getBranchId());
			CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(branch, CmsBranchVo.class); 
			eInp.setCmsBranchVo(cmsBvo);
		}
        if(tInp.getDepartmentId() != null) {
			eInp.setDepartmentId(tInp.getDepartmentId());
			Department department = cmsDepartmentService.getDepartment(tInp.getDepartmentId());
			CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(department, CmsDepartmentVo.class); 
			eInp.setCmsDepartmentVo(cmsDvo);
		}
		
    	return eInp;
    }
}
