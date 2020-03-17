package com.synectiks.pref.ems.rest;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.synectiks.pref.business.service.CmsEmployeeService;
import com.synectiks.pref.business.service.CmsTeacherService;
import com.synectiks.pref.business.service.CommonService;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.domain.Employee;
import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.domain.vo.CmsStudentVo;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.web.rest.util.HeaderUtil;

/**
 * REST controller for managing user export.
 */
@RestController
@RequestMapping("/api")
public class UserExportRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CmsTeacherService cmsTeacherService;
	
	@Autowired
	private CmsEmployeeService cmsEmployeeService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsuserexport")
	public ResponseEntity<Integer> exportUser(@RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
		boolean isTecher = dataMap.get("chkTeacher") != null ? Boolean.parseBoolean(dataMap.get("chkTeacher")) : false;
		boolean isStudent = dataMap.get("chkStudent") != null ? Boolean.parseBoolean(dataMap.get("chkStudent")) : false;
		boolean isEmployee = dataMap.get("chkEmployee") != null ? Boolean.parseBoolean(dataMap.get("chkEmployee")) : false;
//		Long branchId = dataMap.get("branchId") != null ? Long.parseLong(dataMap.get("branchId")) : 0;
		
		logger.debug("Branch id :"+dataMap.get("branchId")+", Teacher selected : "+isTecher+", Student selected : "+isStudent+", Employee selected : "+isEmployee);
		
		Set<String> email = new HashSet<>();
		if(isTecher && !CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
			List<Teacher> teacherList = this.cmsTeacherService.getTeacherListOnFilterCriteria(dataMap);
			for(Teacher t: teacherList) {
				email.add(t.getTeacherEmailAddress());
			}
		}
		if(isStudent && !CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
//			Student student = new Student();
//			student.setBranchId(branchId);
//			List<Student> studentList = this.studentRepository.findAll(Example.of(student));
//			for(Student s: studentList) {
//				email.add(s.getStudentPrimaryEmailId());
//			}
			Long branchId = Long.parseLong(dataMap.get("branchId"));
			List<CmsStudentVo> studentList = this.commonService.getAllCmsStudentsByBranch(branchId);
			for(CmsStudentVo s: studentList) {
				email.add(s.getStudentPrimaryEmailId());
			}
		}
		
		if(isEmployee && !CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
			List<Employee> employeeList = this.cmsEmployeeService.getEmployeeListOnFilterCriteria(dataMap);
			for(Employee e: employeeList) {
				email.add(e.getOfficialMailId());
			}
		}
		List<String> finalList = email.stream().collect(Collectors.toList());
		int statusCode = 200;
		if(finalList != null && finalList.size() > 0) {
			statusCode = exportUserToSecuryService(finalList);
//			return ResponseEntity.status(statusCode)
//		            .headers(HeaderUtil.createEntityUpdateAlert("", ""))
//		            .body(new Integer(statusCode));
		}
		
		return ResponseEntity.status(statusCode)
	            .headers(HeaderUtil.createEntityUpdateAlert("", ""))
	            .body(new Integer(statusCode));
		
	}
	
	private int exportUserToSecuryService(List<String> list) throws JsonProcessingException {
		
		String status = restTemplate.postForObject(applicationProperties.getSecSrvUrl()+"/security/public/importUser", 
				list, String.class, list);
		
		logger.info("Export users response status : "+status);
		return status.equalsIgnoreCase("SUCCESS") ? 200 : 500;
	}
	
	

}
