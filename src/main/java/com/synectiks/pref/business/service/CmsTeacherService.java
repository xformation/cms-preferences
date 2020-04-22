package com.synectiks.pref.business.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.domain.vo.CmsEmployeeVo;
import com.synectiks.pref.domain.vo.CmsTeacherVo;
import com.synectiks.pref.graphql.types.employee.EmployeeInput;
import com.synectiks.pref.graphql.types.teacher.TeacherInput;
import com.synectiks.pref.repository.TeacherRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;
import com.synectiks.pref.utils.ESEvent;
import com.synectiks.pref.utils.IESEntity;
import com.synectiks.pref.utils.IUtils;
import com.synectiks.pref.utils.ESEvent.EventType;

@Component
public class CmsTeacherService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CmsDepartmentService cmsDepartmentService;

    @Autowired
    CmsBranchService cmsBranchService;

    @Autowired
    CmsEmployeeService cmsEmployeeService;
    
    public List<CmsTeacherVo> getCmsTeacherListOnFilterCriteria(Map<String, String> criteriaMap){
    	Teacher obj = new Teacher();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchId") != null) {
    		Branch branch = cmsBranchService.getBranch(Long.parseLong(criteriaMap.get("branchId")));
    		obj.setBranch(branch);
    		isFilter = true;
    	}
    	if(criteriaMap.get("departmentId") != null) {
    		Department department = cmsDepartmentService.getDepartment(Long.parseLong(criteriaMap.get("departmentId")));
    		obj.setDepartment(department);
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		obj.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("teacherName") != null) {
    		obj.setTeacherName(criteriaMap.get("teacherName"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("address") != null) {
    		obj.setAddress(criteriaMap.get("address"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("pinCode") != null) {
    		obj.setPinCode(criteriaMap.get("pinCode"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("teacherEmailAddress") != null) {
    		obj.setTeacherEmailAddress(criteriaMap.get("teacherEmailAddress"));
    		isFilter = true;
    	}
    	List<Teacher> list = null;
    	if(isFilter) {
    		list = this.teacherRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.teacherRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsTeacherVo> ls = changeTeacherToCmsTeacherList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Teacher> getTeacherListOnFilterCriteria(Map<String, String> criteriaMap){
    	Teacher obj = new Teacher();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("branchId") != null) {
    		Branch branch = cmsBranchService.getBranch(Long.parseLong(criteriaMap.get("branchId")));
    		obj.setBranch(branch);
    		isFilter = true;
    	}
    	if(criteriaMap.get("departmentId") != null) {
    		Department department = cmsDepartmentService.getDepartment(Long.parseLong(criteriaMap.get("departmentId")));
    		obj.setDepartment(department);
    		isFilter = true;
    	}
    	
    	if(criteriaMap.get("status") != null) {
    		obj.setStatus(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("teacherName") != null) {
    		obj.setTeacherName(criteriaMap.get("teacherName"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("address") != null) {
    		obj.setAddress(criteriaMap.get("address"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("pinCode") != null) {
    		obj.setPinCode(criteriaMap.get("pinCode"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("teacherEmailAddress") != null) {
    		obj.setTeacherEmailAddress(criteriaMap.get("teacherEmailAddress"));
    		isFilter = true;
    	}
    	List<Teacher> list = null;
    	if(isFilter) {
    		list = this.teacherRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.teacherRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public List<CmsTeacherVo> getCmsTeacherList(String status) {
        Teacher teacher = new Teacher();
        teacher.setStatus(status);
        List<Teacher> list = this.teacherRepository.findAll(Example.of(teacher));
        List<CmsTeacherVo> ls = changeTeacherToCmsTeacherList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<Teacher> getTeacherList(String status) {
        Teacher teacher = new Teacher();
        teacher.setStatus(status);
        List<Teacher> list = this.teacherRepository.findAll(Example.of(teacher));
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }
    
    public List<CmsTeacherVo> getCmsTeacherList(){
    	List<Teacher> list = this.teacherRepository.findAll();
    	List<CmsTeacherVo> ls = changeTeacherToCmsTeacherList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Teacher> getTeacherList(){
    	List<Teacher> list = this.teacherRepository.findAll();
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public CmsTeacherVo getCmsTeacher(Long id){
    	Optional<Teacher> br = this.teacherRepository.findById(id);
    	if(br.isPresent()) {
    		CmsTeacherVo vo = CommonUtil.createCopyProperties(br.get(), CmsTeacherVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsTeacher for given id : "+id+". CmsTeacher object : "+vo);
        	return vo;
    	}
    	logger.debug("Teacher object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Teacher getTeacher(Long id){
    	Optional<Teacher> br = this.teacherRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Teacher object not found for the given id. "+id+". Returning null");
        return null;
    }
    private List<CmsTeacherVo> changeTeacherToCmsTeacherList(List<Teacher> list){
    	List<CmsTeacherVo> ls = new ArrayList<>();
    	for(Teacher tr: list) {
    		CmsTeacherVo vo = CommonUtil.createCopyProperties(tr, CmsTeacherVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Teacher tr, CmsTeacherVo vo) {
		if(tr.getDateOfBirth() != null) {
			vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(tr.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
			vo.setUpdatedOn(null);
		}
		if(tr.getBranch() != null) {
			vo.setBranchId(tr.getBranch().getId());
			CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(tr.getBranch(), CmsBranchVo.class); 
			vo.setCmsBranchVo(cmsBvo);
		}
		if(tr.getDepartment() != null) {
			vo.setDepartmentId(tr.getDepartment().getId());
			CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(tr.getDepartment(), CmsDepartmentVo.class); 
			vo.setCmsDepartmentVo(cmsDvo);
		}
	}
    
    public CmsTeacherVo saveTeacher(TeacherInput inp) {
    	EmployeeInput empInp = cmsEmployeeService.convertCmsTeacherToCmsEmployee(inp);
		CmsEmployeeVo empVo = cmsEmployeeService.saveEmployee(empInp);
    	if(CmsConstants.STAFF_TYPE_NONTEACHING.equalsIgnoreCase(inp.getStaffType())) {
    		List<CmsEmployeeVo> empVoList = empVo.getDataList();
    		List<CmsTeacherVo> tVoList = new ArrayList<>();
    		for(CmsEmployeeVo evo: empVoList) {
    			CmsTeacherVo tvo = convertCmsEmployeeToCmsTeacher(evo);
    			tVoList.add(tvo);
    		}
    		tVoList.addAll(getCmsTeacherList());
    		CmsTeacherVo cmsTvo = convertCmsEmployeeToCmsTeacher(empVo);
    		cmsTvo.setDataList(tVoList);
    		return cmsTvo;
    	}
    	
    	logger.info("Saving teacher");
    	CmsTeacherVo vo = null;
    	try {
    		Teacher teacher = null;
    		if(inp.getId() == null) {
    			logger.debug("Adding new teacher");
    			teacher = CommonUtil.createCopyProperties(inp, Teacher.class);
    			teacher.setEmployeeId(empVo.getId());
    		}else {
    			logger.debug("Updating existing teacher");
    			teacher = this.teacherRepository.findById(inp.getId()).get();
    			if(inp.getTeacherMiddleName() != null) {
    				teacher.setTeacherMiddleName(inp.getTeacherMiddleName());
    			}
    			if(inp.getFatherMiddleName() != null) {
    				teacher.setFatherMiddleName(inp.getFatherMiddleName());
    			}
    			if(inp.getSpouseName() != null) {
    				teacher.setSpouseName(inp.getSpouseName());
    			}
    			if(inp.getSpouseMiddleName() != null) {
    				teacher.setSpouseMiddleName(inp.getSpouseMiddleName());
    			}
    			if(inp.getSpouseLastName() != null) {
    				teacher.setSpouseLastName(inp.getSpouseLastName());
    			}
    			if(inp.getMotherName() != null) {
    				teacher.setMotherName(inp.getMotherName());
    			}
    			if(inp.getMotherMiddleName() != null) {
    				teacher.setMotherMiddleName(inp.getMotherMiddleName());
    			}
    			if(inp.getMotherLastName() != null) {
    				teacher.setMotherLastName(inp.getMotherLastName());
    			}
    			if(inp.getPlaceOfBirth() != null) {
    				teacher.setPlaceOfBirth(inp.getPlaceOfBirth());
    			}
    			if(inp.getReligion() != null) {
    				teacher.setReligion(inp.getReligion());
    			}
    			if(inp.getCaste() != null) {
    				teacher.setCaste(inp.getCaste());
    			}
    			if(inp.getSubCaste() != null) {
    				teacher.setSubCaste(inp.getSubCaste());
    			}
    			if(inp.getAadharNo() != null) {
    				teacher.setAadharNo(inp.getAadharNo());
    			}
    			if(inp.getTown() != null) {
    				teacher.setTown(inp.getTown());
    			}
    			if(inp.getState() != null) {
    				teacher.setState(inp.getState());
    			}
    			if(inp.getCountry() != null) {
    				teacher.setCountry(inp.getCountry());
    			}
    			if(inp.getPinCode() != null) {
    				teacher.setPinCode(inp.getPinCode());
    			}
    			if(inp.getAlternateContactNumber() != null) {
    				teacher.setAlternateContactNumber(inp.getAlternateContactNumber());
    			}
    			if(inp.getAlternateEmailAddress() != null) {
    				teacher.setAlternateEmailAddress(inp.getAlternateEmailAddress());
    			}
    			if(inp.getEmergencyContactMiddleName() != null) {
    				teacher.setEmergencyContactMiddleName(inp.getEmergencyContactMiddleName());
    			}
    			if(inp.getEmergencyContactEmailAddress() != null) {
    				teacher.setEmergencyContactEmailAddress(inp.getEmergencyContactEmailAddress());
    			}
    		}
    		
    		
			teacher.setDesignation(inp.getDesignation());
			teacher.setStaffType(inp.getStaffType());
			teacher.setStatus(inp.getStatus());
			
			teacher.setTeacherName(inp.getTeacherName());
			teacher.setTeacherLastName(inp.getTeacherLastName());
			teacher.setFatherName(inp.getFatherName());
			teacher.setFatherLastName(inp.getFatherLastName());
			teacher.setSex(inp.getSex());
			teacher.setAddress(inp.getAddress());
			teacher.setTeacherContactNumber(inp.getTeacherContactNumber());
			teacher.setTeacherEmailAddress(inp.getTeacherEmailAddress());
			teacher.setRelationWithStaff(inp.getRelationWithStaff());
			teacher.setEmergencyContactName(inp.getEmergencyContactName());
			teacher.setEmergencyContactLastName(inp.getEmergencyContactLastName());
			teacher.setEmergencyContactNo(inp.getEmergencyContactNo());
			
        	Branch branch = cmsBranchService.getBranch(inp.getBranchId());
        	Department department = cmsDepartmentService.getDepartment(inp.getDepartmentId());
        	
        	teacher.setBranch(branch);
        	teacher.setDepartment(department);
        	
        	teacher = teacherRepository.save(teacher);
        	vo = CommonUtil.createCopyProperties(teacher, CmsTeacherVo.class);
        	vo.setStrDateOfBirth(teacher.getDateOfBirth() != null ? DateFormatUtil.changeLocalDateFormat(teacher.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
//        	vo.setCreatedOn(null);
//        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	if(inp.getId() == null) {
        		vo.setExitDescription("Teacher is added successfully");
        		logger.debug("Teacher is added successfully");
        	}else {
        		vo.setExitDescription("Teacher is updated successfully");
        		logger.debug("Teacher is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsTeacherVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, teacher data not be saved");
    		logger.error("Teacher save failed. Exception : ",e);
    	}
    	logger.info("Teacher saved successfully");
    	List ls =  getCmsTeacherList();
    	List<CmsEmployeeVo> evoList = cmsEmployeeService.getCmsEmployeeList();
    	for(CmsEmployeeVo tempEvo: evoList) {
    		if(CmsConstants.STAFF_TYPE_NONTEACHING.equalsIgnoreCase(tempEvo.getStaffType())) {
    			CmsTeacherVo tempTvo = convertCmsEmployeeToCmsTeacher(tempEvo);
        		ls.add(tempTvo);
    		}
    	}
        vo.setDataList(ls);
    	return vo;
    }
    
    public CmsTeacherVo convertCmsEmployeeToCmsTeacher(CmsEmployeeVo inp) {
    	CmsTeacherVo vo = new CmsTeacherVo();
    	
    	if(!CommonUtil.isNullOrEmpty(inp.getEmployeeName())) {
    		StringTokenizer token = new StringTokenizer(inp.getEmployeeName(), " ");
    		int cnt = 0;
    		while(token.hasMoreTokens()) {
    			if(cnt == 0) {
    				vo.setTeacherName(token.nextToken());
    			}else if(cnt == 1) {
    				vo.setTeacherMiddleName(token.nextToken());
    			}else if(cnt == 2) {
    				vo.setTeacherLastName(token.nextToken());
    			}
    			cnt++;
    		}
    	}
    	vo.setDesignation(inp.getDesignation());
//        vo.setJoiningDate(LocalDate.now());
        vo.setDateOfBirth(inp.getStrDateOfBirth() != null ? DateFormatUtil.convertStringToLocalDate(inp.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
        vo.setAadharNo(inp.getAadharNo());
        vo.setPanNo(inp.getPanNo());
//        vo.setPassportNo(null);
        vo.setTeacherContactNumber(inp.getPrimaryContactNo());
        vo.setAlternateContactNumber(inp.getSecondaryContactNo());
        if(!CommonUtil.isNullOrEmpty(inp.getEmployeeFatherName())) {
    		StringTokenizer token = new StringTokenizer(inp.getEmployeeFatherName(), " ");
    		int cnt = 0;
    		while(token.hasMoreTokens()) {
    			if(cnt == 0) {
    				vo.setFatherName(token.nextToken());
    			}else if(cnt == 1) {
    				vo.setFatherMiddleName(token.nextToken());
    			}else if(cnt == 2) {
    				vo.setFatherLastName(token.nextToken());
    			}
    			cnt++;
    		}
    	}
        if(!CommonUtil.isNullOrEmpty(inp.getEmployeeMotherName())) {
    		StringTokenizer token = new StringTokenizer(inp.getEmployeeMotherName(), " ");
    		int cnt = 0;
    		while(token.hasMoreTokens()) {
    			if(cnt == 0) {
    				vo.setMotherName(token.nextToken());
    			}else if(cnt == 1) {
    				vo.setMotherMiddleName(token.nextToken());
    			}else if(cnt == 2) {
    				vo.setMotherLastName(token.nextToken());
    			}
    			cnt++;
    		}
    	}
        vo.setAddress(inp.getPrimaryAddress());
//        vo.setSecondaryAddress(null);
//        private String employeeAddress;
        vo.setTeacherEmailAddress(inp.getPersonalMailId());
//        private String officialMailId;
//        private String disability;
//        private String drivingLicenceNo;
//        private LocalDate drivingLicenceValidity;
        
        vo.setSex(inp.getGender());
//        private String typeOfEmployment;
//        private Long managerId;
        vo.setStatus(inp.getStatus());
//        private String maritalStatus;
        
        vo.setStrDateOfBirth(inp.getStrDateOfBirth());
//    	
//        private String strJobEndDate;
//        private String strResignationDate;
//        private String strResignationAcceptanceDate;
//        private String strDrivingLicenceValidity;
        vo.setStaffType(inp.getStaffType());
        if(inp.getBranchId() != null) {
			vo.setBranchId(inp.getBranchId());
			Branch branch = cmsBranchService.getBranch(inp.getBranchId());
			CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(branch, CmsBranchVo.class); 
			vo.setCmsBranchVo(cmsBvo);
		}
        if(inp.getDepartmentId() != null) {
			vo.setDepartmentId(inp.getDepartmentId());
			Department department = cmsDepartmentService.getDepartment(inp.getDepartmentId());
			CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(department, CmsDepartmentVo.class); 
			vo.setCmsDepartmentVo(cmsDvo);
		}
		
    	return vo;
    }
    
    public void pushToKafka() {
    	List<Teacher> list = getTeacherListOnFilterCriteria(new HashMap<String, String>());
    	for(Teacher teacher: list) {
    		fireEvent(EventType.CREATE, teacher);
    	}
    }
    
    private void fireEvent(EventType type, Teacher entity) {
    	Environment env = PreferencesApp.getBean(Environment.class);
    	RestTemplate rest = PreferencesApp.getBean(RestTemplate.class);
    	
        logger.info("Event type - "+type + ": " + IUtils.getStringFromValue(entity));
		if (!IUtils.isNull(entity) && entity instanceof IESEntity) {
			ESEvent event = ESEvent.builder(type, entity).build();
			logger.info("Event string :: " + IUtils.getStringFromValue(event));
			String res = null;
			try {
				res = IUtils.sendGetRestRequest(rest, IUtils.getValueByKey(
						env, IUtils.KEY_KAFKA_CONFIG, IUtils.URL_KAFKA_URL),
						IUtils.getRestParamMap(IUtils.PRM_TOPIC,
								IUtils.getValueByKey(env, IUtils.KEY_KAFKA_TOPIC, "cms"),
								IUtils.PRM_MSG,
								IUtils.getStringFromValue(event)), String.class);
			} catch(Exception ex) {
				logger.error(ex.getMessage(), ex);
				res = null;
			}
			
		} else {
			logger.error("Teacher entity should not be null");
		}
	}
}
