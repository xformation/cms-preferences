package com.synectiks.pref.web.rest;

import static com.synectiks.pref.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.domain.Employee;
import com.synectiks.pref.repository.EmployeeRepository;
import com.synectiks.pref.repository.search.EmployeeSearchRepository;
import com.synectiks.pref.service.EmployeeService;
import com.synectiks.pref.service.dto.EmployeeDTO;
import com.synectiks.pref.service.mapper.EmployeeMapper;
import com.synectiks.pref.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link EmployeeResource} REST controller.
 */
@SpringBootTest(classes = PreferencesApp.class)
public class EmployeeResourceIT {

    private static final String DEFAULT_EMPLOYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLACE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_RELIGION = "AAAAAAAAAA";
    private static final String UPDATED_RELIGION = "BBBBBBBBBB";

    private static final String DEFAULT_CASTE = "AAAAAAAAAA";
    private static final String UPDATED_CASTE = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CASTE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CASTE = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_PIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION_OFEMERGENCY_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_OFEMERGENCY_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STAFF_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STAFF_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_JOINING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOINING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_JOB_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOB_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESIGNATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESIGNATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESIGNATION_ACCEPTANCE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESIGNATION_ACCEPTANCE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AADHAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_MAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_MAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICIAL_MAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFICIAL_MAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVING_LICENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_DRIVING_LICENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_EMPLOYMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_MANAGER_ID = 1L;
    private static final Long UPDATED_MANAGER_ID = 2L;

    private static final String DEFAULT_MARITAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MARITAL_STATUS = "BBBBBBBBBB";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    /**
     * This repository is mocked in the com.synectiks.pref.repository.search test package.
     *
     * @see com.synectiks.pref.repository.search.EmployeeSearchRepositoryMockConfiguration
     */
    @Autowired
    private EmployeeSearchRepository mockEmployeeSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeService);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .employeeName(DEFAULT_EMPLOYEE_NAME)
            .employeeMiddleName(DEFAULT_EMPLOYEE_MIDDLE_NAME)
            .employeeLastName(DEFAULT_EMPLOYEE_LAST_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .fatherMiddleName(DEFAULT_FATHER_MIDDLE_NAME)
            .fatherLastName(DEFAULT_FATHER_LAST_NAME)
            .spouseName(DEFAULT_SPOUSE_NAME)
            .spouseMiddleName(DEFAULT_SPOUSE_MIDDLE_NAME)
            .spouseLastName(DEFAULT_SPOUSE_LAST_NAME)
            .motherName(DEFAULT_MOTHER_NAME)
            .motherMiddleName(DEFAULT_MOTHER_MIDDLE_NAME)
            .motherLastName(DEFAULT_MOTHER_LAST_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .placeOfBirth(DEFAULT_PLACE_OF_BIRTH)
            .religion(DEFAULT_RELIGION)
            .caste(DEFAULT_CASTE)
            .subCaste(DEFAULT_SUB_CASTE)
            .gender(DEFAULT_GENDER)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .pinCode(DEFAULT_PIN_CODE)
            .relationOfEmergencyContact(DEFAULT_RELATION_OFEMERGENCY_CONTACT)
            .emergencyContactName(DEFAULT_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactNo(DEFAULT_EMERGENCY_CONTACT_NO)
            .emergencyContactEmailAddress(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS)
            .status(DEFAULT_STATUS)
            .staffType(DEFAULT_STAFF_TYPE)
            .designation(DEFAULT_DESIGNATION)
            .joiningDate(DEFAULT_JOINING_DATE)
            .jobEndDate(DEFAULT_JOB_END_DATE)
            .resignationDate(DEFAULT_RESIGNATION_DATE)
            .resignationAcceptanceDate(DEFAULT_RESIGNATION_ACCEPTANCE_DATE)
            .aadharNo(DEFAULT_AADHAR_NO)
            .panNo(DEFAULT_PAN_NO)
            .passportNo(DEFAULT_PASSPORT_NO)
            .primaryContactNo(DEFAULT_PRIMARY_CONTACT_NO)
            .secondaryContactNo(DEFAULT_SECONDARY_CONTACT_NO)
            .primaryAddress(DEFAULT_PRIMARY_ADDRESS)
            .secondaryAddress(DEFAULT_SECONDARY_ADDRESS)
            .personalMailId(DEFAULT_PERSONAL_MAIL_ID)
            .officialMailId(DEFAULT_OFFICIAL_MAIL_ID)
            .drivingLicenceNo(DEFAULT_DRIVING_LICENCE_NO)
            .typeOfEmployment(DEFAULT_TYPE_OF_EMPLOYMENT)
            .managerId(DEFAULT_MANAGER_ID)
            .maritalStatus(DEFAULT_MARITAL_STATUS);
        return employee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createUpdatedEntity(EntityManager em) {
        Employee employee = new Employee()
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .employeeMiddleName(UPDATED_EMPLOYEE_MIDDLE_NAME)
            .employeeLastName(UPDATED_EMPLOYEE_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .spouseName(UPDATED_SPOUSE_NAME)
            .spouseMiddleName(UPDATED_SPOUSE_MIDDLE_NAME)
            .spouseLastName(UPDATED_SPOUSE_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .religion(UPDATED_RELIGION)
            .caste(UPDATED_CASTE)
            .subCaste(UPDATED_SUB_CASTE)
            .gender(UPDATED_GENDER)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .pinCode(UPDATED_PIN_CODE)
            .relationOfEmergencyContact(UPDATED_RELATION_OFEMERGENCY_CONTACT)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(UPDATED_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .emergencyContactEmailAddress(UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS)
            .status(UPDATED_STATUS)
            .staffType(UPDATED_STAFF_TYPE)
            .designation(UPDATED_DESIGNATION)
            .joiningDate(UPDATED_JOINING_DATE)
            .jobEndDate(UPDATED_JOB_END_DATE)
            .resignationDate(UPDATED_RESIGNATION_DATE)
            .resignationAcceptanceDate(UPDATED_RESIGNATION_ACCEPTANCE_DATE)
            .aadharNo(UPDATED_AADHAR_NO)
            .panNo(UPDATED_PAN_NO)
            .passportNo(UPDATED_PASSPORT_NO)
            .primaryContactNo(UPDATED_PRIMARY_CONTACT_NO)
            .secondaryContactNo(UPDATED_SECONDARY_CONTACT_NO)
            .primaryAddress(UPDATED_PRIMARY_ADDRESS)
            .secondaryAddress(UPDATED_SECONDARY_ADDRESS)
            .personalMailId(UPDATED_PERSONAL_MAIL_ID)
            .officialMailId(UPDATED_OFFICIAL_MAIL_ID)
            .drivingLicenceNo(UPDATED_DRIVING_LICENCE_NO)
            .typeOfEmployment(UPDATED_TYPE_OF_EMPLOYMENT)
            .managerId(UPDATED_MANAGER_ID)
            .maritalStatus(UPDATED_MARITAL_STATUS);
        return employee;
    }

    @BeforeEach
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeName()).isEqualTo(DEFAULT_EMPLOYEE_NAME);
        assertThat(testEmployee.getEmployeeMiddleName()).isEqualTo(DEFAULT_EMPLOYEE_MIDDLE_NAME);
        assertThat(testEmployee.getEmployeeLastName()).isEqualTo(DEFAULT_EMPLOYEE_LAST_NAME);
        assertThat(testEmployee.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testEmployee.getFatherMiddleName()).isEqualTo(DEFAULT_FATHER_MIDDLE_NAME);
        assertThat(testEmployee.getFatherLastName()).isEqualTo(DEFAULT_FATHER_LAST_NAME);
        assertThat(testEmployee.getSpouseName()).isEqualTo(DEFAULT_SPOUSE_NAME);
        assertThat(testEmployee.getSpouseMiddleName()).isEqualTo(DEFAULT_SPOUSE_MIDDLE_NAME);
        assertThat(testEmployee.getSpouseLastName()).isEqualTo(DEFAULT_SPOUSE_LAST_NAME);
        assertThat(testEmployee.getMotherName()).isEqualTo(DEFAULT_MOTHER_NAME);
        assertThat(testEmployee.getMotherMiddleName()).isEqualTo(DEFAULT_MOTHER_MIDDLE_NAME);
        assertThat(testEmployee.getMotherLastName()).isEqualTo(DEFAULT_MOTHER_LAST_NAME);
        assertThat(testEmployee.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testEmployee.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testEmployee.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testEmployee.getCaste()).isEqualTo(DEFAULT_CASTE);
        assertThat(testEmployee.getSubCaste()).isEqualTo(DEFAULT_SUB_CASTE);
        assertThat(testEmployee.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testEmployee.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testEmployee.getPinCode()).isEqualTo(DEFAULT_PIN_CODE);
        assertThat(testEmployee.getRelationOfEmergencyContact()).isEqualTo(DEFAULT_RELATION_OFEMERGENCY_CONTACT);
        assertThat(testEmployee.getEmergencyContactName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NAME);
        assertThat(testEmployee.getEmergencyContactMiddleName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testEmployee.getEmergencyContactLastName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testEmployee.getEmergencyContactNo()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NO);
        assertThat(testEmployee.getEmergencyContactEmailAddress()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS);
        assertThat(testEmployee.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEmployee.getStaffType()).isEqualTo(DEFAULT_STAFF_TYPE);
        assertThat(testEmployee.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testEmployee.getJoiningDate()).isEqualTo(DEFAULT_JOINING_DATE);
        assertThat(testEmployee.getJobEndDate()).isEqualTo(DEFAULT_JOB_END_DATE);
        assertThat(testEmployee.getResignationDate()).isEqualTo(DEFAULT_RESIGNATION_DATE);
        assertThat(testEmployee.getResignationAcceptanceDate()).isEqualTo(DEFAULT_RESIGNATION_ACCEPTANCE_DATE);
        assertThat(testEmployee.getAadharNo()).isEqualTo(DEFAULT_AADHAR_NO);
        assertThat(testEmployee.getPanNo()).isEqualTo(DEFAULT_PAN_NO);
        assertThat(testEmployee.getPassportNo()).isEqualTo(DEFAULT_PASSPORT_NO);
        assertThat(testEmployee.getPrimaryContactNo()).isEqualTo(DEFAULT_PRIMARY_CONTACT_NO);
        assertThat(testEmployee.getSecondaryContactNo()).isEqualTo(DEFAULT_SECONDARY_CONTACT_NO);
        assertThat(testEmployee.getPrimaryAddress()).isEqualTo(DEFAULT_PRIMARY_ADDRESS);
        assertThat(testEmployee.getSecondaryAddress()).isEqualTo(DEFAULT_SECONDARY_ADDRESS);
        assertThat(testEmployee.getPersonalMailId()).isEqualTo(DEFAULT_PERSONAL_MAIL_ID);
        assertThat(testEmployee.getOfficialMailId()).isEqualTo(DEFAULT_OFFICIAL_MAIL_ID);
        assertThat(testEmployee.getDrivingLicenceNo()).isEqualTo(DEFAULT_DRIVING_LICENCE_NO);
        assertThat(testEmployee.getTypeOfEmployment()).isEqualTo(DEFAULT_TYPE_OF_EMPLOYMENT);
        assertThat(testEmployee.getManagerId()).isEqualTo(DEFAULT_MANAGER_ID);
        assertThat(testEmployee.getMaritalStatus()).isEqualTo(DEFAULT_MARITAL_STATUS);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(1)).save(testEmployee);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(0)).save(employee);
    }


    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeMiddleName").value(hasItem(DEFAULT_EMPLOYEE_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeLastName").value(hasItem(DEFAULT_EMPLOYEE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME.toString())))
            .andExpect(jsonPath("$.[*].spouseMiddleName").value(hasItem(DEFAULT_SPOUSE_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].spouseLastName").value(hasItem(DEFAULT_SPOUSE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].caste").value(hasItem(DEFAULT_CASTE.toString())))
            .andExpect(jsonPath("$.[*].subCaste").value(hasItem(DEFAULT_SUB_CASTE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE.toString())))
            .andExpect(jsonPath("$.[*].relationOfEmergencyContact").value(hasItem(DEFAULT_RELATION_OFEMERGENCY_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactEmailAddress").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobEndDate").value(hasItem(DEFAULT_JOB_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationDate").value(hasItem(DEFAULT_RESIGNATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationAcceptanceDate").value(hasItem(DEFAULT_RESIGNATION_ACCEPTANCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].aadharNo").value(hasItem(DEFAULT_AADHAR_NO.toString())))
            .andExpect(jsonPath("$.[*].panNo").value(hasItem(DEFAULT_PAN_NO.toString())))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].primaryContactNo").value(hasItem(DEFAULT_PRIMARY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].secondaryContactNo").value(hasItem(DEFAULT_SECONDARY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].primaryAddress").value(hasItem(DEFAULT_PRIMARY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].secondaryAddress").value(hasItem(DEFAULT_SECONDARY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].personalMailId").value(hasItem(DEFAULT_PERSONAL_MAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].officialMailId").value(hasItem(DEFAULT_OFFICIAL_MAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].drivingLicenceNo").value(hasItem(DEFAULT_DRIVING_LICENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].typeOfEmployment").value(hasItem(DEFAULT_TYPE_OF_EMPLOYMENT.toString())))
            .andExpect(jsonPath("$.[*].managerId").value(hasItem(DEFAULT_MANAGER_ID.intValue())))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.employeeName").value(DEFAULT_EMPLOYEE_NAME.toString()))
            .andExpect(jsonPath("$.employeeMiddleName").value(DEFAULT_EMPLOYEE_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.employeeLastName").value(DEFAULT_EMPLOYEE_LAST_NAME.toString()))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.fatherMiddleName").value(DEFAULT_FATHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.fatherLastName").value(DEFAULT_FATHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.spouseName").value(DEFAULT_SPOUSE_NAME.toString()))
            .andExpect(jsonPath("$.spouseMiddleName").value(DEFAULT_SPOUSE_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.spouseLastName").value(DEFAULT_SPOUSE_LAST_NAME.toString()))
            .andExpect(jsonPath("$.motherName").value(DEFAULT_MOTHER_NAME.toString()))
            .andExpect(jsonPath("$.motherMiddleName").value(DEFAULT_MOTHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.motherLastName").value(DEFAULT_MOTHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.placeOfBirth").value(DEFAULT_PLACE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.caste").value(DEFAULT_CASTE.toString()))
            .andExpect(jsonPath("$.subCaste").value(DEFAULT_SUB_CASTE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.pinCode").value(DEFAULT_PIN_CODE.toString()))
            .andExpect(jsonPath("$.relationOfEmergencyContact").value(DEFAULT_RELATION_OFEMERGENCY_CONTACT.toString()))
            .andExpect(jsonPath("$.emergencyContactName").value(DEFAULT_EMERGENCY_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactMiddleName").value(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactLastName").value(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactNo").value(DEFAULT_EMERGENCY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.emergencyContactEmailAddress").value(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.staffType").value(DEFAULT_STAFF_TYPE.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.joiningDate").value(DEFAULT_JOINING_DATE.toString()))
            .andExpect(jsonPath("$.jobEndDate").value(DEFAULT_JOB_END_DATE.toString()))
            .andExpect(jsonPath("$.resignationDate").value(DEFAULT_RESIGNATION_DATE.toString()))
            .andExpect(jsonPath("$.resignationAcceptanceDate").value(DEFAULT_RESIGNATION_ACCEPTANCE_DATE.toString()))
            .andExpect(jsonPath("$.aadharNo").value(DEFAULT_AADHAR_NO.toString()))
            .andExpect(jsonPath("$.panNo").value(DEFAULT_PAN_NO.toString()))
            .andExpect(jsonPath("$.passportNo").value(DEFAULT_PASSPORT_NO.toString()))
            .andExpect(jsonPath("$.primaryContactNo").value(DEFAULT_PRIMARY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.secondaryContactNo").value(DEFAULT_SECONDARY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.primaryAddress").value(DEFAULT_PRIMARY_ADDRESS.toString()))
            .andExpect(jsonPath("$.secondaryAddress").value(DEFAULT_SECONDARY_ADDRESS.toString()))
            .andExpect(jsonPath("$.personalMailId").value(DEFAULT_PERSONAL_MAIL_ID.toString()))
            .andExpect(jsonPath("$.officialMailId").value(DEFAULT_OFFICIAL_MAIL_ID.toString()))
            .andExpect(jsonPath("$.drivingLicenceNo").value(DEFAULT_DRIVING_LICENCE_NO.toString()))
            .andExpect(jsonPath("$.typeOfEmployment").value(DEFAULT_TYPE_OF_EMPLOYMENT.toString()))
            .andExpect(jsonPath("$.managerId").value(DEFAULT_MANAGER_ID.intValue()))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .employeeMiddleName(UPDATED_EMPLOYEE_MIDDLE_NAME)
            .employeeLastName(UPDATED_EMPLOYEE_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .spouseName(UPDATED_SPOUSE_NAME)
            .spouseMiddleName(UPDATED_SPOUSE_MIDDLE_NAME)
            .spouseLastName(UPDATED_SPOUSE_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .religion(UPDATED_RELIGION)
            .caste(UPDATED_CASTE)
            .subCaste(UPDATED_SUB_CASTE)
            .gender(UPDATED_GENDER)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .pinCode(UPDATED_PIN_CODE)
            .relationOfEmergencyContact(UPDATED_RELATION_OFEMERGENCY_CONTACT)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(UPDATED_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .emergencyContactEmailAddress(UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS)
            .status(UPDATED_STATUS)
            .staffType(UPDATED_STAFF_TYPE)
            .designation(UPDATED_DESIGNATION)
            .joiningDate(UPDATED_JOINING_DATE)
            .jobEndDate(UPDATED_JOB_END_DATE)
            .resignationDate(UPDATED_RESIGNATION_DATE)
            .resignationAcceptanceDate(UPDATED_RESIGNATION_ACCEPTANCE_DATE)
            .aadharNo(UPDATED_AADHAR_NO)
            .panNo(UPDATED_PAN_NO)
            .passportNo(UPDATED_PASSPORT_NO)
            .primaryContactNo(UPDATED_PRIMARY_CONTACT_NO)
            .secondaryContactNo(UPDATED_SECONDARY_CONTACT_NO)
            .primaryAddress(UPDATED_PRIMARY_ADDRESS)
            .secondaryAddress(UPDATED_SECONDARY_ADDRESS)
            .personalMailId(UPDATED_PERSONAL_MAIL_ID)
            .officialMailId(UPDATED_OFFICIAL_MAIL_ID)
            .drivingLicenceNo(UPDATED_DRIVING_LICENCE_NO)
            .typeOfEmployment(UPDATED_TYPE_OF_EMPLOYMENT)
            .managerId(UPDATED_MANAGER_ID)
            .maritalStatus(UPDATED_MARITAL_STATUS);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testEmployee.getEmployeeMiddleName()).isEqualTo(UPDATED_EMPLOYEE_MIDDLE_NAME);
        assertThat(testEmployee.getEmployeeLastName()).isEqualTo(UPDATED_EMPLOYEE_LAST_NAME);
        assertThat(testEmployee.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testEmployee.getFatherMiddleName()).isEqualTo(UPDATED_FATHER_MIDDLE_NAME);
        assertThat(testEmployee.getFatherLastName()).isEqualTo(UPDATED_FATHER_LAST_NAME);
        assertThat(testEmployee.getSpouseName()).isEqualTo(UPDATED_SPOUSE_NAME);
        assertThat(testEmployee.getSpouseMiddleName()).isEqualTo(UPDATED_SPOUSE_MIDDLE_NAME);
        assertThat(testEmployee.getSpouseLastName()).isEqualTo(UPDATED_SPOUSE_LAST_NAME);
        assertThat(testEmployee.getMotherName()).isEqualTo(UPDATED_MOTHER_NAME);
        assertThat(testEmployee.getMotherMiddleName()).isEqualTo(UPDATED_MOTHER_MIDDLE_NAME);
        assertThat(testEmployee.getMotherLastName()).isEqualTo(UPDATED_MOTHER_LAST_NAME);
        assertThat(testEmployee.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testEmployee.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testEmployee.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testEmployee.getCaste()).isEqualTo(UPDATED_CASTE);
        assertThat(testEmployee.getSubCaste()).isEqualTo(UPDATED_SUB_CASTE);
        assertThat(testEmployee.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testEmployee.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testEmployee.getPinCode()).isEqualTo(UPDATED_PIN_CODE);
        assertThat(testEmployee.getRelationOfEmergencyContact()).isEqualTo(UPDATED_RELATION_OFEMERGENCY_CONTACT);
        assertThat(testEmployee.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testEmployee.getEmergencyContactMiddleName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testEmployee.getEmergencyContactLastName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testEmployee.getEmergencyContactNo()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NO);
        assertThat(testEmployee.getEmergencyContactEmailAddress()).isEqualTo(UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS);
        assertThat(testEmployee.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEmployee.getStaffType()).isEqualTo(UPDATED_STAFF_TYPE);
        assertThat(testEmployee.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testEmployee.getJoiningDate()).isEqualTo(UPDATED_JOINING_DATE);
        assertThat(testEmployee.getJobEndDate()).isEqualTo(UPDATED_JOB_END_DATE);
        assertThat(testEmployee.getResignationDate()).isEqualTo(UPDATED_RESIGNATION_DATE);
        assertThat(testEmployee.getResignationAcceptanceDate()).isEqualTo(UPDATED_RESIGNATION_ACCEPTANCE_DATE);
        assertThat(testEmployee.getAadharNo()).isEqualTo(UPDATED_AADHAR_NO);
        assertThat(testEmployee.getPanNo()).isEqualTo(UPDATED_PAN_NO);
        assertThat(testEmployee.getPassportNo()).isEqualTo(UPDATED_PASSPORT_NO);
        assertThat(testEmployee.getPrimaryContactNo()).isEqualTo(UPDATED_PRIMARY_CONTACT_NO);
        assertThat(testEmployee.getSecondaryContactNo()).isEqualTo(UPDATED_SECONDARY_CONTACT_NO);
        assertThat(testEmployee.getPrimaryAddress()).isEqualTo(UPDATED_PRIMARY_ADDRESS);
        assertThat(testEmployee.getSecondaryAddress()).isEqualTo(UPDATED_SECONDARY_ADDRESS);
        assertThat(testEmployee.getPersonalMailId()).isEqualTo(UPDATED_PERSONAL_MAIL_ID);
        assertThat(testEmployee.getOfficialMailId()).isEqualTo(UPDATED_OFFICIAL_MAIL_ID);
        assertThat(testEmployee.getDrivingLicenceNo()).isEqualTo(UPDATED_DRIVING_LICENCE_NO);
        assertThat(testEmployee.getTypeOfEmployment()).isEqualTo(UPDATED_TYPE_OF_EMPLOYMENT);
        assertThat(testEmployee.getManagerId()).isEqualTo(UPDATED_MANAGER_ID);
        assertThat(testEmployee.getMaritalStatus()).isEqualTo(UPDATED_MARITAL_STATUS);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(1)).save(testEmployee);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(0)).save(employee);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Delete the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(1)).deleteById(employee.getId());
    }

    @Test
    @Transactional
    public void searchEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);
//        when(mockEmployeeSearchRepository.search(queryStringQuery("id:" + employee.getId())))
//            .thenReturn(Collections.singletonList(employee));
        // Search the employee
        restEmployeeMockMvc.perform(get("/api/_search/employees?query=id:" + employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME)))
            .andExpect(jsonPath("$.[*].employeeMiddleName").value(hasItem(DEFAULT_EMPLOYEE_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].employeeLastName").value(hasItem(DEFAULT_EMPLOYEE_LAST_NAME)))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME)))
            .andExpect(jsonPath("$.[*].spouseMiddleName").value(hasItem(DEFAULT_SPOUSE_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].spouseLastName").value(hasItem(DEFAULT_SPOUSE_LAST_NAME)))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME)))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].caste").value(hasItem(DEFAULT_CASTE)))
            .andExpect(jsonPath("$.[*].subCaste").value(hasItem(DEFAULT_SUB_CASTE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE)))
            .andExpect(jsonPath("$.[*].relationOfEmergencyContact").value(hasItem(DEFAULT_RELATION_OFEMERGENCY_CONTACT)))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].emergencyContactEmailAddress").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobEndDate").value(hasItem(DEFAULT_JOB_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationDate").value(hasItem(DEFAULT_RESIGNATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationAcceptanceDate").value(hasItem(DEFAULT_RESIGNATION_ACCEPTANCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].aadharNo").value(hasItem(DEFAULT_AADHAR_NO)))
            .andExpect(jsonPath("$.[*].panNo").value(hasItem(DEFAULT_PAN_NO)))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO)))
            .andExpect(jsonPath("$.[*].primaryContactNo").value(hasItem(DEFAULT_PRIMARY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].secondaryContactNo").value(hasItem(DEFAULT_SECONDARY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].primaryAddress").value(hasItem(DEFAULT_PRIMARY_ADDRESS)))
            .andExpect(jsonPath("$.[*].secondaryAddress").value(hasItem(DEFAULT_SECONDARY_ADDRESS)))
            .andExpect(jsonPath("$.[*].personalMailId").value(hasItem(DEFAULT_PERSONAL_MAIL_ID)))
            .andExpect(jsonPath("$.[*].officialMailId").value(hasItem(DEFAULT_OFFICIAL_MAIL_ID)))
            .andExpect(jsonPath("$.[*].drivingLicenceNo").value(hasItem(DEFAULT_DRIVING_LICENCE_NO)))
            .andExpect(jsonPath("$.[*].typeOfEmployment").value(hasItem(DEFAULT_TYPE_OF_EMPLOYMENT)))
            .andExpect(jsonPath("$.[*].managerId").value(hasItem(DEFAULT_MANAGER_ID.intValue())))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setId(employeeDTO1.getId());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setId(2L);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setId(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeeMapper.fromId(null)).isNull();
    }
}
