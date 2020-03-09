package com.synectiks.pref.ems.rest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.synectiks.pref.business.service.CmsEmployeeService;
import com.synectiks.pref.domain.Employee;
import com.synectiks.pref.domain.vo.CmsEmployeeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CmsEmployeeService cmsEmployeeService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsemployee-by-filters")
    public List<CmsEmployeeVo> getCmsEmployeeListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Employees based on filter criteria");
        List<CmsEmployeeVo> list = this.cmsEmployeeService.getCmsEmployeeListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee-by-filters")
    public List<Employee> getEmployeeListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Employees based on filter criteria");
        List<Employee> list = this.cmsEmployeeService.getEmployeeListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsemployee")
    public List<CmsEmployeeVo> getAllCmsEmployee() throws Exception {
        logger.debug("REST request to get all Cms Employees");
        return this.cmsEmployeeService.getCmsEmployeeList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-employee")
    public List<Employee> getAllEmployee() throws Exception {
        logger.debug("REST request to get all the Employees");
        return this.cmsEmployeeService.getEmployeesList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee-by-id/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Employee : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsEmployeeService.getEmployee(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsemployee/{id}")
    public ResponseEntity<CmsEmployeeVo> getCmsEmployee(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Employee : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsEmployeeService.getCmsEmployee(id)));
    }
}
//
//    @RequestMapping(method = RequestMethod.GET, value = "/cmsemployees-qryprms")
//    public List<CmsEmployeeVo> getAllTeachersByDeptBranchId(@RequestParam Map<String, String> dataMap) {
//        logger.debug("REST request to get all the Teachers");
//
//        Map<String, String> criteriaMap = new HashMap<String, String>();
//        if (dataMap.containsKey("deptId")) {
//            criteriaMap.put("departmentId", dataMap.get("deptId"));
//        }
//        if (dataMap.containsKey("branchId")) {
//            criteriaMap.put("branchId", dataMap.get("branchId"));
//        }
//        List<CmsTeacherVo> list = this.cmsTeacherService.getCmsTeacherListOnFilterCriteria(criteriaMap);
//        return list;
//    }
//
//}

