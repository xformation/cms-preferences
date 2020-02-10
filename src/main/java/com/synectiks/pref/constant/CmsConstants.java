package com.synectiks.pref.constant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.synectiks.pref.dataimport.CmsTableWithDomainAndRepositoryMapper;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.AttendanceMaster;
import com.synectiks.pref.domain.AuthorizedSignatory;
import com.synectiks.pref.domain.BankAccounts;
import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.College;
import com.synectiks.pref.domain.Country;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.domain.LegalEntity;
import com.synectiks.pref.domain.Section;
import com.synectiks.pref.domain.State;
import com.synectiks.pref.domain.Subject;
import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.domain.Term;
import com.synectiks.pref.domain.vo.Config;
import com.synectiks.pref.repository.AcademicYearRepository;
import com.synectiks.pref.repository.AttendanceMasterRepository;
import com.synectiks.pref.repository.AuthorizedSignatoryRepository;
import com.synectiks.pref.repository.BankAccountsRepository;
import com.synectiks.pref.repository.BatchRepository;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.repository.CityRepository;
import com.synectiks.pref.repository.CollegeRepository;
import com.synectiks.pref.repository.CountryRepository;
import com.synectiks.pref.repository.DepartmentRepository;
import com.synectiks.pref.repository.ExceptionRecordRepository;
import com.synectiks.pref.repository.HolidayRepository;
import com.synectiks.pref.repository.LegalEntityRepository;
import com.synectiks.pref.repository.SectionRepository;
import com.synectiks.pref.repository.StateRepository;
import com.synectiks.pref.repository.SubjectRepository;
import com.synectiks.pref.repository.TeachRepository;
import com.synectiks.pref.repository.TeacherRepository;
import com.synectiks.pref.repository.TermRepository;

public interface CmsConstants {
	
	String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    String SYSTEM_ACCOUNT = "system";
    String ANONYMOUS_USER = "anonymoususer";
    String DEFAULT_LANGUAGE = "en";
    
	String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
	String DATE_FORMAT_dd_MM_yyyy = "dd-MM-yyyy";
	String DATE_FORMAT_MM_dd_yyyy = "MM-dd-yyyy";
	String DATE_FORMAT_d_MMM_yyyy = "dd MMM yyyy";
	
	String ADD_SUCCESS_MESSAGE = "Records added successfully";
	String ADD_FAILURE_MESSAGE = "Due to some exception, records could no be added.";
	String UPDATE_SUCCESS_MESSAGE = "Records updated successfully";
	String UPDATE_FAILURE_MESSAGE = "Due to some exception, records could no be updated.";
	
	String INFLUXDB_LOG_LEVEL_BASIC = "BASIC";
	String INFLUXDB_LOG_LEVEL_FULL = "FULL";
	String INFLUXDB_LOG_LEVEL_HEADERS = "HEADERS";
	String INFLUXDB_LOG_LEVEL_NONE = "NONE";
	
	public static String STATUS_ACTIVE = "ACTIVE";
	public static String STATUS_DEACTIVE = "DEACTIVE";
	public static String STATUS_DRAFT = "DRAFT";
	public static List<String> STATUS_LIST = initStatusList();
    public static List<String> initStatusList(){
    	List<String> s = new ArrayList<>();
    	s.add(STATUS_ACTIVE);
    	s.add(STATUS_DEACTIVE);
    	s.add(STATUS_DRAFT);
    	return s;
    }
    
    public static String MALE = "MALE";
    public static String FEMALE = "FEMALE";
    public static String BOTH = "BOTH";
    public static List<String> GENDER_LIST = initGenderList();
    public static List<String> initGenderList(){
    	List<String> s = new ArrayList<>();
    	s.add(MALE);
    	s.add(FEMALE);
    	s.add(BOTH);
    	return s;
    }
    
    public static String STATUS_RECEIVED = "RECEIVED";
    public static String STATUS_FOLLOWUP = "FOLLOWUP";
    public static String STATUS_DECLINED = "DECLINED";
    public static String STATUS_CONVERTED_TO_ADMISSION = "CONVERTED_TO_ADMISSION";
	public static List<String> ENQUIRY_STATUS_LIST = initAdmissionEnquiryStatusList();
    public static List<String> initAdmissionEnquiryStatusList(){
    	List<String> s = new ArrayList<>();
    	s.add(STATUS_RECEIVED);
    	s.add(STATUS_FOLLOWUP);
    	s.add(STATUS_DECLINED);
    	s.add(STATUS_CONVERTED_TO_ADMISSION);
    	return s;
    }

    public static String STATUS_INPROCESS = "INPROCESS";
    public static String STATUS_PARKED = "PARKED";
    public static String STATUS_ADMISSION_GRANTED = "ADMISSION_GRANTED";
    public static List<String> ADMISSION_STATUS_LIST = initAdmissionStatusList();
    public static List<String> initAdmissionStatusList(){
    	List<String> s = new ArrayList<>();
    	s.add(STATUS_RECEIVED);
    	s.add(STATUS_INPROCESS);
    	s.add(STATUS_PARKED);
    	s.add(STATUS_DECLINED);
    	s.add(STATUS_ADMISSION_GRANTED);
    	return s;
    }

    public static String MODE_INPERSON = "INPERSON";
    public static String MODE_TELEPHONE = "TELEPHONE";
    public static String MODE_EMAIL = "EMAIL";
    public static String MODE_ONLINE = "ONLINE";
    public static String MODE_APPLICATION_LETTER = "APPLICATION_LETTER";
    public static List<String> MODE_OF_ENQUIRY_LIST = initModeOfEnquiryStatusList();
    public static List<String> initModeOfEnquiryStatusList(){
    	List<String> s = new ArrayList<>();
    	s.add(MODE_INPERSON);
    	s.add(MODE_TELEPHONE);
    	s.add(MODE_EMAIL);
    	s.add(MODE_ONLINE);
    	s.add(MODE_APPLICATION_LETTER);
    	return s;
    }
	
    String ERROR_ADMISSIONENQUIRY_ALREADY_EXISTS = "Admission Enquiry already exists. Application allows only one Enquiry.";
    String VALIDATION_FAILURE = "Business validation failed: ";
    String TRANSACTION_SOURCE_ADMISSION_PAGE = "ADMISSION_PAGE";
    String SOURCE_ADMISSION_ENQUIRY = "ADMISSION_ENQUIRY";
    String SOURCE_STUDENT = "STUDENT";
    
    String YES = "YES";
	String NO = "NO";
	
	String CMS_IMAGE_FILE_PATH = "college_images/";

	String CMS_COLLEGE_LOGO_FILE_NAME = "college_logo";
	String CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME = "dashboard";
	String CMS_LEGAL_ENTITY_LOGO_FILE_NAME = "legalentity_logo";

	String LECTURE_NOT_SCHEDULED = "LECTURE_NOT_SCHEDULED";

	String OS_WINDOWS = "windows";
	String OS_UNIX = "unix";
	String OS_MAC = "mac";
	String OS_SOLARIS = "solaris";
	String COLLEGE_ID_PLACEHOLDER_REPLACER = "college_id_";
	String BRANCH_ID_PLACEHOLDER_REPLACER = "branch_id_";
	String STUDENT_IMAGE_FILE_PATH = CMS_IMAGE_FILE_PATH+File.separator+"COLLEGE_ID"+File.separator+"student_images";

	ConcurrentHashMap<String, Config> USERS_CACHE = new ConcurrentHashMap<String, Config>();

	public static List<String> EXCLUDE_TABLE_LIST = initExcludeTableList();
    public static List<String> initExcludeTableList(){
    	List<String> s = new ArrayList<>();
    	s.add("exception_record");
    	s.add("batch");
    	s.add("section");
    	s.add("teach");
    	s.add("attendance_master");
    	return s;
    }
    
	List<String> TABLE_LIST = initTableList();
	public static List<String> initTableList() {
		List<String> ls = initTableDomainRepositoryMapperMap().keySet().stream().collect(Collectors.toList());
		ls.removeAll(EXCLUDE_TABLE_LIST);
		Collections.sort(ls);
	    return ls;
	}

	public static Map<String, CmsTableWithDomainAndRepositoryMapper> initTableDomainRepositoryMapperMap() {
		Map<String, CmsTableWithDomainAndRepositoryMapper> mpr = new HashMap<String, CmsTableWithDomainAndRepositoryMapper>();
		mpr.put("exception_record", new CmsTableWithDomainAndRepositoryMapper("exception_record", ExceptionRecord.class, ExceptionRecordRepository.class));
		mpr.put("country", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));
		mpr.put("state", new CmsTableWithDomainAndRepositoryMapper("state", State.class, StateRepository.class));
		mpr.put("city", new CmsTableWithDomainAndRepositoryMapper("city", City.class, CityRepository.class));
//		mpr.put("currency", new CmsTableWithDomainAndRepositoryMapper("currency", Currency.class, CurrencyRepository.class));
		mpr.put("college", new CmsTableWithDomainAndRepositoryMapper("college", College.class, CollegeRepository.class));
		mpr.put("branch", new CmsTableWithDomainAndRepositoryMapper("branch", Branch.class, BranchRepository.class));
		mpr.put("authorized_signatory", new CmsTableWithDomainAndRepositoryMapper("authorized_signatory", AuthorizedSignatory.class, AuthorizedSignatoryRepository.class));
	    mpr.put("bank_accounts", new CmsTableWithDomainAndRepositoryMapper("bank_accounts", BankAccounts.class, BankAccountsRepository.class));
	    mpr.put("legal_entity", new CmsTableWithDomainAndRepositoryMapper("legal_entity", LegalEntity.class, LegalEntityRepository.class));
	    mpr.put("academic_year", new CmsTableWithDomainAndRepositoryMapper("academic_year", AcademicYear.class, AcademicYearRepository.class));
	    mpr.put("holiday", new CmsTableWithDomainAndRepositoryMapper("holiday", Holiday.class, HolidayRepository.class));
	    mpr.put("term", new CmsTableWithDomainAndRepositoryMapper("term", Term.class, TermRepository.class));
	    mpr.put("department", new CmsTableWithDomainAndRepositoryMapper("department", Department.class, DepartmentRepository.class));
	    mpr.put("batch", new CmsTableWithDomainAndRepositoryMapper("batch", Batch.class, BatchRepository.class));
	    mpr.put("section", new CmsTableWithDomainAndRepositoryMapper("section", Section.class, SectionRepository.class));
	    mpr.put("teacher", new CmsTableWithDomainAndRepositoryMapper("teacher", Teacher.class, TeacherRepository.class));
	    mpr.put("subject", new CmsTableWithDomainAndRepositoryMapper("subject", Subject.class, SubjectRepository.class));
	    mpr.put("teach", new CmsTableWithDomainAndRepositoryMapper("teach", Teach.class, TeachRepository.class));
	    mpr.put("attendance_master", new CmsTableWithDomainAndRepositoryMapper("attendance_master", AttendanceMaster.class, AttendanceMasterRepository.class));
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
//	    mpr.put("meta_lecture", new CmsTableWithDomainAndRepositoryMapper("meta_lecture", MetaLecture.class, MetaLectureRepository.class));
//	    mpr.put("lecture", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));
	    
//	    mpr.put("student", new CmsTableWithDomainAndRepositoryMapper("student", Student.class, StudentRepository.class));
//	    mpr.put("student_attendance", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));
//	    mpr.put("student_exam_report", new CmsTableWithDomainAndRepositoryMapper("student_exam_report", StudentExamReport.class, StudentExamReportRepository.class));
//	    mpr.put("student_facility_link", new CmsTableWithDomainAndRepositoryMapper("country", Country.class, CountryRepository.class));
//	    mpr.put("transport_route", new CmsTableWithDomainAndRepositoryMapper("transport_route", TransportRoute.class, TransportRouteRepository.class));
//	    mpr.put("type_of_grading", new CmsTableWithDomainAndRepositoryMapper("type_of_grading", TypeOfGrading.class, TypeOfGradingRepository.class));
//	    mpr.put("employee", new CmsTableWithDomainAndRepositoryMapper("employee", Employee.class, EmployeeRepository.class));

	    return mpr;
	}


	String XLSX_FILE_EXTENSION = "xlsx";
	String XLS_FILE_EXTENSION = "xls";
	int BATCH_SIZE = 100;
	
	// BillDesk payment gateway specific constants
	String HASH_KEY = "uIZ2iayX70hc";
	String HASH_ALGO = "HmacSHA256";
    String MERCHANT_ID = "HMACUAT";
    String SECURITY_ID = "hmacuat";
    String RESPONSE_URL = "http://localhost:8080/api/cmspaymentresponse";
	String PAYMENT_STATUS_FAILED = "550";
	String PAYMENT_REDIRECT_URL = "http://localhost:3000/payment-response";

	String ERROR_COLLEGE_ALREADY_EXISTS = "College alreay exists. Application allows only one college.";
	String PRIVATE = "PRIVATE";
	String PUBLIC = "PUBLIC";
	String SEMI_GOVERNMENT = "SEMI-GOVERNMENT";

    public static String CUSTOMER = "CUSTOMER";
    public static String USER = "USER";
    public static String SUPER = "SUPER";
    public static List<String> TYPES_LIST = initTypeList();
    public static List<String> initTypeList(){
        List<String> s = new ArrayList<>();
        s.add(CUSTOMER);
        s.add(USER);
        s.add(SUPER);
        return s;
    }
    
    String SUBJECT_TYPE_COMMON = "COMMON";
    String SUBJECT_TYPE_ELECTIVE = "ELECTIVE";
    
    String STAFF_TYPE_TEACHING = "TEACHING";
    String STAFF_TYPE_NONTEACHING = "NONTEACHING";
    String STAFF_TYPE_GUEST = "GUEST";
    
    String RELIGION_HINDU = "HINDU";
    String RELIGION_MUSLIM = "MUSLIM";
    String RELIGION_SIKH = "SIKH";
    String RELIGION_CHRISTIAN = "CHRISTIAN";
    String RELIGION_BUDH = "BUDH";
    String RELIGION_PARSIAN = "PARSIAN";
    
    String CAST_GENERAL = "GENERAL";
    String CAST_SCHEDULED_CASTE = "SC";
    String CAST_SCHEDULED_TRIBE = "ST";
    String CAST_OTHER_BACKWARD_CLASSES="OBC";
    
    String DESIGNATION_LECTURER = "Lecturer";
    String DESIGNATION_PROFESSOR = "Professor";
    
}
