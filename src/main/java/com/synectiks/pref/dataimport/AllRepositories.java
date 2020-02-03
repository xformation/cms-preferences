package com.synectiks.pref.dataimport;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class AllRepositories {

	@Autowired
	private ApplicationContext context;
	
	public <T> JpaRepository findRepository (String table) {
		if(CommonUtil.isNullOrEmpty(table)) return null;
		Map<String, CmsTableWithDomainAndRepositoryMapper> map = CmsConstants.initTableDomainRepositoryMapperMap();
		CmsTableWithDomainAndRepositoryMapper mapper = map.get(table);
		return (JpaRepository)context.getBean(mapper.getRepository());
	}
		
}
