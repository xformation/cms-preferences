package com.synectiks.pref.business.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.synectiks.pref.domain.AttendanceMaster;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.Section;
import com.synectiks.pref.domain.Subject;
import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.domain.vo.CmsAttendanceMasterVo;
import com.synectiks.pref.domain.vo.CmsBatchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.domain.vo.CmsSectionVo;
import com.synectiks.pref.domain.vo.CmsSubjectVo;
import com.synectiks.pref.domain.vo.CmsTeachVo;
import com.synectiks.pref.domain.vo.CmsTeacherVo;
import com.synectiks.pref.graphql.types.subject.SubjectInput;
import com.synectiks.pref.repository.SubjectRepository;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class CmsSubjectService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @PersistenceContext
    private EntityManager entityManager;
    
	@Autowired
    SubjectRepository subjectRepository;
    
	@Autowired
    CmsDepartmentService cmsDepartmentService;
    
    @Autowired
    CmsAttendanceMasterService cmsAttendanceMasterService;

    @Autowired
    CmsBatchService cmsBatchService;

    @Autowired
    CmsSectionService cmsSectionService;
    
    @Autowired
    CmsTeachService cmsTeachService;
    
    @Autowired
    CmsTeacherService cmsTeacherService;
    
	
    public List<CmsSubjectVo> getCmsSubjectListOnFilterCriteria(Map<String, String> criteriaMap){
    	Subject obj = new Subject();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectCode") != null) {
    		obj.setSubjectCode(criteriaMap.get("subjectCode"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectType") != null) {
    		obj.setSubjectCode(criteriaMap.get("subjectType"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectDesc") != null) {
    		obj.setSubjectCode(criteriaMap.get("subjectDesc"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		obj.setSubjectCode(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("departmentId") != null) {
    		Department department = cmsDepartmentService.getDepartment(Long.parseLong(criteriaMap.get("departmentId")));
    		obj.setDepartment(department);
    		isFilter = true;
    	}
    	if(criteriaMap.get("batchId") != null) {
    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
    		obj.setBatch(batch);
    		isFilter = true;
    	}
    	List<Subject> list = null;
    	if(isFilter) {
    		list = this.subjectRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.subjectRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsSubjectVo> ls = changeSubjectToCmsSubjectList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    public List<Subject> getSubjectListOnFilterCriteria(Map<String, String> criteriaMap){
    	Subject obj = new Subject();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectCode") != null) {
    		obj.setSubjectCode(criteriaMap.get("subjectCode"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectType") != null) {
    		obj.setSubjectCode(criteriaMap.get("subjectType"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("subjectDesc") != null) {
    		obj.setSubjectCode(criteriaMap.get("subjectDesc"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("status") != null) {
    		obj.setSubjectCode(criteriaMap.get("status"));
    		isFilter = true;
    	}
    	if(criteriaMap.get("departmentId") != null) {
    		Department department = cmsDepartmentService.getDepartment(Long.parseLong(criteriaMap.get("departmentId")));
    		obj.setDepartment(department);
    		isFilter = true;
    	}
    	if(criteriaMap.get("batchId") != null) {
    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
    		obj.setBatch(batch);
    		isFilter = true;
    	}
    	List<Subject> list = null;
    	if(isFilter) {
    		list = this.subjectRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.subjectRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }
    
    public CmsSubjectVo getCmsSubject(Long id){
    	Optional<Subject> br = this.subjectRepository.findById(id);
    	if(br.isPresent()) {
    		CmsSubjectVo vo = CommonUtil.createCopyProperties(br.get(), CmsSubjectVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsSubject for given id : "+id+". CmsSubject object : "+vo);
        	return vo;
    	}
    	logger.debug("Subject object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Subject getSubject(Long id){
    	Optional<Subject> br = this.subjectRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Subject object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    private List<CmsSubjectVo> changeSubjectToCmsSubjectList(List<Subject> list){
    	List<CmsSubjectVo> ls = new ArrayList<>();
    	for(Subject tr: list) {
    		CmsSubjectVo vo = CommonUtil.createCopyProperties(tr, CmsSubjectVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	public void convertDatesAndProvideDependencies(Subject sb, CmsSubjectVo vo) {
		if(sb.getDepartment() != null) {
			vo.setDepartmentId(sb.getDepartment().getId());
			CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(sb.getDepartment(), CmsDepartmentVo.class);
			vo.setCmsDepartmentVo(cmsDvo);
		}
		if(sb.getBatch() != null) {
			vo.setBatchId(sb.getBatch().getId());
			CmsBatchVo cmsBvo =CommonUtil.createCopyProperties(sb.getBatch(), CmsBatchVo.class);
			vo.setCmsBatchVo(cmsBvo);
		}
	}
	
	
	public List<Subject> getSubjectWithDepartmentAndBatchList(List<Department> departmentList, List<Batch> batchList) {
        @SuppressWarnings("unchecked")
        List<Subject> list = this.entityManager.createQuery("select l from Subject l where l.department in (:departmentList) and l.batch in (:batchList)")
            .setParameter("departmentList", departmentList)
            .setParameter("batchList", batchList)
            .getResultList();
        
        if(list.size() == 0) {
        	logger.warn("getSubjectWithDepartmentAndBatchList(): Subject not found. Returning empty list");
        	return Collections.emptyList();
        }
        	
        return list;
    }

    
	public CmsSubjectVo saveSubject(SubjectInput input) {
    	logger.info("Saving subject");
    	CmsSubjectVo vo = null;
    	try {
    		Subject subject = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new subject");
    			subject = CommonUtil.createCopyProperties(input, Subject.class);
//    			subject.setCreatedOn(LocalDate.now());
    		}else {
    			logger.debug("Updating existing subject");
    			subject = this.subjectRepository.findById(input.getId()).get();
//    			subject.setUpdatedOn(LocalDate.now());
    			
    		}
    		Department department = this.cmsDepartmentService.getDepartment(input.getDepartmentId());
			subject.setDepartment(department);
    		
			Batch b = this.cmsBatchService.getBatch(input.getBatchId());
			subject.setBatch(b);
    		
			subject.setSubjectCode(input.getSubjectCode());
			subject.setSubjectType(input.getSubjectType());
			subject.setSubjectDesc(input.getSubjectDesc());
			subject.setStatus(input.getStatus());
    		
    		subject = subjectRepository.save(subject);
        	
        	vo = CommonUtil.createCopyProperties(subject, CmsSubjectVo.class);
        	
        	if(input.getId() != null) {
        		saveTeacherSubjectMapping(input, vo);
        		logger.debug("Teacher and Subject mapping in Teach and AttendanceMaster is added successfully.");
        	}
//        	vo.setStrCreatedOn(subject.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(subject.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
//        	vo.setStrUpdatedOn(subject.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(subject.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
//        	
//        	vo.setCreatedOn(null);
//        	vo.setUpdatedOn(null);
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("Subject is added successfully");
        		logger.debug("Subject is added successfully.");
        	}else {
        		vo.setExitDescription("Subject is updated successfully");
        		logger.debug("Subject is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsSubjectVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, subject data not be saved");
    		logger.error("Subject save failed. Exception : ",e);
    	}
    	logger.info("Subject saved successfully");
    	List<CmsSubjectVo> ls =  getMergedSubjectAndAttendanceMasterList();
        vo.setDataList(ls);
    	return vo;
        
    }
    
	public void saveTeacherSubjectMapping(SubjectInput input, CmsSubjectVo vo) {
//		CmsSubjectVo vo = null;
//    	try {
//    		Teacher teacher = this.cmsTeacherService.getTeacher(input.getTeacherId());
//        	Subject subject = getSubject(input.getSubjectId());
        	
        	Teach teach = this.cmsTeachService.saveTeach(input);
        	
        	Batch batch = this.cmsBatchService.getBatch(input.getBatchId());
        	Section section = this.cmsSectionService.getSection(input.getSectionId());
        	
        	AttendanceMaster am = this.cmsAttendanceMasterService.saveAttendanceMaster(batch, section, teach);
        	
//        	vo = new CmsSubjectVo();
        	provideSubjectDependencies(am, vo);
//    		
//        	List<CmsSubjectVo> ls =  getMergedSubjectAndAttendanceMasterList();
//        	vo.setDataList(ls);
//        	
//        	vo.setExitCode(0L);
//        	if(input.getId() == null) {
//        		vo.setExitDescription("Teacher and Subject mapping is added successfully");
//        		logger.debug("Teacher and Subject mapping is added successfully.");
//        	}else {
//        		vo.setExitDescription("Teacher and Subject mapping is updated successfully");
//        		logger.debug("Teacher and Subject mapping is updated successfully");
//        	}
//        	
//    	}catch(Exception  e) {
//    		vo = new CmsSubjectVo();
//        	vo.setExitCode(1L);
//        	vo.setExitDescription("Due to some exception, teacher could not be assigned to subject.");
//    		logger.error("Teacer and Subject mapping save failed. Exception : ",e);
//    	}
    	
//    	return vo;
    }
	
	public List<CmsSubjectVo> getMergedSubjectAndAttendanceMasterList(){
    	Map<String, String> m = new HashMap<String, String>();
    	List<Subject> subjectList =  getSubjectListOnFilterCriteria(m);
    	
    	List<AttendanceMaster> amList = this.cmsAttendanceMasterService.getAttendanceMasterListOnFilterCriteria(m);
    	Map<Long, Subject> map = new HashMap<Long, Subject>();
    	
    	for(Subject s: subjectList) {
    		map.put(s.getId(), s);
    	}
    	
    	for(AttendanceMaster am: amList) {
    		if(map.containsKey(am.getTeach().getSubject().getId())) {
    			map.remove(am.getTeach().getSubject().getId());
    		}
		}
    	
    	List<CmsSubjectVo> list = new ArrayList<>();
    	
    	for(Subject sub: map.values()) {
    		CmsSubjectVo vo = CommonUtil.createCopyProperties(sub, CmsSubjectVo.class);
    		convertDatesAndProvideDependencies(sub, vo);
//    		vo.setDepartmentId(sub.getDepartment().getId());
//    		CmsDepartmentVo cmsDepartmentVo = CommonUtil.createCopyProperties(sub.getDepartment(), CmsDepartmentVo.class);
//    		vo.setCmsDepartmentVo(cmsDepartmentVo);
    		list.add(vo);
    	}
    	
    	for(AttendanceMaster am: amList) {
    		CmsSubjectVo vo = CommonUtil.createCopyProperties(am.getTeach().getSubject(), CmsSubjectVo.class);
    		provideSubjectDependencies(am, vo);
    		
    		list.add(vo);
    	}
    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return list;
    }

	private void provideSubjectDependencies(AttendanceMaster am, CmsSubjectVo vo) {
		vo.setTeacherId(am.getTeach().getTeacher().getId());
		CmsTeacherVo ctVo = CommonUtil.createCopyProperties(am.getTeach().getTeacher(), CmsTeacherVo.class);
		vo.setCmsTeacherVo(ctVo);
		
		vo.setTeachId(am.getTeach().getId());
		CmsTeachVo cthVo = CommonUtil.createCopyProperties(am.getTeach(), CmsTeachVo.class);
		vo.setCmsTeachVo(cthVo);
		
		vo.setBatchId(am.getBatch().getId());
		CmsBatchVo cmsBatchVo = CommonUtil.createCopyProperties(am.getBatch(), CmsBatchVo.class);
		vo.setCmsBatchVo(cmsBatchVo);
		
		vo.setSectionId(am.getSection().getId());
		CmsSectionVo cmsSectionVo = CommonUtil.createCopyProperties(am.getSection(), CmsSectionVo.class);
		vo.setCmsSectionVo(cmsSectionVo);
		
		vo.setAttendanceMasterId(am.getId());
		CmsAttendanceMasterVo cmsAttendanceMasterVo = CommonUtil.createCopyProperties(am, CmsAttendanceMasterVo.class);
		vo.setCmsAttendanceMasterVo(cmsAttendanceMasterVo);
		
		vo.setDepartmentId(am.getBatch().getDepartment().getId());
		CmsDepartmentVo cmsDepartmentVo = CommonUtil.createCopyProperties(am.getBatch().getDepartment(), CmsDepartmentVo.class);
		vo.setCmsDepartmentVo(cmsDepartmentVo);
	}
	
}
