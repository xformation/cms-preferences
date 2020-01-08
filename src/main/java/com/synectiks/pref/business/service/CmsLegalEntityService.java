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
import com.synectiks.pref.domain.LegalEntity;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsLegalEntityVo;
import com.synectiks.pref.graphql.types.legalentity.LegalEntityInput;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.repository.LegalEntityRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsLegalEntityService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    LegalEntityRepository legalEntityRepository;
    
    
    public List<CmsLegalEntityVo> getLegalEntityList(){
    	List<LegalEntity> list = this.legalEntityRepository.findAll();
    	List<CmsLegalEntityVo> ls = changeLegalEntityToCmsLegalEntityList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    public CmsLegalEntityVo getLegalEntity(Long branchId){
    	Optional<Branch> oBranch = branchRepository.findById(branchId);
    	if(!oBranch.isPresent()) {
    		logger.warn("Branch not found for the given branch id : "+branchId+". Returning null legal entity");
    		return null;
    	}
    	LegalEntity le = new LegalEntity();
    	le.setBranch(oBranch.get());
    	Optional<LegalEntity> ole = this.legalEntityRepository.findOne(Example.of(le));
    	if(ole.isPresent()) {
    		return changeToCmsLegalEntity(ole.get());
    	}
    	return null;
    }
    
    private CmsLegalEntityVo changeToCmsLegalEntity(LegalEntity le) {
    	CmsLegalEntityVo vo = CommonUtil.createCopyProperties(le, CmsLegalEntityVo.class);
		
		if(le.getCreatedOn() != null) {
        	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(le.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	vo.setCreatedOn(null);
        }
        if(le.getUpdatedOn() != null) {
        	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(le.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	vo.setUpdatedOn(null);
        }
        
        if(le.getDateOfIncorporation() != null) {
        	vo.setStrDateOfIncorporation(DateFormatUtil.changeLocalDateFormat(le.getDateOfIncorporation(), CmsConstants.DATE_FORMAT_MM_dd_yyyy));
        }
        if(le.getPfRegistrationDate() != null) {
        	vo.setStrPfRegistrationDate(DateFormatUtil.changeLocalDateFormat(le.getPfRegistrationDate(), CmsConstants.DATE_FORMAT_MM_dd_yyyy));
        }
        if(le.getEsiRegistrationDate() != null) {
        	vo.setStrEsiRegistrationDate(DateFormatUtil.changeLocalDateFormat(le.getEsiRegistrationDate(), CmsConstants.DATE_FORMAT_MM_dd_yyyy));
        }
        if(le.getPtRegistrationDate() != null) {
        	vo.setStrPtRegistrationDate(DateFormatUtil.changeLocalDateFormat(le.getPtRegistrationDate(), CmsConstants.DATE_FORMAT_MM_dd_yyyy));
        }
        
        if(le.getBranch() != null) {
        	vo.setBranchId(le.getBranch().getId());
        	CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(le.getBranch(), CmsBranchVo.class); 
    		vo.setCmsBranchVo(cmsBvo);
        }
        return vo;
    }
    
    private List<CmsLegalEntityVo> changeLegalEntityToCmsLegalEntityList(List<LegalEntity> list){
    	List<CmsLegalEntityVo> ls = new ArrayList<>();
    	for(LegalEntity le: list) {
            ls.add(changeToCmsLegalEntity(le));
    	}
    	return ls;
    }
    
    public CmsLegalEntityVo saveLegalEntity(LegalEntityInput inp) {
    	logger.info("Saving legalEntity");
    	CmsLegalEntityVo vo = null;
    	try {
    		LegalEntity le = null;
    		if(inp.getId() == null) {
    			logger.debug("Adding new legalEntity");
    			le = CommonUtil.createCopyProperties(inp, LegalEntity.class);
    			le.setCreatedOn(LocalDate.now());
    			le.setDateOfIncorporation(DateFormatUtil.convertStringToLocalDate(inp.getStrDateOfIncorporation(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setPfRegistrationDate(DateFormatUtil.convertStringToLocalDate(inp.getStrPfRegistrationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setEsiRegistrationDate(DateFormatUtil.convertStringToLocalDate(inp.getStrEsiRegistrationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setPtRegistrationDate(DateFormatUtil.convertStringToLocalDate(inp.getStrPtRegistrationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setStatus(CmsConstants.STATUS_ACTIVE);
    		}else {
    			logger.debug("Updating existing legalEntity");
    			le = this.legalEntityRepository.findById(inp.getId()).get();
    			le.setUpdatedOn(LocalDate.now());
    			le.setLegalNameOfCollege(inp.getLegalNameOfCollege());
    			le.setTypeOfCollege(inp.getTypeOfCollege());
    			le.setDateOfIncorporation(DateFormatUtil.convertStringToLocalDate(inp.getStrDateOfIncorporation(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setRegisteredOfficeAddress(inp.getRegisteredOfficeAddress());
    			le.setCollegeIdentificationNumber(inp.getCollegeIdentificationNumber());
    			le.setPan(inp.getPan());
    			le.setTan(inp.getTan());
    			le.setTanCircleNumber(inp.getTanCircleNumber());
    			le.setCitTdsLocation(inp.getCitTdsLocation());
    			le.setFormSignatory(inp.getFormSignatory());
    			le.setPfNumber(inp.getPfNumber());
    			le.setPfRegistrationDate(DateFormatUtil.convertStringToLocalDate(inp.getStrPfRegistrationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setPfSignatory(inp.getPfSignatory());
    			le.setEsiNumber(inp.getEsiNumber());
    			le.setEsiRegistrationDate(DateFormatUtil.convertStringToLocalDate(inp.getStrEsiRegistrationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setEsiSignatory(inp.getEsiSignatory());
    			le.setPtNumber(inp.getPtNumber());
    			le.setPtRegistrationDate(DateFormatUtil.convertStringToLocalDate(inp.getStrPtRegistrationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    			le.setPtSignatory(inp.getPtSignatory());
    		}
        	Optional<Branch> oBranch = branchRepository.findById(inp.getBranchId());
        	if(oBranch.isPresent()) {
        		le.setBranch(oBranch.get());
        	}
        	
        	le = legalEntityRepository.save(le);
        	vo = CommonUtil.createCopyProperties(le, CmsLegalEntityVo.class);
        	vo.setStrCreatedOn(le.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(le.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(le.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(le.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	if(oBranch.isPresent()) {
        		CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(oBranch.get(), CmsBranchVo.class); 
        		vo.setCmsBranchVo(cmsBvo);
        	}
        	
        	vo.setExitCode(0L);
        	if(inp.getId() == null) {
        		vo.setExitDescription("LegalEntity is added successfully");
        		logger.debug("LegalEntity is added successfully");
        	}else {
        		vo.setExitDescription("LegalEntity is updated successfully");
        		logger.debug("LegalEntity is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsLegalEntityVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, legalEntity data could not be saved");
    		logger.error("LegalEntity save failed. Exception : ",e);
    	}
    	logger.info("LegalEntity saved successfully");
    	List ls =  getLegalEntityList();
        vo.setDataList(ls);
        return vo;
    }
}
