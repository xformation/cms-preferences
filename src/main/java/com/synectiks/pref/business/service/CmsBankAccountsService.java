package com.synectiks.pref.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.pref.config.Constants;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.BankAccounts;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.vo.CmsBankAccountsVo;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.graphql.types.bankaccount.BankAccountsInput;
import com.synectiks.pref.repository.BankAccountsRepository;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;

@Component
public class CmsBankAccountsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BankAccountsRepository bankAccountsRepository;
    
    
    public List<CmsBankAccountsVo> getBankAccountsList(){
    	List<BankAccounts> list = this.bankAccountsRepository.findAll();
    	List<CmsBankAccountsVo> ls = changeBankAccountsToCmsBankAccountsList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
    
    private List<CmsBankAccountsVo> changeBankAccountsToCmsBankAccountsList(List<BankAccounts> list){
    	List<CmsBankAccountsVo> ls = new ArrayList<>();
    	for(BankAccounts ba: list) {
    		CmsBankAccountsVo vo = CommonUtil.createCopyProperties(ba, CmsBankAccountsVo.class);
    		
    		if(ba.getCreatedOn() != null) {
            	vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ba.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setCreatedOn(null);
            }
            if(ba.getUpdatedOn() != null) {
            	vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ba.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	vo.setUpdatedOn(null);
            }
            if(ba.getBranch() != null) {
            	vo.setBranchId(ba.getBranch().getId());
            	CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(ba.getBranch(), CmsBranchVo.class); 
        		vo.setCmsBranchVo(cmsBvo);
            }
            ls.add(vo);
    	}
    	return ls;
    }
    
    public CmsBankAccountsVo saveBankAccounts(BankAccountsInput cmsBankAccountsVo) {
    	logger.info("Saving bankAccounts");
    	CmsBankAccountsVo vo = null;
    	try {
    		BankAccounts bankAccounts = null;
    		if(cmsBankAccountsVo.getId() == null) {
    			logger.debug("Adding new bankAccounts");
    			bankAccounts = CommonUtil.createCopyProperties(cmsBankAccountsVo, BankAccounts.class);
    			bankAccounts.setCreatedOn(LocalDate.now());
    		}else {
    			logger.debug("Updating existing bankAccounts");
    			bankAccounts = this.bankAccountsRepository.findById(cmsBankAccountsVo.getId()).get();
    			bankAccounts.setUpdatedOn(LocalDate.now());
    			bankAccounts.setBankName(cmsBankAccountsVo.getBankName());
    			bankAccounts.setAccountNumber(cmsBankAccountsVo.getAccountNumber());
    			bankAccounts.setTypeOfAccount(cmsBankAccountsVo.getTypeOfAccount());
    			bankAccounts.setIfscCode(cmsBankAccountsVo.getIfscCode());
    			bankAccounts.setAddress(cmsBankAccountsVo.getAddress());
    			bankAccounts.setCorporateId(cmsBankAccountsVo.getCorporateId());
    			bankAccounts.setStatus(cmsBankAccountsVo.getStatus());
    		}
        	Optional<Branch> oBranch = branchRepository.findById(cmsBankAccountsVo.getBranchId());
        	if(oBranch.isPresent()) {
        		bankAccounts.setBranch(oBranch.get());
        	}
        	
        	bankAccounts = bankAccountsRepository.save(bankAccounts);
        	vo = CommonUtil.createCopyProperties(bankAccounts, CmsBankAccountsVo.class);
        	vo.setStrCreatedOn(bankAccounts.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(bankAccounts.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(bankAccounts.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(bankAccounts.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	if(oBranch.isPresent()) {
        		CmsBranchVo cmsBvo =CommonUtil.createCopyProperties(oBranch.get(), CmsBranchVo.class); 
        		vo.setCmsBranchVo(cmsBvo);
        	}
        	
        	vo.setExitCode(0L);
        	if(cmsBankAccountsVo.getId() == null) {
        		vo.setExitDescription("BankAccounts is added successfully");
        		logger.debug("BankAccounts is added successfully");
        	}else {
        		vo.setExitDescription("BankAccounts is updated successfully");
        		logger.debug("BankAccounts is updated successfully");
        	}
        	
        }catch(Exception e) {
        	vo = new CmsBankAccountsVo();
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, bankAccounts data could not be saved");
    		logger.error("BankAccounts save failed. Exception : ",e);
    	}
    	logger.info("BankAccounts saved successfully");
    	List ls =  getBankAccountsList();
        vo.setDataList(ls);
        return vo;
    }
}
