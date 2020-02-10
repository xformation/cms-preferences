package com.synectiks.pref.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AuthorizedSignatory;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.LegalEntity;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;


public class LegalEntityDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public LegalEntityDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
		this.sheetName = sheetName;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		LegalEntity obj = CommonUtil.createCopyProperties(cls.newInstance(), LegalEntity.class);
		
		String branchName = row.getCellAsString(0).orElse(null);
		if(CommonUtil.isNullOrEmpty(branchName)) {
			sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Field name - branch_id");
		}else {
			Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(b.isPresent()) {
                obj.setBranch(b.get());
                obj.setLegalNameOfCollege(b.get().getBranchName());
                obj.setRegisteredOfficeAddress(b.get().getAddress());
            }else {
                sb.append("branch_id, ");
                logger.warn("Branch not found. Given branch name : "+branchName);
            }
		}
		
		String dateOfIncorporation = row.getCellAsString(1).orElse(null);
        if(CommonUtil.isNullOrEmpty(dateOfIncorporation)) {
            sb.append("date_of_incorporation, ");
            logger.warn("Mandatory field missing. Field name - date_of_incorporation");
        }else {
            obj.setDateOfIncorporation(DateFormatUtil.convertStringToLocalDate(dateOfIncorporation, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        
        String typeOfCollege = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(typeOfCollege)) {
            sb.append("type_of_college, ");
            logger.warn("Mandatory field missing. Field name - type_of_college");
        }else {
        	obj.setTypeOfCollege(typeOfCollege);
        }
        
        String collegeIdentificationNumber = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(collegeIdentificationNumber)) {
            sb.append("college_identification_number, ");
            logger.warn("Mandatory field missing. Field name - college_identification_number");
        }else {
        	obj.setCollegeIdentificationNumber(collegeIdentificationNumber);
        }
        
        String pan = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(pan)) {
            sb.append("pan, ");
            logger.warn("Mandatory field missing. Field name - pan");
        }else {
        	obj.setPan(pan);
        }
        
        String tan = row.getCellAsString(5).orElse(null);
        if(CommonUtil.isNullOrEmpty(tan)) {
            sb.append("tan, ");
            logger.warn("Mandatory field missing. Field name - tan");
        }else {
        	obj.setTan(tan);
        }
        
        String tanCircleNumber = row.getCellAsString(6).orElse(null);
        if(CommonUtil.isNullOrEmpty(tanCircleNumber)) {
            sb.append("tan_circle_number, ");
            logger.warn("Mandatory field missing. Field name - tan_circle_number");
        }else {
        	obj.setTanCircleNumber(tanCircleNumber);
        }
        
        String formSignatory = row.getCellAsString(7).orElse(null);
        if(CommonUtil.isNullOrEmpty(formSignatory)) {
            sb.append("form_signatory, ");
            logger.warn("Mandatory field missing. Field name - form_signatory");
        }else {
        	AuthorizedSignatory as = new AuthorizedSignatory();
            as.setEmailId(formSignatory);
            Optional<AuthorizedSignatory> oas = this.allRepositories.findRepository("authorized_signatory").findOne(Example.of(as));
            if(oas.isPresent()) {
            	obj.setFormSignatory(oas.get().getId());
            }
        }
        
        String citTdsLocation = row.getCellAsString(8).orElse(null);
        if(CommonUtil.isNullOrEmpty(citTdsLocation)) {
            sb.append("cit_tds_location, ");
            logger.warn("Mandatory field missing. Field name - cit_tds_location");
        }else {
            obj.setCitTdsLocation(citTdsLocation);
        }

        String pfNumber = row.getCellAsString(9).orElse(null);
        if(CommonUtil.isNullOrEmpty(pfNumber)) {
            sb.append("pf_number, ");
            logger.warn("Mandatory field missing. Field name - pf_number");
        }else {
            obj.setPfNumber(pfNumber);
        }
        
        String pfRegistrationDate = row.getCellAsString(10).orElse(null);
        if(CommonUtil.isNullOrEmpty(pfRegistrationDate)) {
            sb.append("pf_registration_date, ");
            logger.warn("Mandatory field missing. Field name - pf_registration_date");
        }else {
            obj.setPfRegistrationDate(DateFormatUtil.convertStringToLocalDate(pfRegistrationDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        
        String pfSignatory = row.getCellAsString(11).orElse(null);
        if(CommonUtil.isNullOrEmpty(pfSignatory)) {
            sb.append("pf_signatory, ");
            logger.warn("Mandatory field missing. Field name - pf_signatory");
        }else {
        	AuthorizedSignatory as = new AuthorizedSignatory();
            as.setEmailId(pfSignatory);
            Optional<AuthorizedSignatory> oas = this.allRepositories.findRepository("authorized_signatory").findOne(Example.of(as));
            if(oas.isPresent()) {
            	obj.setPfSignatory(oas.get().getId());
            }
        }
        
        String esiNumber = row.getCellAsString(12).orElse(null);
        if(CommonUtil.isNullOrEmpty(esiNumber)) {
            sb.append("esi_number, ");
            logger.warn("Mandatory field missing. Field name - esi_number");
        }else {
            obj.setEsiNumber(esiNumber);
        }
        
        String esiRegistrationDate = row.getCellAsString(13).orElse(null);
        if(CommonUtil.isNullOrEmpty(esiRegistrationDate)) {
            sb.append("esi_registration_date, ");
            logger.warn("Mandatory field missing. Field name - esi_registration_date");
        }else {
            obj.setEsiRegistrationDate(DateFormatUtil.convertStringToLocalDate(esiRegistrationDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        
        String esiSignatory = row.getCellAsString(14).orElse(null);
        if(CommonUtil.isNullOrEmpty(esiSignatory)) {
            sb.append("esi_signatory, ");
            logger.warn("Mandatory field missing. Field name - esi_signatory");
        }else {
        	AuthorizedSignatory as = new AuthorizedSignatory();
            as.setEmailId(esiSignatory);
            Optional<AuthorizedSignatory> oas = this.allRepositories.findRepository("authorized_signatory").findOne(Example.of(as));
            if(oas.isPresent()) {
            	obj.setEsiSignatory(oas.get().getId());
            }
        }
        
        String ptNumber = row.getCellAsString(15).orElse(null);
        if(CommonUtil.isNullOrEmpty(ptNumber)) {
            sb.append("pt_number, ");
            logger.warn("Mandatory field missing. Field name - pt_number");
        }else {
            obj.setPtNumber(ptNumber);
        }
        
        String ptRegistrationDate = row.getCellAsString(16).orElse(null);
        if(CommonUtil.isNullOrEmpty(esiRegistrationDate)) {
            sb.append("pt_registration_date, ");
            logger.warn("Mandatory field missing. Field name - pt_registration_date");
        }else {
            obj.setPtRegistrationDate(DateFormatUtil.convertStringToLocalDate(ptRegistrationDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        
        String ptSignatory = row.getCellAsString(17).orElse(null);
        if(CommonUtil.isNullOrEmpty(esiSignatory)) {
            sb.append("pt_signatory, ");
            logger.warn("Mandatory field missing. Field name - pt_signatory");
        }else {
        	AuthorizedSignatory as = new AuthorizedSignatory();
            as.setEmailId(ptSignatory);
            Optional<AuthorizedSignatory> oas = this.allRepositories.findRepository("authorized_signatory").findOne(Example.of(as));
            if(oas.isPresent()) {
            	obj.setPtSignatory(oas.get().getId());
            }
        }
        
        String status = row.getCellAsString(18).orElse(null);
		if(CommonUtil.isNullOrEmpty(status)) {
			sb.append("status, ");
			logger.warn("Mandatory field missing. Field name - status");
		}else {
			obj.setStatus(status);
		}
		
		return (T)obj;
	}
	
}
