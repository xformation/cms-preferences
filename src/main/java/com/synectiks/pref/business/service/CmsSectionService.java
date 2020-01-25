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
import com.synectiks.pref.domain.Section;
import com.synectiks.pref.domain.enumeration.SectionEnum;
import com.synectiks.pref.domain.vo.CmsBatchVo;
import com.synectiks.pref.domain.vo.CmsSectionVo;
import com.synectiks.pref.repository.SectionRepository;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class CmsSectionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    CmsBatchService cmsBatchService;

        
    public List<CmsSectionVo> getCmsSectionListOnFilterCriteria(Map<String, String> criteriaMap){
    	Section obj = new Section();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("batchId") != null) {
    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
    		obj.setBatch(batch);
    		isFilter = true;
    	}
    	List<Section> list = null;
    	if(isFilter) {
    		list = this.sectionRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.sectionRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	List<CmsSectionVo> ls = changeSectionToCmsSectionList(list);
    	Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return ls;
    }
    
    public List<Section> getSectionListOnFilterCriteria(Map<String, String> criteriaMap){
    	Section obj = new Section();
    	boolean isFilter = false;
    	if(criteriaMap.get("id") != null) {
    		obj.setId(Long.parseLong(criteriaMap.get("id")));
    		isFilter = true;
    	}
    	if(criteriaMap.get("batchId") != null) {
    		Batch batch = cmsBatchService.getBatch(Long.parseLong(criteriaMap.get("batchId")));
    		obj.setBatch(batch);
    		isFilter = true;
    	}
    	
    	List<Section> list = null;
    	if(isFilter) {
    		list = this.sectionRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
    	}else {
    		list = this.sectionRepository.findAll(Sort.by(Direction.DESC, "id"));
    	}
        
    	Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    	return list;
    }
    
    public CmsSectionVo getCmsSection(Long id){
    	Optional<Section> br = this.sectionRepository.findById(id);
    	if(br.isPresent()) {
    		CmsSectionVo vo = CommonUtil.createCopyProperties(br.get(), CmsSectionVo.class);
    		convertDatesAndProvideDependencies(br.get(), vo);
    		logger.debug("CmsSection for given id : "+id+". CmsSection object : "+vo);
        	return vo;
    	}
    	logger.debug("Section object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Section getSection(Long id){
    	Optional<Section> br = this.sectionRepository.findById(id);
    	if(br.isPresent()) {
    		return br.get();
    	}
    	logger.debug("Section object not found for the given id. "+id+". Returning null");
        return null;
    }
    
    private List<CmsSectionVo> changeSectionToCmsSectionList(List<Section> list){
    	List<CmsSectionVo> ls = new ArrayList<>();
    	for(Section tr: list) {
    		CmsSectionVo vo = CommonUtil.createCopyProperties(tr, CmsSectionVo.class);
    		convertDatesAndProvideDependencies(tr, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	private void convertDatesAndProvideDependencies(Section tr, CmsSectionVo vo) {
		if(tr.getBatch() != null) {
			vo.setBatchId(tr.getBatch().getId());
			CmsBatchVo cmsDvo =CommonUtil.createCopyProperties(tr.getBatch(), CmsBatchVo.class); 
			vo.setCmsBatchVo(cmsDvo);
		}
	}
    
    public void saveSection(Long batchId) {
    	Batch batch = this.cmsBatchService.getBatch(batchId);
    	logger.debug("Making entries in section for the given batch. Batch id : "+batchId);
    	for (SectionEnum be : SectionEnum.values()) { 
            Section section = new Section();
            section.setBatch(batch);
            section.setSection(be);
            sectionRepository.save(section);
        } 
    }
    
    
}
