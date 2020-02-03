package com.synectiks.pref.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AuthorizedSignatory;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.service.util.CommonUtil;


public class AuthorizedSignatoryLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public AuthorizedSignatoryLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
		this.sheetName = sheetName;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		AuthorizedSignatory obj = CommonUtil.createCopyProperties(cls.newInstance(), AuthorizedSignatory.class);
		obj.setName(row.getCellAsString(0).orElse(null));
        obj.setFatherName(row.getCellAsString(1).orElse(null));
        obj.setDesignation(row.getCellAsString(2).orElse(null));
        obj.setAddress(row.getCellAsString(3).orElse(null));
		obj.setEmailId(row.getCellAsString(4).orElse(null));
        obj.setPanNo(row.getCellAsString(5).orElse(null));
        
		String branchName = row.getCellAsString(6).orElse(null);
		String branchAddress = row.getCellAsString(7).orElse(null);
		if(!CommonUtil.isNullOrEmpty(branchName) && !CommonUtil.isNullOrEmpty(branchAddress)) {
			Branch branch = new Branch();
			branch.setBranchName(branchName);
			branch.setAddress(branchAddress);
			Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
			obj.setBranch(b.isPresent() ? b.get() : null);
		}
        

		return (T)obj;
	}
	
}
