package com.synectiks.pref.business.service;

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

import com.synectiks.pref.domain.Subject;
import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.domain.vo.CmsSubjectVo;
import com.synectiks.pref.domain.vo.CmsTeachVo;
import com.synectiks.pref.domain.vo.CmsTeacherVo;
import com.synectiks.pref.graphql.types.subject.SubjectInput;
import com.synectiks.pref.repository.TeachRepository;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class CmsTeachService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    TeachRepository teachRepository;

    @Autowired
    CmsSubjectService cmsSubjectService;

    @Autowired
    CmsTeacherService cmsTeacherService;
    
    public List<CmsTeachVo> getCmsTeachListOnFilterCriteria(Map<String, String> criteriaMap){
    	Teach obj = new Teach();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		obj.setDesc(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectId") != null) {
    		Subject subject = cmsSubjectService.getSubject(Long.parseLong(criteriaMap.get("subjectId")));
    		obj.setSubject(subject);
    		isFilter = true;
    	}
    	if(criteriaMap.get("teacherId") != null) {
    		Teacher teacher = cmsTeacherService.getTeacher(Long.parseLong(criteriaMap.get("teacherId")));
    		obj.setTeacher(teacher);
    		isFilter = true;
    	}
    	List<Teach> list = null;
    	if(isFilter) {
    		list = this.teachRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.teachRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsTeachVo> ls = changeTeachToCmsTeachList(list);
    	Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return ls;
    }
    
    public List<Teach> getTeachListOnFilterCriteria(Map<String, String> criteriaMap){
    	Teach obj = new Teach();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	
    	if(criteriaMap.get("description") != null) {
    		obj.setDesc(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectId") != null) {
    		Subject subject = cmsSubjectService.getSubject(Long.parseLong(criteriaMap.get("subjectId")));
    		obj.setSubject(subject);
    		isFilter = true;
    	}
    	if(criteriaMap.get("teacherId") != null) {
    		Teacher teacher = cmsTeacherService.getTeacher(Long.parseLong(criteriaMap.get("teacherId")));
    		obj.setTeacher(teacher);
    		isFilter = true;
    	}
    	List<Teach> list = null;
    	if(isFilter) {
    		list = this.teachRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.teachRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return list;
    }
    
    public CmsTeachVo getCmsTeach(Long id){
    	Optional<Teach> th = this.teachRepository.findById(id);
    	if(th.isPresent()) {
    		CmsTeachVo vo = CommonUtil.createCopyProperties(th.get(), CmsTeachVo.class);
    		convertDatesAndProvideDependencies(th.get(), vo);
    		logger.debug("CmsTeach for given id : "+id+". CmsTeach object : "+vo);
        	return vo;
    	}
    	logger.debug("Teach object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Teach getTeach(Long id){
    	Optional<Teach> th = this.teachRepository.findById(id);
    	if(th.isPresent()) {
    		return th.get();
    	}
    	logger.debug("Teach object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    
    public CmsTeachVo getCmsTeach(Long teacherId, Long subjectId){
    	Teach teach = new Teach();
    	Subject subject = this.cmsSubjectService.getSubject(subjectId);
    	Teacher teacher = this.cmsTeacherService.getTeacher(teacherId);
    	teach.setSubject(subject);
    	teach.setTeacher(teacher);
    	
    	Optional<Teach> th = this.teachRepository.findOne(Example.of(teach));
    	
    	if(th.isPresent()) {
    		CmsTeachVo vo = CommonUtil.createCopyProperties(th.get(), CmsTeachVo.class);
    		convertDatesAndProvideDependencies(th.get(), vo);
    		logger.debug("CmsTeach for given teacher and subject id. CmsTeach object : "+vo);
        	return vo;
    	}
    	logger.debug("Teach object not found for the given teacher and subject ids. Returning null object");
        return null;
    }
    
    public Teach getTeach(Long teacherId, Long subjectId){
    	Teach teach = new Teach();
    	Subject subject = this.cmsSubjectService.getSubject(subjectId);
    	Teacher teacher = this.cmsTeacherService.getTeacher(teacherId);
    	teach.setSubject(subject);
    	teach.setTeacher(teacher);
    	
    	Optional<Teach> th = this.teachRepository.findOne(Example.of(teach));
    	if(th.isPresent()) {
    		return th.get();
    	}
    	logger.debug("Teach object not found for the teacher and subject id. Returning null");
        return null;
    }
    
    private List<CmsTeachVo> changeTeachToCmsTeachList(List<Teach> list){
    	List<CmsTeachVo> ls = new ArrayList<>();
    	for(Teach tr: list) {
    		CmsTeachVo vo = CommonUtil.createCopyProperties(tr, CmsTeachVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Teach tr, CmsTeachVo vo) {
		if(tr.getSubject() != null) {
			vo.setSubjectId(tr.getSubject().getId());
			CmsSubjectVo cmsSvo =CommonUtil.createCopyProperties(tr.getSubject(), CmsSubjectVo.class); 
			vo.setCmsSubjectVo(cmsSvo);
		}
		if(tr.getTeacher() != null) {
			vo.setTeacherId(tr.getTeacher().getId());
			CmsTeacherVo cmsTvo =CommonUtil.createCopyProperties(tr.getTeacher(), CmsTeacherVo.class); 
			vo.setCmsTeacherVo(cmsTvo);
		}
	}
    
    public Teach saveTeach(SubjectInput input) {
    	Subject subject = this.cmsSubjectService.getSubject(input.getId());
    	Teacher teacher = this.cmsTeacherService.getTeacher(input.getTeacherId());
    	logger.debug("Making entries in teach for the given subject id : "+input.getSubjectId()+"and teacher id : "+input.getSubjectId());
    	Teach teach = new Teach();
    	teach.setSubject(subject);
    	teach.setTeacher(teacher);
    	Optional<Teach> oth = this.teachRepository.findOne(Example.of(teach));
    	if(!oth.isPresent()) {
    		teach.setDesc("Teacher - "+teacher.getTeacherName()+". Subject - "+ subject.getSubjectDesc()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
    		Teach th = this.teachRepository.save(teach);
    		logger.debug("Teach data saved : "+teach);
    		return th;
    	}else {
    		logger.debug("Teach mapping already exists. "+oth.get());
    	}
    	return oth.get();
    }
    
    
}
