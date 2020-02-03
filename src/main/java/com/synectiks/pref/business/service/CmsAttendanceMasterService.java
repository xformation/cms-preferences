package com.synectiks.pref.business.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import com.synectiks.pref.domain.AttendanceMaster;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Section;
import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.domain.vo.CmsAttendanceMasterVo;
import com.synectiks.pref.domain.vo.CmsBatchVo;
import com.synectiks.pref.domain.vo.CmsSectionVo;
import com.synectiks.pref.domain.vo.CmsTeachVo;
import com.synectiks.pref.graphql.types.subject.SubjectInput;
import com.synectiks.pref.repository.AttendanceMasterRepository;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class CmsAttendanceMasterService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    AttendanceMasterRepository attendanceMasterRepository;

    @Autowired
    CmsBatchService cmsBatchService;

    @Autowired
    CmsSectionService cmsSectionService;
    
    @Autowired
    CmsTeachService cmsTeachService;
    
    public List<CmsAttendanceMasterVo> getCmsAttendanceMasterListOnFilterCriteria(Map<String, String> criteriaMap){
    	AttendanceMaster obj = new AttendanceMaster();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		obj.setDesc(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("batchId") != null) {
    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
    		obj.setBatch(batch);
    		isFilter = true;
    	}
    	if(criteriaMap.get("sectionId") != null) {
    		Section section = cmsSectionService.getSection(Long.parseLong(criteriaMap.get("sectionId")));
    		obj.setSection(section);
    		isFilter = true;
    	}
    	if(criteriaMap.get("teachId") != null) {
    		Teach teach = cmsTeachService.getTeach(Long.parseLong(criteriaMap.get("teachId")));
    		obj.setTeach(teach);
    		isFilter = true;
    	}
    	List<AttendanceMaster> list = null;
    	if(isFilter) {
    		list = this.attendanceMasterRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.attendanceMasterRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsAttendanceMasterVo> ls = changeAttendanceMasterToCmsAttendanceMasterList(list);
    	Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return ls;
    }
    
    public List<AttendanceMaster> getAttendanceMasterListOnFilterCriteria(Map<String, String> criteriaMap){
    	AttendanceMaster obj = new AttendanceMaster();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("description") != null) {
    		obj.setDesc(criteriaMap.get("description"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("batchId") != null) {
    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
    		obj.setBatch(batch);
    		isFilter = true;
    	}
    	if(criteriaMap.get("sectionId") != null) {
    		Section section = cmsSectionService.getSection(Long.parseLong(criteriaMap.get("sectionId")));
    		obj.setSection(section);
    		isFilter = true;
    	}
    	if(criteriaMap.get("teachId") != null) {
    		Teach teach = cmsTeachService.getTeach(Long.parseLong(criteriaMap.get("teachId")));
    		obj.setTeach(teach);
    		isFilter = true;
    	}
    	List<AttendanceMaster> list = null;
    	if(isFilter) {
    		list = this.attendanceMasterRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.attendanceMasterRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return list;
    }
    
//    public List<AttendanceMaster> getTeachListOnFilterCriteria(Map<String, String> criteriaMap){
//    	AttendanceMaster obj = new AttendanceMaster();
//    	boolean isFilter = false;
//    	if(criteriaMap.get("id") != null) {
//    		obj.setId(Long.parseLong(criteriaMap.get("id")));
//    		isFilter = true;
//    	}
//    	if(criteriaMap.get("description") != null) {
//    		obj.setDesc(criteriaMap.get("description"));
//    		isFilter = true;
//    	}
//    	if(criteriaMap.get("batchId") != null) {
//    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
//    		obj.setBatch(batch);
//    		isFilter = true;
//    	}
//    	if(criteriaMap.get("sectionId") != null) {
//    		Section section = cmsSectionService.getSection(Long.parseLong(criteriaMap.get("sectionId")));
//    		obj.setSection(section);
//    		isFilter = true;
//    	}
//    	if(criteriaMap.get("teachId") != null) {
//    		Teach teach = cmsTeachService.getTeach(Long.parseLong(criteriaMap.get("teachId")));
//    		obj.setTeach(teach);
//    		isFilter = true;
//    	}
//    	List<AttendanceMaster> list = null;
//    	if(isFilter) {
//    		list = this.attendanceMasterRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
//    	}else {
//    		list = this.attendanceMasterRepository.findAll(Sort.by(Direction.DESC, "id"));
//    	}
//        
//    	Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
//    	return list;
//    }
    
    public CmsAttendanceMasterVo getCmsAttendanceMaster(Long id){
    	Optional<AttendanceMaster> th = this.attendanceMasterRepository.findById(id);
    	if(th.isPresent()) {
    		CmsAttendanceMasterVo vo = CommonUtil.createCopyProperties(th.get(), CmsAttendanceMasterVo.class);
    		convertDatesAndProvideDependencies(th.get(), vo);
    		logger.debug("CmsAttendanceMaster for given id : "+id+". CmsAttendanceMaster object : "+vo);
        	return vo;
    	}
    	logger.debug("AttendanceMaster object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public AttendanceMaster getAttendanceMaster(Long id){
    	Optional<AttendanceMaster> th = this.attendanceMasterRepository.findById(id);
    	if(th.isPresent()) {
    		return th.get();
    	}
    	logger.debug("AttendanceMaster object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    private List<CmsAttendanceMasterVo> changeAttendanceMasterToCmsAttendanceMasterList(List<AttendanceMaster> list){
    	List<CmsAttendanceMasterVo> ls = new ArrayList<>();
    	for(AttendanceMaster tr: list) {
    		CmsAttendanceMasterVo vo = CommonUtil.createCopyProperties(tr, CmsAttendanceMasterVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(AttendanceMaster tr, CmsAttendanceMasterVo vo) {
		if(tr.getBatch() != null) {
			vo.setBatchId(tr.getBatch().getId());
			CmsBatchVo cmsBvo =CommonUtil.createCopyProperties(tr.getBatch(), CmsBatchVo.class); 
			vo.setCmsBatchVo(cmsBvo);
		}
		if(tr.getSection() != null) {
			vo.setSectionId(tr.getSection().getId());
			CmsSectionVo cmsSvo =CommonUtil.createCopyProperties(tr.getSection(), CmsSectionVo.class); 
			vo.setCmsSectionVo(cmsSvo);
		}
		if(tr.getTeach() != null) {
			vo.setTeachId(tr.getTeach().getId());
			CmsTeachVo cmsTvo =CommonUtil.createCopyProperties(tr.getTeach(), CmsTeachVo.class); 
			vo.setCmsTeachVo(cmsTvo);
		}
	}
    
    public AttendanceMaster saveAttendanceMaster(SubjectInput input) {
    	Batch batch = this.cmsBatchService.getBatch(input.getBatchId());
    	Section section = this.cmsSectionService.getSection(input.getSectionId());
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("subjectId", String.valueOf(input.getSubjectId()));
    	map.put("teacherId", String.valueOf(input.getTeacherId()));
    	List<Teach> teachList = this.cmsTeachService.getTeachListOnFilterCriteria(map);
    	Teach teach = teachList.get(0);
    	
    	logger.debug("Making entries in attendance master for the given subject id : "+input.getSubjectId()+"and teacher id : "+input.getTeacherId()+" and teach id : "+teach.getId());
    	AttendanceMaster am = new AttendanceMaster();
    	am.setBatch(batch);
    	am.setSection(section);
    	am.setTeach(teach);
    	am = this.attendanceMasterRepository.save(am);
    	return am;
    }
    
    public AttendanceMaster saveAttendanceMaster(Batch batch, Section section, Teach teach) {
    	logger.debug("Saving attendance master for the given subject id : "+teach.getSubject().getId()+"and teacher id : "+teach.getTeacher().getId()+" and teach id : "+teach.getId());
    	AttendanceMaster am = new AttendanceMaster();
    	am.setDesc("Teacher - "+teach.getTeacher().getTeacherName()+". Subject - "+ teach.getSubject().getSubjectDesc()+". Branch - "+teach.getTeacher().getBranch().getBranchName()+". Department - "+teach.getTeacher().getDepartment().getName()+". Batch/Year - "+ batch.getBatch()+". Section - "+ section.getSection()  );
    	am.setBatch(batch);
    	am.setSection(section);
    	am.setTeach(teach);
    	am = this.attendanceMasterRepository.save(am);
    	return am;
    }
    
    
}
