package com.synectiks.pref.constant;

import java.util.ArrayList;
import java.util.List;

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
}
