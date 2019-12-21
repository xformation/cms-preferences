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
import com.synectiks.pref.domain.College;
import com.synectiks.pref.repository.CollegeRepository;
import com.synectiks.pref.repository.search.CollegeSearchRepository;
import com.synectiks.pref.service.CollegeService;
import com.synectiks.pref.service.dto.CollegeDTO;
import com.synectiks.pref.service.mapper.CollegeMapper;
import com.synectiks.pref.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link CollegeResource} REST controller.
 */
@SpringBootTest(classes = PreferencesApp.class)
public class CollegeResourceIT {

    private static final String DEFAULT_COLLEGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLLEGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_FILE_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_FILE_EXTENSION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CollegeService collegeService;

    /**
     * This repository is mocked in the com.synectiks.pref.repository.search test package.
     *
     * @see com.synectiks.pref.repository.search.CollegeSearchRepositoryMockConfiguration
     */
    @Autowired
    private CollegeSearchRepository mockCollegeSearchRepository;

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

    private MockMvc restCollegeMockMvc;

    private College college;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CollegeResource collegeResource = new CollegeResource(collegeService);
        this.restCollegeMockMvc = MockMvcBuilders.standaloneSetup(collegeResource)
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
    public static College createEntity(EntityManager em) {
        College college = new College()
            .collegeName(DEFAULT_COLLEGE_NAME)
            .logoFilePath(DEFAULT_LOGO_FILE_PATH)
            .logoFileName(DEFAULT_LOGO_FILE_NAME)
            .logoFileExtension(DEFAULT_LOGO_FILE_EXTENSION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON)
            .status(DEFAULT_STATUS);
        return college;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static College createUpdatedEntity(EntityManager em) {
        College college = new College()
            .collegeName(UPDATED_COLLEGE_NAME)
            .logoFilePath(UPDATED_LOGO_FILE_PATH)
            .logoFileName(UPDATED_LOGO_FILE_NAME)
            .logoFileExtension(UPDATED_LOGO_FILE_EXTENSION)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .status(UPDATED_STATUS);
        return college;
    }

    @BeforeEach
    public void initTest() {
        college = createEntity(em);
    }

    @Test
    @Transactional
    public void createCollege() throws Exception {
        int databaseSizeBeforeCreate = collegeRepository.findAll().size();

        // Create the College
        CollegeDTO collegeDTO = collegeMapper.toDto(college);
        restCollegeMockMvc.perform(post("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isCreated());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeCreate + 1);
        College testCollege = collegeList.get(collegeList.size() - 1);
        assertThat(testCollege.getCollegeName()).isEqualTo(DEFAULT_COLLEGE_NAME);
        assertThat(testCollege.getLogoFilePath()).isEqualTo(DEFAULT_LOGO_FILE_PATH);
        assertThat(testCollege.getLogoFileName()).isEqualTo(DEFAULT_LOGO_FILE_NAME);
        assertThat(testCollege.getLogoFileExtension()).isEqualTo(DEFAULT_LOGO_FILE_EXTENSION);
        assertThat(testCollege.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCollege.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testCollege.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testCollege.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testCollege.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the College in Elasticsearch
        verify(mockCollegeSearchRepository, times(1)).save(testCollege);
    }

    @Test
    @Transactional
    public void createCollegeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = collegeRepository.findAll().size();

        // Create the College with an existing ID
        college.setId(1L);
        CollegeDTO collegeDTO = collegeMapper.toDto(college);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollegeMockMvc.perform(post("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeCreate);

        // Validate the College in Elasticsearch
        verify(mockCollegeSearchRepository, times(0)).save(college);
    }


    @Test
    @Transactional
    public void getAllColleges() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        // Get all the collegeList
        restCollegeMockMvc.perform(get("/api/colleges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(college.getId().intValue())))
            .andExpect(jsonPath("$.[*].collegeName").value(hasItem(DEFAULT_COLLEGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].logoFilePath").value(hasItem(DEFAULT_LOGO_FILE_PATH.toString())))
            .andExpect(jsonPath("$.[*].logoFileName").value(hasItem(DEFAULT_LOGO_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].logoFileExtension").value(hasItem(DEFAULT_LOGO_FILE_EXTENSION.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        // Get the college
        restCollegeMockMvc.perform(get("/api/colleges/{id}", college.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(college.getId().intValue()))
            .andExpect(jsonPath("$.collegeName").value(DEFAULT_COLLEGE_NAME.toString()))
            .andExpect(jsonPath("$.logoFilePath").value(DEFAULT_LOGO_FILE_PATH.toString()))
            .andExpect(jsonPath("$.logoFileName").value(DEFAULT_LOGO_FILE_NAME.toString()))
            .andExpect(jsonPath("$.logoFileExtension").value(DEFAULT_LOGO_FILE_EXTENSION.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCollege() throws Exception {
        // Get the college
        restCollegeMockMvc.perform(get("/api/colleges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        int databaseSizeBeforeUpdate = collegeRepository.findAll().size();

        // Update the college
        College updatedCollege = collegeRepository.findById(college.getId()).get();
        // Disconnect from session so that the updates on updatedCollege are not directly saved in db
        em.detach(updatedCollege);
        updatedCollege
            .collegeName(UPDATED_COLLEGE_NAME)
            .logoFilePath(UPDATED_LOGO_FILE_PATH)
            .logoFileName(UPDATED_LOGO_FILE_NAME)
            .logoFileExtension(UPDATED_LOGO_FILE_EXTENSION)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .status(UPDATED_STATUS);
        CollegeDTO collegeDTO = collegeMapper.toDto(updatedCollege);

        restCollegeMockMvc.perform(put("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isOk());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeUpdate);
        College testCollege = collegeList.get(collegeList.size() - 1);
        assertThat(testCollege.getCollegeName()).isEqualTo(UPDATED_COLLEGE_NAME);
        assertThat(testCollege.getLogoFilePath()).isEqualTo(UPDATED_LOGO_FILE_PATH);
        assertThat(testCollege.getLogoFileName()).isEqualTo(UPDATED_LOGO_FILE_NAME);
        assertThat(testCollege.getLogoFileExtension()).isEqualTo(UPDATED_LOGO_FILE_EXTENSION);
        assertThat(testCollege.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCollege.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testCollege.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testCollege.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testCollege.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the College in Elasticsearch
        verify(mockCollegeSearchRepository, times(1)).save(testCollege);
    }

    @Test
    @Transactional
    public void updateNonExistingCollege() throws Exception {
        int databaseSizeBeforeUpdate = collegeRepository.findAll().size();

        // Create the College
        CollegeDTO collegeDTO = collegeMapper.toDto(college);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollegeMockMvc.perform(put("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the College in Elasticsearch
        verify(mockCollegeSearchRepository, times(0)).save(college);
    }

    @Test
    @Transactional
    public void deleteCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        int databaseSizeBeforeDelete = collegeRepository.findAll().size();

        // Delete the college
        restCollegeMockMvc.perform(delete("/api/colleges/{id}", college.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the College in Elasticsearch
        verify(mockCollegeSearchRepository, times(1)).deleteById(college.getId());
    }

//    @Test
//    @Transactional
//    public void searchCollege() throws Exception {
//        // Initialize the database
//        collegeRepository.saveAndFlush(college);
//        when(mockCollegeSearchRepository.search(queryStringQuery("id:" + college.getId())))
//            .thenReturn(Collections.singletonList(college));
//        // Search the college
//        restCollegeMockMvc.perform(get("/api/_search/colleges?query=id:" + college.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(college.getId().intValue())))
//            .andExpect(jsonPath("$.[*].collegeName").value(hasItem(DEFAULT_COLLEGE_NAME)))
//            .andExpect(jsonPath("$.[*].logoFilePath").value(hasItem(DEFAULT_LOGO_FILE_PATH)))
//            .andExpect(jsonPath("$.[*].logoFileName").value(hasItem(DEFAULT_LOGO_FILE_NAME)))
//            .andExpect(jsonPath("$.[*].logoFileExtension").value(hasItem(DEFAULT_LOGO_FILE_EXTENSION)))
//            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
//            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
//            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
//            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
//            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
//    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(College.class);
        College college1 = new College();
        college1.setId(1L);
        College college2 = new College();
        college2.setId(college1.getId());
        assertThat(college1).isEqualTo(college2);
        college2.setId(2L);
        assertThat(college1).isNotEqualTo(college2);
        college1.setId(null);
        assertThat(college1).isNotEqualTo(college2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollegeDTO.class);
        CollegeDTO collegeDTO1 = new CollegeDTO();
        collegeDTO1.setId(1L);
        CollegeDTO collegeDTO2 = new CollegeDTO();
        assertThat(collegeDTO1).isNotEqualTo(collegeDTO2);
        collegeDTO2.setId(collegeDTO1.getId());
        assertThat(collegeDTO1).isEqualTo(collegeDTO2);
        collegeDTO2.setId(2L);
        assertThat(collegeDTO1).isNotEqualTo(collegeDTO2);
        collegeDTO1.setId(null);
        assertThat(collegeDTO1).isNotEqualTo(collegeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(collegeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(collegeMapper.fromId(null)).isNull();
    }
}
