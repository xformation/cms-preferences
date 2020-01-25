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

import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.enumeration.BatchEnum;
import com.synectiks.pref.domain.vo.CmsBatchVo;
import com.synectiks.pref.domain.vo.CmsDepartmentVo;
import com.synectiks.pref.repository.BatchRepository;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class CmsBatchService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    CmsDepartmentService cmsDepartmentService;

    @Autowired
    CmsSectionService cmsSectionService;
    
    public List<CmsBatchVo> getCmsBatchListOnFilterCriteria(Map<String, String> criteriaMap){
    	Batch obj = new Batch();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("departmentId") != null) {
    		Department department = cmsDepartmentService.getDepartment(Long.parseLong(criteriaMap.get("departmentId")));
    		obj.setDepartment(department);
    		isFilter = true;
    	}
    	List<Batch> list = null;
    	if(isFilter) {
    		list = this.batchRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.batchRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsBatchVo> ls = changeBatchToCmsBatchList(list);
    	Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return ls;
    }
    
    public List<Batch> getBatchListOnFilterCriteria(Map<String, String> criteriaMap){
    	Batch obj = new Batch();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("departmentId") != null) {
    		Department department = cmsDepartmentService.getDepartment(Long.parseLong(criteriaMap.get("departmentId")));
    		obj.setDepartment(department);
    		isFilter = true;
    	}
    	
    	List<Batch> list = null;
    	if(isFilter) {
    		list = this.batchRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.batchRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return list;
    }
    
    public CmsBatchVo getCmsBatch(Long id){
    	Optional<Batch> br = this.batchRepository.findById(id);
    	if(br.isPresent()) {
    		CmsBatchVo vo = CommonUtil.createCopyProperties(br.get(), CmsBatchVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsBatch for given id : "+id+". CmsBatch object : "+vo);
        	return vo;
    	}
    	logger.debug("Batch object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Batch getBatch(Long id){
    	Optional<Batch> br = this.batchRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Batch object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    private List<CmsBatchVo> changeBatchToCmsBatchList(List<Batch> list){
    	List<CmsBatchVo> ls = new ArrayList<>();
    	for(Batch tr: list) {
    		CmsBatchVo vo = CommonUtil.createCopyProperties(tr, CmsBatchVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	public void convertDatesAndProvideDependencies(Batch tr, CmsBatchVo vo) {
		if(tr.getDepartment() != null) {
			vo.setDepartmentId(tr.getDepartment().getId());
			CmsDepartmentVo cmsDvo =CommonUtil.createCopyProperties(tr.getDepartment(), CmsDepartmentVo.class); 
//			this.cmsDepartmentService.convertDatesAndProvideDependencies(tr.getDepartment(), cmsDvo);
			vo.setCmsDepartmentVo(cmsDvo);
		}
	}
    
    public void saveBatch(Long departmentId) {
    	Department department = this.cmsDepartmentService.getDepartment(departmentId);
    	logger.debug("Making entries in batch for the given department. Department id : "+departmentId);
    	for (BatchEnum be : BatchEnum.values()) { 
            Batch batch = new Batch();
            batch.setDepartment(department);
            batch.setBatch(be);
            batch = batchRepository.save(batch);
            this.cmsSectionService.saveSection(batch.getId());
        } 
    }
    
    
}
