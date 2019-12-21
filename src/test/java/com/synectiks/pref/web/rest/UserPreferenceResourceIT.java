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
import com.synectiks.pref.domain.UserPreference;
import com.synectiks.pref.repository.UserPreferenceRepository;
import com.synectiks.pref.repository.search.UserPreferenceSearchRepository;
import com.synectiks.pref.service.UserPreferenceService;
import com.synectiks.pref.service.dto.UserPreferenceDTO;
import com.synectiks.pref.service.mapper.UserPreferenceMapper;
import com.synectiks.pref.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link UserPreferenceResource} REST controller.
 */
@SpringBootTest(classes = PreferencesApp.class)
public class UserPreferenceResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_ACADEMIC_YEAR_ID = 1L;
    private static final Long UPDATED_ACADEMIC_YEAR_ID = 2L;

    private static final Long DEFAULT_COLLEGE_ID = 1L;
    private static final Long UPDATED_COLLEGE_ID = 2L;

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_SEMESTER_ID = 1L;
    private static final Long UPDATED_SEMESTER_ID = 2L;

    private static final Long DEFAULT_BATCH_ID = 1L;
    private static final Long UPDATED_BATCH_ID = 2L;

    private static final Long DEFAULT_SECTION_ID = 1L;
    private static final Long UPDATED_SECTION_ID = 2L;

    private static final Long DEFAULT_CLASS_ID = 1L;
    private static final Long UPDATED_CLASS_ID = 2L;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;

    @Autowired
    private UserPreferenceService userPreferenceService;

    /**
     * This repository is mocked in the com.synectiks.pref.repository.search test package.
     *
     * @see com.synectiks.pref.repository.search.UserPreferenceSearchRepositoryMockConfiguration
     */
    @Autowired
    private UserPreferenceSearchRepository mockUserPreferenceSearchRepository;

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

    private MockMvc restUserPreferenceMockMvc;

    private UserPreference userPreference;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserPreferenceResource userPreferenceResource = new UserPreferenceResource(userPreferenceService);
        this.restUserPreferenceMockMvc = MockMvcBuilders.standaloneSetup(userPreferenceResource)
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
    public static UserPreference createEntity(EntityManager em) {
        UserPreference userPreference = new UserPreference()
            .userId(DEFAULT_USER_ID)
            .academicYearId(DEFAULT_ACADEMIC_YEAR_ID)
            .collegeId(DEFAULT_COLLEGE_ID)
            .branchId(DEFAULT_BRANCH_ID)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .courseId(DEFAULT_COURSE_ID)
            .semesterId(DEFAULT_SEMESTER_ID)
            .batchId(DEFAULT_BATCH_ID)
            .sectionId(DEFAULT_SECTION_ID)
            .classId(DEFAULT_CLASS_ID);
        return userPreference;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPreference createUpdatedEntity(EntityManager em) {
        UserPreference userPreference = new UserPreference()
            .userId(UPDATED_USER_ID)
            .academicYearId(UPDATED_ACADEMIC_YEAR_ID)
            .collegeId(UPDATED_COLLEGE_ID)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .semesterId(UPDATED_SEMESTER_ID)
            .batchId(UPDATED_BATCH_ID)
            .sectionId(UPDATED_SECTION_ID)
            .classId(UPDATED_CLASS_ID);
        return userPreference;
    }

    @BeforeEach
    public void initTest() {
        userPreference = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserPreference() throws Exception {
        int databaseSizeBeforeCreate = userPreferenceRepository.findAll().size();

        // Create the UserPreference
        UserPreferenceDTO userPreferenceDTO = userPreferenceMapper.toDto(userPreference);
        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreferenceDTO)))
            .andExpect(status().isCreated());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeCreate + 1);
        UserPreference testUserPreference = userPreferenceList.get(userPreferenceList.size() - 1);
        assertThat(testUserPreference.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserPreference.getAcademicYearId()).isEqualTo(DEFAULT_ACADEMIC_YEAR_ID);
        assertThat(testUserPreference.getCollegeId()).isEqualTo(DEFAULT_COLLEGE_ID);
        assertThat(testUserPreference.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testUserPreference.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testUserPreference.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testUserPreference.getSemesterId()).isEqualTo(DEFAULT_SEMESTER_ID);
        assertThat(testUserPreference.getBatchId()).isEqualTo(DEFAULT_BATCH_ID);
        assertThat(testUserPreference.getSectionId()).isEqualTo(DEFAULT_SECTION_ID);
        assertThat(testUserPreference.getClassId()).isEqualTo(DEFAULT_CLASS_ID);

        // Validate the UserPreference in Elasticsearch
        verify(mockUserPreferenceSearchRepository, times(1)).save(testUserPreference);
    }

    @Test
    @Transactional
    public void createUserPreferenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userPreferenceRepository.findAll().size();

        // Create the UserPreference with an existing ID
        userPreference.setId(1L);
        UserPreferenceDTO userPreferenceDTO = userPreferenceMapper.toDto(userPreference);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreferenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeCreate);

        // Validate the UserPreference in Elasticsearch
        verify(mockUserPreferenceSearchRepository, times(0)).save(userPreference);
    }


    @Test
    @Transactional
    public void getAllUserPreferences() throws Exception {
        // Initialize the database
        userPreferenceRepository.saveAndFlush(userPreference);

        // Get all the userPreferenceList
        restUserPreferenceMockMvc.perform(get("/api/user-preferences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPreference.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].academicYearId").value(hasItem(DEFAULT_ACADEMIC_YEAR_ID.intValue())))
            .andExpect(jsonPath("$.[*].collegeId").value(hasItem(DEFAULT_COLLEGE_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].semesterId").value(hasItem(DEFAULT_SEMESTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].sectionId").value(hasItem(DEFAULT_SECTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].classId").value(hasItem(DEFAULT_CLASS_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getUserPreference() throws Exception {
        // Initialize the database
        userPreferenceRepository.saveAndFlush(userPreference);

        // Get the userPreference
        restUserPreferenceMockMvc.perform(get("/api/user-preferences/{id}", userPreference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userPreference.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.academicYearId").value(DEFAULT_ACADEMIC_YEAR_ID.intValue()))
            .andExpect(jsonPath("$.collegeId").value(DEFAULT_COLLEGE_ID.intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.semesterId").value(DEFAULT_SEMESTER_ID.intValue()))
            .andExpect(jsonPath("$.batchId").value(DEFAULT_BATCH_ID.intValue()))
            .andExpect(jsonPath("$.sectionId").value(DEFAULT_SECTION_ID.intValue()))
            .andExpect(jsonPath("$.classId").value(DEFAULT_CLASS_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserPreference() throws Exception {
        // Get the userPreference
        restUserPreferenceMockMvc.perform(get("/api/user-preferences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserPreference() throws Exception {
        // Initialize the database
        userPreferenceRepository.saveAndFlush(userPreference);

        int databaseSizeBeforeUpdate = userPreferenceRepository.findAll().size();

        // Update the userPreference
        UserPreference updatedUserPreference = userPreferenceRepository.findById(userPreference.getId()).get();
        // Disconnect from session so that the updates on updatedUserPreference are not directly saved in db
        em.detach(updatedUserPreference);
        updatedUserPreference
            .userId(UPDATED_USER_ID)
            .academicYearId(UPDATED_ACADEMIC_YEAR_ID)
            .collegeId(UPDATED_COLLEGE_ID)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .semesterId(UPDATED_SEMESTER_ID)
            .batchId(UPDATED_BATCH_ID)
            .sectionId(UPDATED_SECTION_ID)
            .classId(UPDATED_CLASS_ID);
        UserPreferenceDTO userPreferenceDTO = userPreferenceMapper.toDto(updatedUserPreference);

        restUserPreferenceMockMvc.perform(put("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreferenceDTO)))
            .andExpect(status().isOk());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeUpdate);
        UserPreference testUserPreference = userPreferenceList.get(userPreferenceList.size() - 1);
        assertThat(testUserPreference.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserPreference.getAcademicYearId()).isEqualTo(UPDATED_ACADEMIC_YEAR_ID);
        assertThat(testUserPreference.getCollegeId()).isEqualTo(UPDATED_COLLEGE_ID);
        assertThat(testUserPreference.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testUserPreference.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testUserPreference.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testUserPreference.getSemesterId()).isEqualTo(UPDATED_SEMESTER_ID);
        assertThat(testUserPreference.getBatchId()).isEqualTo(UPDATED_BATCH_ID);
        assertThat(testUserPreference.getSectionId()).isEqualTo(UPDATED_SECTION_ID);
        assertThat(testUserPreference.getClassId()).isEqualTo(UPDATED_CLASS_ID);

        // Validate the UserPreference in Elasticsearch
        verify(mockUserPreferenceSearchRepository, times(1)).save(testUserPreference);
    }

    @Test
    @Transactional
    public void updateNonExistingUserPreference() throws Exception {
        int databaseSizeBeforeUpdate = userPreferenceRepository.findAll().size();

        // Create the UserPreference
        UserPreferenceDTO userPreferenceDTO = userPreferenceMapper.toDto(userPreference);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPreferenceMockMvc.perform(put("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreferenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UserPreference in Elasticsearch
        verify(mockUserPreferenceSearchRepository, times(0)).save(userPreference);
    }

    @Test
    @Transactional
    public void deleteUserPreference() throws Exception {
        // Initialize the database
        userPreferenceRepository.saveAndFlush(userPreference);

        int databaseSizeBeforeDelete = userPreferenceRepository.findAll().size();

        // Delete the userPreference
        restUserPreferenceMockMvc.perform(delete("/api/user-preferences/{id}", userPreference.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UserPreference in Elasticsearch
        verify(mockUserPreferenceSearchRepository, times(1)).deleteById(userPreference.getId());
    }

//    @Test
//    @Transactional
//    public void searchUserPreference() throws Exception {
//        // Initialize the database
//        userPreferenceRepository.saveAndFlush(userPreference);
//        when(mockUserPreferenceSearchRepository.search(queryStringQuery("id:" + userPreference.getId())))
//            .thenReturn(Collections.singletonList(userPreference));
//        // Search the userPreference
//        restUserPreferenceMockMvc.perform(get("/api/_search/user-preferences?query=id:" + userPreference.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(userPreference.getId().intValue())))
//            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
//            .andExpect(jsonPath("$.[*].academicYearId").value(hasItem(DEFAULT_ACADEMIC_YEAR_ID.intValue())))
//            .andExpect(jsonPath("$.[*].collegeId").value(hasItem(DEFAULT_COLLEGE_ID.intValue())))
//            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
//            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
//            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
//            .andExpect(jsonPath("$.[*].semesterId").value(hasItem(DEFAULT_SEMESTER_ID.intValue())))
//            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
//            .andExpect(jsonPath("$.[*].sectionId").value(hasItem(DEFAULT_SECTION_ID.intValue())))
//            .andExpect(jsonPath("$.[*].classId").value(hasItem(DEFAULT_CLASS_ID.intValue())));
//    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPreference.class);
        UserPreference userPreference1 = new UserPreference();
        userPreference1.setId(1L);
        UserPreference userPreference2 = new UserPreference();
        userPreference2.setId(userPreference1.getId());
        assertThat(userPreference1).isEqualTo(userPreference2);
        userPreference2.setId(2L);
        assertThat(userPreference1).isNotEqualTo(userPreference2);
        userPreference1.setId(null);
        assertThat(userPreference1).isNotEqualTo(userPreference2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPreferenceDTO.class);
        UserPreferenceDTO userPreferenceDTO1 = new UserPreferenceDTO();
        userPreferenceDTO1.setId(1L);
        UserPreferenceDTO userPreferenceDTO2 = new UserPreferenceDTO();
        assertThat(userPreferenceDTO1).isNotEqualTo(userPreferenceDTO2);
        userPreferenceDTO2.setId(userPreferenceDTO1.getId());
        assertThat(userPreferenceDTO1).isEqualTo(userPreferenceDTO2);
        userPreferenceDTO2.setId(2L);
        assertThat(userPreferenceDTO1).isNotEqualTo(userPreferenceDTO2);
        userPreferenceDTO1.setId(null);
        assertThat(userPreferenceDTO1).isNotEqualTo(userPreferenceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userPreferenceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userPreferenceMapper.fromId(null)).isNull();
    }
}
