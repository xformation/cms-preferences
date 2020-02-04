package com.synectiks.pref.dataimport;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.loader.AcademicYearLoader;
import com.synectiks.pref.dataimport.loader.AttendanceMasterDataLoader;
import com.synectiks.pref.dataimport.loader.AuthorizedSignatoryLoader;
import com.synectiks.pref.dataimport.loader.BatchDataLoader;
import com.synectiks.pref.dataimport.loader.BranchDataLoader;
import com.synectiks.pref.dataimport.loader.CollegeDataLoader;
import com.synectiks.pref.dataimport.loader.DepartmentLoader;
import com.synectiks.pref.dataimport.loader.HolidayDataLoader;
import com.synectiks.pref.dataimport.loader.SectionDataLoader;
import com.synectiks.pref.dataimport.loader.TeacherDataLoader;
import com.synectiks.pref.dataimport.loader.TermDataLoader;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class DataLoaderFactory {
	
	public DataLoader getLoader(String tableName, AllRepositories allRepositories){  
		
        if(tableName == null){  
        	return null;  
        }  
//        if(tableName.equalsIgnoreCase("STUDENT")) {  
//            return new StudentDataLoader(this.studentRepository, tableName);  
//        }   
        
//        if(tableName.equalsIgnoreCase("COUNTRY")) {  
//        	return new CountryDataLoader(tableName, allRepositories); 
//        }
//        if(tableName.equalsIgnoreCase("STATE")) {  
//        	return new StateDataLoader(tableName, allRepositories);  
//        }
//        if(tableName.equalsIgnoreCase("CITY")) {  
//        	return new CityDataLoader(tableName, allRepositories); 
//        }
        if(tableName.equalsIgnoreCase("COLLEGE")) {  
        	return new CollegeDataLoader(tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("BRANCH")) {  
        	return new BranchDataLoader(tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("AUTHORIZED_SIGNATORY")){
            return new AuthorizedSignatoryLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("ACADEMIC_YEAR")){
            return new AcademicYearLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("DEPARTMENT")){
            return new DepartmentLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("TERM")){
            return new TermDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("HOLIDAY")){
            return new HolidayDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("BATCH")){
            return new BatchDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("TEACHER")){
            return new TeacherDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("ATTENDANCE_MASTER")){
            return new AttendanceMasterDataLoader (tableName, allRepositories);
        }
//        if(tableName.equalsIgnoreCase("STUDENT")){
//            return new StudentDataLoader(tableName, allRepositories);
//        }
        if(tableName.equalsIgnoreCase("SECTION")){
            return new SectionDataLoader(tableName, allRepositories);
        }
        return null;  
	}
	
	public Class getClassName(String table) {
		if(CommonUtil.isNullOrEmpty(table)) return null;
		Map<String, CmsTableWithDomainAndRepositoryMapper> map = CmsConstants.initTableDomainRepositoryMapperMap();
		CmsTableWithDomainAndRepositoryMapper mapper = map.get(table);
		return mapper.getDomain();
	}
	

}
