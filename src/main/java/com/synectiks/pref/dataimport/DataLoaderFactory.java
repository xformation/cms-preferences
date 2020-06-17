package com.synectiks.pref.dataimport;

import java.util.Map;

import com.synectiks.pref.dataimport.loader.*;
import org.springframework.stereotype.Component;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.service.util.CommonUtil;

@Component
public class DataLoaderFactory {

	public DataLoader getLoader(String tableName, AllRepositories allRepositories){

        if(tableName == null){
        	return null;
        }

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
        if(tableName.equalsIgnoreCase("BANK_ACCOUNTS")){
            return new BankAccountsDataLoader(tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("LEGAL_ENTITY")){
            return new LegalEntityDataLoader(tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("ACADEMIC_YEAR")){
            return new AcademicYearLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("HOLIDAY")){
            return new HolidayDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("TERM")){
            return new TermDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("DEPARTMENT")){
            return new DepartmentLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("TEACHER")){
            return new TeacherDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("SUBJECT")){
            return new SubjectDataLoader (tableName, allRepositories);
        }

//        if(tableName.equalsIgnoreCase("ATTENDANCE_MASTER")){
//            return new AttendanceMasterDataLoader (tableName, allRepositories);
//        }

        if(tableName.equalsIgnoreCase("STUDENT")){
            return new StudentDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("FEE_CATEGORY")){
            return new FeeCategoryLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("TRANSPORT_ROUTE")){
            return new TransportRouteDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("FACILITY")){
            return new FacilityDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("FEE_DETAILS")){
            return new FeeDetailsDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("ADMISSION_ENQUIRY")){
            return new AdmissionEnquiryDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("ADMISSION_APPLICATION")){
            return new AdmissionApplicationDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("CONTRACT")){
            return new ContractDataLoader(tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("DUE_DATE")){
            return new DueDateDataLoader(tableName, allRepositories);
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
