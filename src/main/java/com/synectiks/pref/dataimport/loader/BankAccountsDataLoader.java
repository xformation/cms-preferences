package com.synectiks.pref.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.BankAccounts;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.service.util.CommonUtil;


public class BankAccountsDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public BankAccountsDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
		this.sheetName = sheetName;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		BankAccounts obj = CommonUtil.createCopyProperties(cls.newInstance(), BankAccounts.class);
		
		String bankName = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(bankName)) {
            sb.append("bank_name, ");
            logger.warn("Mandatory field missing. Field name - bank_name");
        }else {
            obj.setBankName(bankName);
        }
        
        String accountNumber = row.getCellAsString(1).orElse(null);
        if(CommonUtil.isNullOrEmpty(accountNumber)) {
            sb.append("account_number, ");
            logger.warn("Mandatory field missing. Field name - account_number");
        }else {
        	obj.setAccountNumber(accountNumber);
        }
        
        String typeOfAccount = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(typeOfAccount)) {
            sb.append("type_of_account, ");
            logger.warn("Mandatory field missing. Field name - type_of_account");
        }else {
        	obj.setTypeOfAccount(typeOfAccount);
        }
        
        String ifscCode = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(ifscCode)) {
            sb.append("ifsc_code, ");
            logger.warn("Mandatory field missing. Field name - ifsc_code");
        }else {
        	obj.setIfscCode(ifscCode);
        }
        
        String address = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(address)) {
            sb.append("address, ");
            logger.warn("Mandatory field missing. Field name - address");
        }else {
        	obj.setAddress(address);
        }
        
        String corporateId = row.getCellAsString(5).orElse(null);
        if(CommonUtil.isNullOrEmpty(corporateId)) {
            sb.append("corporate_id, ");
            logger.warn("Mandatory field missing. Field name - corporate_id");
        }else {
        	obj.setCorporateId(corporateId);
        }
        
        String branchName = row.getCellAsString(6).orElse(null);
		if(CommonUtil.isNullOrEmpty(branchName)) {
			sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Field name - branch_id");
		}else {
			Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(b.isPresent()) {
                obj.setBranch(b.get());
            }else {
                sb.append("branch_id, ");
                logger.warn("Branch not found. Given branch name : "+branchName);
            }
		}

		String status = row.getCellAsString(7).orElse(null);
		if(CommonUtil.isNullOrEmpty(status)) {
			sb.append("status, ");
			logger.warn("Mandatory field missing. Field name - status");
		}else {
			obj.setStatus(status);
		}
		
		return (T)obj;
	}
	
}
