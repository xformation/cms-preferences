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

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Course;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsCourseVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.graphql.types.course.CourseInput;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.repository.CourseRepository;
import com.synectiks.pref.repository.DepartmentRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsCourseService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    BranchRepository branchRepository;

    public List<CmsCourseVo> getCourseList(String status) {
    	Course course = new Course();
        course.setStatus(status);
        List<Course> list = this.courseRepository.findAll(Example.of(course));
        List<CmsCourseVo> ls = changeCourseToCmsCourseList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    
    public List<CmsCourseVo> getCourseList(){
    	List<Course> list = this.courseRepository.findAll();
    	List<CmsCourseVo> ls = changeCourseToCmsCourseList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public CmsCourseVo getCourse(Long id){
    	Optional<Course> oCrs = this.courseRepository.findById(id);
    	CmsCourseVo vo = null;
    	if(oCrs.isPresent()) {
    		vo = CommonUtil.createCopyProperties(oCrs.get(), CmsCourseVo.class);
    		if(oCrs.get().getCreatedOn() != null) {
            	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(oCrs.get().getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setCreatedOn(null);
            }
            if(oCrs.get().getUpdatedOn() != null) {
            	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(oCrs.get().getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setUpdatedOn(null);
            }
            if(oCrs.get().getDepartment() != null) {
            	vo.setDepartmentId(oCrs.get().getDepartment().getId());
            	CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(oCrs.get().getDepartment(), CmsDepartmentVo.class); 
        		vo.setCmsDepartmentVo(cmsDvo);
            }
            if(oCrs.get().getBranch() != null) {
            	vo.setBranchId(oCrs.get().getBranch().getId());
            	CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(oCrs.get().getBranch(), CmsBranchVo.class); 
        		vo.setCmsBranchVo(cmsBvo);
            }
    	}
    	return vo;
    }
    
    private List<CmsCourseVo> changeCourseToCmsCourseList(List<Course> list){
    	List<CmsCourseVo> ls = new ArrayList<>();
    	for(Course o: list) {
    		CmsCourseVo vo = CommonUtil.createCopyProperties(o, CmsCourseVo.class);
    		if(o.getCreatedOn() != null) {
            	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(o.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(o.getUpdatedOn() != null) {
            	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(o.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
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
    		ls.add(vo);
    	}
    	return ls;
    }
    
    public CmsCourseVo saveCourse(CourseInput input) {
    	logger.info("Saving course");
    	CmsCourseVo vo = null;
    	try {
    		Course course = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new course");
    			course = CommonUtil.createCopyProperties(input, Course.class);
    			course.setCreatedOn(LocalDate.now());
    			course.setTotalFee(input.getTotalFee());
    			course.setYearlyFee(input.getYearlyFee());
    			course.setPerSemesterFee(input.getPerSemesterFee());
    			course.setComments(input.getComments());
    		}else {
    			logger.debug("Updating existing course");
    			course = this.courseRepository.findById(input.getId()).get();
    			course.setUpdatedOn(LocalDate.now());
    			if(input.getTotalFee() != null) {
    				course.setTotalFee(input.getTotalFee());
    			}
    			if(input.getYearlyFee() != null) {
    				course.setYearlyFee(input.getYearlyFee());
    			}
    			if(input.getPerSemesterFee() != null) {
    				course.setPerSemesterFee(input.getPerSemesterFee());
    			}
    			if(input.getComments() != null) {
    				course.setComments(input.getComments());
    			}
    		}
    		Department dp = this.departmentRepository.findById(input.getDepartmentId()).get();
			course.setDepartment(dp);
    		
			Branch b = this.branchRepository.findById(input.getBranchId()).get();
			course.setBranch(b);
    		
			course.setName(input.getName());
			course.setDescription(input.getDescription());
			course.setCourseDuration(input.getCourseDuration());
			course.setCourseType(input.getCourseType());
			course.setYearOrSemesterType(input.getYearOrSemesterType());
			course.setStatus(input.getStatus());
    		
    		course = this.courseRepository.save(course);
        	
        	vo = CommonUtil.createCopyProperties(course, CmsCourseVo.class);
        	vo.setStrCreatedOn(course.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(course.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(course.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(course.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("Course is added successfully");
        		logger.debug("Course is added successfully");
        	}else {
        		vo.setExitDescription("Course is updated successfully");
        		logger.debug("Course is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsCourseVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, course data not be saved");
    		logger.error("Course save failed. Exception : ",e);
    	}
    	logger.info("Course saved successfully");
    	List ls =  getCourseList();
        vo.setDataList(ls);
    	return vo;
        
    }
}
