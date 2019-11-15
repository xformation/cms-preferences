package com.synectiks.pref.web.rest;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.repository.BranchRepository;
import com.synectiks.pref.repository.search.BranchSearchRepository;
import com.synectiks.pref.service.BranchService;
import com.synectiks.pref.service.dto.BranchDTO;
import com.synectiks.pref.service.mapper.BranchMapper;
import com.synectiks.pref.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static com.synectiks.pref.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link BranchResource} REST controller.
 */
@SpringBootTest(classes = PreferencesApp.class)
public class BranchResourceIT {

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_HEAD = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_HEAD = "BBBBBBBBBB";

    private static final String DEFAULT_CELL_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CELL_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_LAND_LINE_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_LAND_LINE_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_NO = "AAAAAAAAAA";
    private static final String UPDATED_FAX_NO = "BBBBBBBBBB";

    private static final String DEFAULT_IS_MAIN_BRANCH = "AAA";
    private static final String UPDATED_IS_MAIN_BRANCH = "BBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

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
    private BranchRepository branchRepository;

    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private BranchService branchService;

    /**
     * This repository is mocked in the com.synectiks.pref.repository.search test package.
     *
     * @see com.synectiks.pref.repository.search.BranchSearchRepositoryMockConfiguration
     */
    @Autowired
    private BranchSearchRepository mockBranchSearchRepository;

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

    private MockMvc restBranchMockMvc;

    private Branch branch;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BranchResource branchResource = new BranchResource(branchService);
        this.restBranchMockMvc = MockMvcBuilders.standaloneSetup(branchResource)
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
    public static Branch createEntity(EntityManager em) {
        Branch branch = new Branch()
            .branchName(DEFAULT_BRANCH_NAME)
            .address(DEFAULT_ADDRESS)
            .pinCode(DEFAULT_PIN_CODE)
            .branchHead(DEFAULT_BRANCH_HEAD)
            .cellPhoneNo(DEFAULT_CELL_PHONE_NO)
            .landLinePhoneNo(DEFAULT_LAND_LINE_PHONE_NO)
            .emailId(DEFAULT_EMAIL_ID)
            .faxNo(DEFAULT_FAX_NO)
            .isMainBranch(DEFAULT_IS_MAIN_BRANCH)
            .startDate(DEFAULT_START_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON)
            .status(DEFAULT_STATUS);
        return branch;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branch createUpdatedEntity(EntityManager em) {
        Branch branch = new Branch()
            .branchName(UPDATED_BRANCH_NAME)
            .address(UPDATED_ADDRESS)
            .pinCode(UPDATED_PIN_CODE)
            .branchHead(UPDATED_BRANCH_HEAD)
            .cellPhoneNo(UPDATED_CELL_PHONE_NO)
            .landLinePhoneNo(UPDATED_LAND_LINE_PHONE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .faxNo(UPDATED_FAX_NO)
            .isMainBranch(UPDATED_IS_MAIN_BRANCH)
            .startDate(UPDATED_START_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .status(UPDATED_STATUS);
        return branch;
    }

    @BeforeEach
    public void initTest() {
        branch = createEntity(em);
    }

    @Test
    @Transactional
    public void createBranch() throws Exception {
        int databaseSizeBeforeCreate = branchRepository.findAll().size();

        // Create the Branch
        BranchDTO branchDTO = branchMapper.toDto(branch);
        restBranchMockMvc.perform(post("/api/branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(branchDTO)))
            .andExpect(status().isCreated());

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll();
        assertThat(branchList).hasSize(databaseSizeBeforeCreate + 1);
        Branch testBranch = branchList.get(branchList.size() - 1);
        assertThat(testBranch.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBranch.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBranch.getPinCode()).isEqualTo(DEFAULT_PIN_CODE);
        assertThat(testBranch.getBranchHead()).isEqualTo(DEFAULT_BRANCH_HEAD);
        assertThat(testBranch.getCellPhoneNo()).isEqualTo(DEFAULT_CELL_PHONE_NO);
        assertThat(testBranch.getLandLinePhoneNo()).isEqualTo(DEFAULT_LAND_LINE_PHONE_NO);
        assertThat(testBranch.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testBranch.getFaxNo()).isEqualTo(DEFAULT_FAX_NO);
        assertThat(testBranch.getIsMainBranch()).isEqualTo(DEFAULT_IS_MAIN_BRANCH);
        assertThat(testBranch.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBranch.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBranch.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBranch.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testBranch.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the Branch in Elasticsearch
        verify(mockBranchSearchRepository, times(1)).save(testBranch);
    }

    @Test
    @Transactional
    public void createBranchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = branchRepository.findAll().size();

        // Create the Branch with an existing ID
        branch.setId(1L);
        BranchDTO branchDTO = branchMapper.toDto(branch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchMockMvc.perform(post("/api/branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(branchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll();
        assertThat(branchList).hasSize(databaseSizeBeforeCreate);

        // Validate the Branch in Elasticsearch
        verify(mockBranchSearchRepository, times(0)).save(branch);
    }


    @Test
    @Transactional
    public void getAllBranches() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

        // Get all the branchList
        restBranchMockMvc.perform(get("/api/branches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branch.getId().intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE.toString())))
            .andExpect(jsonPath("$.[*].branchHead").value(hasItem(DEFAULT_BRANCH_HEAD.toString())))
            .andExpect(jsonPath("$.[*].cellPhoneNo").value(hasItem(DEFAULT_CELL_PHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].landLinePhoneNo").value(hasItem(DEFAULT_LAND_LINE_PHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].faxNo").value(hasItem(DEFAULT_FAX_NO.toString())))
            .andExpect(jsonPath("$.[*].isMainBranch").value(hasItem(DEFAULT_IS_MAIN_BRANCH.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

        // Get the branch
        restBranchMockMvc.perform(get("/api/branches/{id}", branch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(branch.getId().intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.pinCode").value(DEFAULT_PIN_CODE.toString()))
            .andExpect(jsonPath("$.branchHead").value(DEFAULT_BRANCH_HEAD.toString()))
            .andExpect(jsonPath("$.cellPhoneNo").value(DEFAULT_CELL_PHONE_NO.toString()))
            .andExpect(jsonPath("$.landLinePhoneNo").value(DEFAULT_LAND_LINE_PHONE_NO.toString()))
            .andExpect(jsonPath("$.emailId").value(DEFAULT_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.faxNo").value(DEFAULT_FAX_NO.toString()))
            .andExpect(jsonPath("$.isMainBranch").value(DEFAULT_IS_MAIN_BRANCH.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBranch() throws Exception {
        // Get the branch
        restBranchMockMvc.perform(get("/api/branches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

        int databaseSizeBeforeUpdate = branchRepository.findAll().size();

        // Update the branch
        Branch updatedBranch = branchRepository.findById(branch.getId()).get();
        // Disconnect from session so that the updates on updatedBranch are not directly saved in db
        em.detach(updatedBranch);
        updatedBranch
            .branchName(UPDATED_BRANCH_NAME)
            .address(UPDATED_ADDRESS)
            .pinCode(UPDATED_PIN_CODE)
            .branchHead(UPDATED_BRANCH_HEAD)
            .cellPhoneNo(UPDATED_CELL_PHONE_NO)
            .landLinePhoneNo(UPDATED_LAND_LINE_PHONE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .faxNo(UPDATED_FAX_NO)
            .isMainBranch(UPDATED_IS_MAIN_BRANCH)
            .startDate(UPDATED_START_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .status(UPDATED_STATUS);
        BranchDTO branchDTO = branchMapper.toDto(updatedBranch);

        restBranchMockMvc.perform(put("/api/branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(branchDTO)))
            .andExpect(status().isOk());

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        Branch testBranch = branchList.get(branchList.size() - 1);
        assertThat(testBranch.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranch.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBranch.getPinCode()).isEqualTo(UPDATED_PIN_CODE);
        assertThat(testBranch.getBranchHead()).isEqualTo(UPDATED_BRANCH_HEAD);
        assertThat(testBranch.getCellPhoneNo()).isEqualTo(UPDATED_CELL_PHONE_NO);
        assertThat(testBranch.getLandLinePhoneNo()).isEqualTo(UPDATED_LAND_LINE_PHONE_NO);
        assertThat(testBranch.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testBranch.getFaxNo()).isEqualTo(UPDATED_FAX_NO);
        assertThat(testBranch.getIsMainBranch()).isEqualTo(UPDATED_IS_MAIN_BRANCH);
        assertThat(testBranch.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBranch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBranch.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBranch.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testBranch.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the Branch in Elasticsearch
        verify(mockBranchSearchRepository, times(1)).save(testBranch);
    }

    @Test
    @Transactional
    public void updateNonExistingBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().size();

        // Create the Branch
        BranchDTO branchDTO = branchMapper.toDto(branch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchMockMvc.perform(put("/api/branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(branchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Branch in Elasticsearch
        verify(mockBranchSearchRepository, times(0)).save(branch);
    }

    @Test
    @Transactional
    public void deleteBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

        int databaseSizeBeforeDelete = branchRepository.findAll().size();

        // Delete the branch
        restBranchMockMvc.perform(delete("/api/branches/{id}", branch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Branch> branchList = branchRepository.findAll();
        assertThat(branchList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Branch in Elasticsearch
        verify(mockBranchSearchRepository, times(1)).deleteById(branch.getId());
    }

    @Test
    @Transactional
    public void searchBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);
        when(mockBranchSearchRepository.search(queryStringQuery("id:" + branch.getId())))
            .thenReturn(Collections.singletonList(branch));
        // Search the branch
        restBranchMockMvc.perform(get("/api/_search/branches?query=id:" + branch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branch.getId().intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE)))
            .andExpect(jsonPath("$.[*].branchHead").value(hasItem(DEFAULT_BRANCH_HEAD)))
            .andExpect(jsonPath("$.[*].cellPhoneNo").value(hasItem(DEFAULT_CELL_PHONE_NO)))
            .andExpect(jsonPath("$.[*].landLinePhoneNo").value(hasItem(DEFAULT_LAND_LINE_PHONE_NO)))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].faxNo").value(hasItem(DEFAULT_FAX_NO)))
            .andExpect(jsonPath("$.[*].isMainBranch").value(hasItem(DEFAULT_IS_MAIN_BRANCH)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Branch.class);
        Branch branch1 = new Branch();
        branch1.setId(1L);
        Branch branch2 = new Branch();
        branch2.setId(branch1.getId());
        assertThat(branch1).isEqualTo(branch2);
        branch2.setId(2L);
        assertThat(branch1).isNotEqualTo(branch2);
        branch1.setId(null);
        assertThat(branch1).isNotEqualTo(branch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchDTO.class);
        BranchDTO branchDTO1 = new BranchDTO();
        branchDTO1.setId(1L);
        BranchDTO branchDTO2 = new BranchDTO();
        assertThat(branchDTO1).isNotEqualTo(branchDTO2);
        branchDTO2.setId(branchDTO1.getId());
        assertThat(branchDTO1).isEqualTo(branchDTO2);
        branchDTO2.setId(2L);
        assertThat(branchDTO1).isNotEqualTo(branchDTO2);
        branchDTO1.setId(null);
        assertThat(branchDTO1).isNotEqualTo(branchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(branchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(branchMapper.fromId(null)).isNull();
    }
}
