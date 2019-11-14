package com.synectiks.pref.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.synectiks.pref.domain.vo.Config;


/**
 * Application constants.
 */
public interface Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";

//    private Constants() {
//    }
    
    String CMS_IMAGE_FILE_PATH = "college_images/";

	String CMS_COLLEGE_LOGO_FILE_NAME = "college_logo";
	String CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME = "dashboard";
	String CMS_LEGAL_ENTITY_LOGO_FILE_NAME = "legalentity_logo";
	
	String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
	String DATE_FORMAT_dd_MM_yyyy = "dd-MM-yyyy";
	String DATE_FORMAT_MM_dd_yyyy = "MM-dd-yyyy";
	String DATE_FORMAT_d_MMM_yyyy = "dd MMM yyyy";
	
	String LECTURE_NOT_SCHEDULED = "LECTURE_NOT_SCHEDULED";
	
	String ADD_SUCCESS_MESSAGE = "Records added successfully";
	String ADD_FAILURE_MESSAGE = "Due to some exception, records could no be added.";
	String UPDATE_SUCCESS_MESSAGE = "Records updated successfully";
	String UPDATE_FAILURE_MESSAGE = "Due to some exception, records could no be updated.";
	
	String OS_WINDOWS = "windows";
	String OS_UNIX = "unix";
	String OS_MAC = "mac";
	String OS_SOLARIS = "solaris";
	String COLLEGE_ID_PLACEHOLDER_REPLACER = "college_id_";
	String BRANCH_ID_PLACEHOLDER_REPLACER = "branch_id_";
	String STUDENT_IMAGE_FILE_PATH = CMS_IMAGE_FILE_PATH+File.separator+"COLLEGE_ID"+File.separator+"student_images";
	
	ConcurrentHashMap<String, Config> USERS_CACHE = new ConcurrentHashMap<String, Config>();
    
//	List<String> tabelName = initTableList();
//	public static List<String> initTableList() {
//		List<String> ls = initTableDomainRepositoryMapperMap().keySet().stream().collect(Collectors.toList());
//		Collections.sort(ls);
//	    return ls;
//	}
	
//	public static Map<String, CmsTableWithDomainAndRepositoryMapper> initTableDomainRepositoryMapperMap() {
//		Map<String, CmsTableWithDomainAndRepositoryMapper> mpr = new HashMap<String, CmsTableWithDomainAndRepositoryMapper>();
//		mpr.put("exception_record", new CmsTableWithDomainAndRepositoryMapper("exception_record", ExceptionRecord.class, ExceptionRecordRepository.class));      
//		mpr.put("country", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));
//		mpr.put("state", new CmsTableWithDomainAndRepositoryMapper("state", State.class, StateRepository.class));
//		mpr.put("city", new CmsTableWithDomainAndRepositoryMapper("city", City.class, CityRepository.class));   
//		mpr.put("currency", new CmsTableWithDomainAndRepositoryMapper("currency", Currency.class, CurrencyRepository.class));             
//		mpr.put("college", new CmsTableWithDomainAndRepositoryMapper("college", College.class, CollegeRepository.class));
//		mpr.put("branch", new CmsTableWithDomainAndRepositoryMapper("branch", Branch.class, BranchRepository.class)); 
//		mpr.put("authorized_signatory", new CmsTableWithDomainAndRepositoryMapper("authorized_signatory", AuthorizedSignatory.class, AuthorizedSignatoryRepository.class)); 
//	    mpr.put("bank_accounts", new CmsTableWithDomainAndRepositoryMapper("bank_accounts", BankAccounts.class, BankAccountsRepository.class));
//	    mpr.put("legal_entity", new CmsTableWithDomainAndRepositoryMapper("legal_entity", LegalEntity.class, LegalEntityRepository.class));         
//	    mpr.put("academic_year", new CmsTableWithDomainAndRepositoryMapper("academic_year", AcademicYear.class, AcademicYearRepository.class));
//	    mpr.put("holiday", new CmsTableWithDomainAndRepositoryMapper("holiday", Holiday.class, HolidayRepository.class));              
//	    mpr.put("term", new CmsTableWithDomainAndRepositoryMapper("term", Term.class, TermRepository.class));                 
//	    mpr.put("department", new CmsTableWithDomainAndRepositoryMapper("department", Department.class, DepartmentRepository.class));           
//	    mpr.put("batch", new CmsTableWithDomainAndRepositoryMapper("batch", Batch.class, BatchRepository.class));                
//	    mpr.put("teacher", new CmsTableWithDomainAndRepositoryMapper("teacher", Teacher.class, TeacherRepository.class));              
//	    mpr.put("subject", new CmsTableWithDomainAndRepositoryMapper("subject", Subject.class, SubjectRepository.class));              
//	    mpr.put("teach", new CmsTableWithDomainAndRepositoryMapper("teach", Teach.class, TeachRepository.class));                		
//	    mpr.put("attendance_master", new CmsTableWithDomainAndRepositoryMapper("attendance_master", AttendanceMaster.class, AttendanceMasterRepository.class));    
//	    mpr.put("invoice", new CmsTableWithDomainAndRepositoryMapper("invoice", Invoice.class, InvoiceRepository.class));              
//	    mpr.put("due_date", new CmsTableWithDomainAndRepositoryMapper("due_date", DueDate.class, DueDateRepository.class));             
//	    mpr.put("payment_remainder", new CmsTableWithDomainAndRepositoryMapper("payment_remainder", PaymentRemainder.class, PaymentRemainderRepository.class));    
//	    mpr.put("late_fee", new CmsTableWithDomainAndRepositoryMapper("late_fee", LateFee.class, LateFeeRepository.class));             
//	    mpr.put("fee_category", new CmsTableWithDomainAndRepositoryMapper("fee_category", FeeCategory.class, FeeCategoryRepository.class));         
//	    mpr.put("fee_details", new CmsTableWithDomainAndRepositoryMapper("fee_details", FeeDetails.class, FeeDetailsRepository.class));          
//	    mpr.put("academic_exam_setting", new CmsTableWithDomainAndRepositoryMapper("academic_exam_setting", AcademicExamSetting.class, AcademicExamSettingRepository.class)); 
//	    mpr.put("academic_history", new CmsTableWithDomainAndRepositoryMapper("academic_history", AcademicHistory.class, AcademicHistoryRepository.class));
//	    mpr.put("admin_attendance", new CmsTableWithDomainAndRepositoryMapper("admin_attendance", AdminAttendance.class, AdminAttendanceRepository.class));
//	    mpr.put("admission_application", new CmsTableWithDomainAndRepositoryMapper("admission_application", AdmissionApplication.class, AdmissionApplicationRepository.class)); 
//	    mpr.put("admission_enquiry", new CmsTableWithDomainAndRepositoryMapper("admission_enquiry", AdmissionEnquiry.class, AdmissionEnquiryRepository.class));   
//	    mpr.put("competitive_exam", new CmsTableWithDomainAndRepositoryMapper("competitive_exam", CompetitiveExam.class, CompetitiveExamRepository.class));     
//	    mpr.put("documents", new CmsTableWithDomainAndRepositoryMapper("documents", Documents.class, DocumentsRepository.class));            
//	    mpr.put("facility", new CmsTableWithDomainAndRepositoryMapper("facility", Facility.class, FacilityRepository.class));             
////	    mpr.put("meta_lecture", new CmsTableWithDomainAndRepositoryMapper("meta_lecture", MetaLecture.class, MetaLectureRepository.class));         
////	    mpr.put("lecture", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));              
//	    mpr.put("section", new CmsTableWithDomainAndRepositoryMapper("section", Section.class, SectionRepository.class));              
//	    mpr.put("student", new CmsTableWithDomainAndRepositoryMapper("student", Student.class, StudentRepository.class));              
////	    mpr.put("student_attendance", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));   
//	    mpr.put("student_exam_report", new CmsTableWithDomainAndRepositoryMapper("student_exam_report", StudentExamReport.class, StudentExamReportRepository.class));  
////	    mpr.put("student_facility_link", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class)); 
//	    mpr.put("transport_route", new CmsTableWithDomainAndRepositoryMapper("transport_route", TransportRoute.class, TransportRouteRepository.class));      
//	    mpr.put("type_of_grading", new CmsTableWithDomainAndRepositoryMapper("type_of_grading", TypeOfGrading.class, TypeOfGradingRepository.class));      
//	    mpr.put("employee", new CmsTableWithDomainAndRepositoryMapper("employee", Employee.class, EmployeeRepository.class));      
//	    
//	    return mpr;
//	}
	
	
	String XLSX_FILE_EXTENSION = "xlsx";
	String XLS_FILE_EXTENSION = "xls";
	int BATCH_SIZE = 100;
	String INFLUXDB_LOG_LEVEL_BASIC = "BASIC";
	String INFLUXDB_LOG_LEVEL_FULL = "FULL";
	String INFLUXDB_LOG_LEVEL_HEADERS = "HEADERS";
	String INFLUXDB_LOG_LEVEL_NONE = "NONE";
	
	// BillDesk payment gateway specific constants
	String HASH_KEY = "uIZ2iayX70hc";
	String HASH_ALGO = "HmacSHA256";
    String MERCHANT_ID = "HMACUAT";
    String SECURITY_ID = "hmacuat";
    String RESPONSE_URL = "http://localhost:8080/api/cmspaymentresponse";
	String PAYMENT_STATUS_FAILED = "550";
	String PAYMENT_REDIRECT_URL = "http://localhost:3000/payment-response";
	
	String ERROR_COLLEGE_ALREADY_EXISTS = "College alreay exists. Application allows only one college.";
	String VALIDATION_FAILURE = "Business validation failed: ";
	String STATUS_ACTIVE = "ACTIVE";
	String STATUS_DEACTIVE = "DEACTIVE";
	String STATUS_DRAFT = "DRAFT";
	
	
}
