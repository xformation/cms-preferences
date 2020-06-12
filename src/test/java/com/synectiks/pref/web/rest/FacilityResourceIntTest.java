package com.synectiks.pref.web.rest;

import com.synectiks.pref.PreferencesApp;

import com.synectiks.pref.domain.Facility;
import com.synectiks.pref.repository.FacilityRepository;
import com.synectiks.pref.repository.search.FacilitySearchRepository;
import com.synectiks.pref.service.FacilityService;
import com.synectiks.pref.service.dto.FacilityDTO;
import com.synectiks.pref.service.mapper.FacilityMapper;
import com.synectiks.pref.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;


import static com.synectiks.pref.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FacilityResource REST controller.
 *
 * @see FacilityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PreferencesApp.class)
public class FacilityResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SUSPAND_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUSPAND_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SUSPAND_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUSPAND_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private FacilityService facilityService;

    /**
     * This repository is mocked in the com.synectiks.pref.repository.search test package.
     *
     * @see com.synectiks.pref.repository.search.FacilitySearchRepositoryMockConfiguration
     */
    @Autowired
    private FacilitySearchRepository mockFacilitySearchRepository;

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

    private MockMvc restFacilityMockMvc;

    private Facility facility;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacilityResource facilityResource = new FacilityResource(facilityService);
        this.restFacilityMockMvc = MockMvcBuilders.standaloneSetup(facilityResource)
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
    public static Facility createEntity(EntityManager em) {
        Facility facility = new Facility()
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .suspandStartDate(DEFAULT_SUSPAND_START_DATE)
            .suspandEndDate(DEFAULT_SUSPAND_END_DATE)
            .amount(DEFAULT_AMOUNT);
        return facility;
    }

    @Before
    public void initTest() {
        facility = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacility() throws Exception {
        int databaseSizeBeforeCreate = facilityRepository.findAll().size();

        // Create the Facility
        FacilityDTO facilityDTO = facilityMapper.toDto(facility);
        restFacilityMockMvc.perform(post("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isCreated());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeCreate + 1);
        Facility testFacility = facilityList.get(facilityList.size() - 1);
        assertThat(testFacility.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFacility.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFacility.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testFacility.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testFacility.getSuspandStartDate()).isEqualTo(DEFAULT_SUSPAND_START_DATE);
        assertThat(testFacility.getSuspandEndDate()).isEqualTo(DEFAULT_SUSPAND_END_DATE);
        assertThat(testFacility.getAmount()).isEqualTo(DEFAULT_AMOUNT);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(1)).save(testFacility);
    }

    @Test
    @Transactional
    public void createFacilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facilityRepository.findAll().size();

        // Create the Facility with an existing ID
        facility.setId(1L);
        FacilityDTO facilityDTO = facilityMapper.toDto(facility);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacilityMockMvc.perform(post("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeCreate);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(0)).save(facility);
    }

    @Test
    @Transactional
    public void getAllFacilities() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        // Get all the facilityList
        restFacilityMockMvc.perform(get("/api/facilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facility.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].suspandStartDate").value(hasItem(DEFAULT_SUSPAND_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].suspandEndDate").value(hasItem(DEFAULT_SUSPAND_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void getFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        // Get the facility
        restFacilityMockMvc.perform(get("/api/facilities/{id}", facility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facility.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.suspandStartDate").value(DEFAULT_SUSPAND_START_DATE.toString()))
            .andExpect(jsonPath("$.suspandEndDate").value(DEFAULT_SUSPAND_END_DATE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFacility() throws Exception {
        // Get the facility
        restFacilityMockMvc.perform(get("/api/facilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        int databaseSizeBeforeUpdate = facilityRepository.findAll().size();

        // Update the facility
        Facility updatedFacility = facilityRepository.findById(facility.getId()).get();
        // Disconnect from session so that the updates on updatedFacility are not directly saved in db
        em.detach(updatedFacility);
        updatedFacility
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .suspandStartDate(UPDATED_SUSPAND_START_DATE)
            .suspandEndDate(UPDATED_SUSPAND_END_DATE)
            .amount(UPDATED_AMOUNT);
        FacilityDTO facilityDTO = facilityMapper.toDto(updatedFacility);

        restFacilityMockMvc.perform(put("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isOk());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);
        Facility testFacility = facilityList.get(facilityList.size() - 1);
        assertThat(testFacility.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFacility.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFacility.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testFacility.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testFacility.getSuspandStartDate()).isEqualTo(UPDATED_SUSPAND_START_DATE);
        assertThat(testFacility.getSuspandEndDate()).isEqualTo(UPDATED_SUSPAND_END_DATE);
        assertThat(testFacility.getAmount()).isEqualTo(UPDATED_AMOUNT);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(1)).save(testFacility);
    }

    @Test
    @Transactional
    public void updateNonExistingFacility() throws Exception {
        int databaseSizeBeforeUpdate = facilityRepository.findAll().size();

        // Create the Facility
        FacilityDTO facilityDTO = facilityMapper.toDto(facility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacilityMockMvc.perform(put("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(0)).save(facility);
    }

    @Test
    @Transactional
    public void deleteFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        int databaseSizeBeforeDelete = facilityRepository.findAll().size();

        // Delete the facility
        restFacilityMockMvc.perform(delete("/api/facilities/{id}", facility.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(1)).deleteById(facility.getId());
    }

    @Test
    @Transactional
    public void searchFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);
//        when(mockFacilitySearchRepository.search(queryStringQuery("id:" + facility.getId())))
//            .thenReturn(Collections.singletonList(facility));
        // Search the facility
        restFacilityMockMvc.perform(get("/api/_search/facilities?query=id:" + facility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facility.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].suspandStartDate").value(hasItem(DEFAULT_SUSPAND_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].suspandEndDate").value(hasItem(DEFAULT_SUSPAND_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Facility.class);
        Facility facility1 = new Facility();
        facility1.setId(1L);
        Facility facility2 = new Facility();
        facility2.setId(facility1.getId());
        assertThat(facility1).isEqualTo(facility2);
        facility2.setId(2L);
        assertThat(facility1).isNotEqualTo(facility2);
        facility1.setId(null);
        assertThat(facility1).isNotEqualTo(facility2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacilityDTO.class);
        FacilityDTO facilityDTO1 = new FacilityDTO();
        facilityDTO1.setId(1L);
        FacilityDTO facilityDTO2 = new FacilityDTO();
        assertThat(facilityDTO1).isNotEqualTo(facilityDTO2);
        facilityDTO2.setId(facilityDTO1.getId());
        assertThat(facilityDTO1).isEqualTo(facilityDTO2);
        facilityDTO2.setId(2L);
        assertThat(facilityDTO1).isNotEqualTo(facilityDTO2);
        facilityDTO1.setId(null);
        assertThat(facilityDTO1).isNotEqualTo(facilityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(facilityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(facilityMapper.fromId(null)).isNull();
    }
}
