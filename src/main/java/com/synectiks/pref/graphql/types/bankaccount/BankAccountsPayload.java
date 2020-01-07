package com.synectiks.pref.graphql.types.bankaccount;

import com.synectiks.pref.domain.vo.CmsBankAccountsVo;

public class BankAccountsPayload {
    private final CmsBankAccountsVo cmsBankAccountsVo;

    public BankAccountsPayload(CmsBankAccountsVo bankAccountsVo){
        this.cmsBankAccountsVo = bankAccountsVo;
    }

	public CmsBankAccountsVo getCmsBankAccountsVo() {
		return cmsBankAccountsVo;
	}

    
}
