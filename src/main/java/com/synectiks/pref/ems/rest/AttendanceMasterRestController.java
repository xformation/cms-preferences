package com.synectiks.pref.ems.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.business.service.CmsAttendanceMasterService;
import com.synectiks.pref.domain.AttendanceMaster;
import com.synectiks.pref.domain.vo.CmsAttendanceMasterVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing AttendanceMaster.
 */
@RestController
@RequestMapping("/api")
public class AttendanceMasterRestController {

    private final Logger logger = LoggerFactory.getLogger(AttendanceMasterRestController.class);

    @Autowired
    private CmsAttendanceMasterService cmsAttendanceMasterService; 
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsattendancemaster-by-filters")
    public List<CmsAttendanceMasterVo> getCmsAttendanceMasterListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms AttendanceMaster based on filter criteria");
        List<CmsAttendanceMasterVo> list = this.cmsAttendanceMasterService.getCmsAttendanceMasterListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/attendancemaster-by-filters")
    public List<AttendanceMaster> getAttendanceMasterListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of AttendanceMaster based on filter criteria");
        List<AttendanceMaster> list = this.cmsAttendanceMasterService.getAttendanceMasterListOnFilterCriteria(dataMap);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsattendancemaster")
    public List<CmsAttendanceMasterVo> getAllCmsAttendanceMaster() throws Exception {
        logger.debug("REST request to get all Cms AttendanceMaster");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsAttendanceMasterService.getCmsAttendanceMasterListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-attendancemaster")
    public List<AttendanceMaster> getAllAttendanceMaster() throws Exception {
        logger.debug("REST request to get all the AttendanceMaster");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsAttendanceMasterService.getAttendanceMasterListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/attendancemaster-by-id/{id}")
    public ResponseEntity<AttendanceMaster> getAttendanceMaster(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a AttendanceMaster : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsAttendanceMasterService.getAttendanceMaster(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsattendancemaster/{id}")
    public ResponseEntity<CmsAttendanceMasterVo> getCmsAttendanceMaster(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a AttendanceMaster : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsAttendanceMasterService.getCmsAttendanceMaster(id)));
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsattendance-masters-bydepartmentid")
    public List<AttendanceMaster> getAttendanceMasterByDepartment(@RequestParam Map<String, String> dataMap) {
    	logger.debug("Getting attendance master");
    	
    	Long departmentId = Long.parseLong(dataMap.get("departmentId"));
    	List<AttendanceMaster> list = new ArrayList<>();
    	Map<String, String> criteriaMap = new HashMap<String, String>();
    	List<AttendanceMaster> amList = cmsAttendanceMasterService.getAttendanceMasterListOnFilterCriteria(criteriaMap);
    	
    	for(AttendanceMaster am : amList) {
    		if(departmentId == am.getTeach().getTeacher().getDepartment().getId()) {
    			list.add(am);
    		}
    	}
    	return list;
    }
    
}
