package com.synectiks.pref.web.rest;

import com.synectiks.pref.service.CollegeService;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.service.dto.CollegeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.synectiks.pref.domain.College}.
 */
@RestController
@RequestMapping("/api")
public class CollegeResource {

    private final Logger log = LoggerFactory.getLogger(CollegeResource.class);

    private static final String ENTITY_NAME = "college";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollegeService collegeService;

    public CollegeResource(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    /**
     * {@code POST  /colleges} : Create a new college.
     *
     * @param collegeDTO the collegeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collegeDTO, or with status {@code 400 (Bad Request)} if the college has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/colleges")
    public ResponseEntity<CollegeDTO> createCollege(@RequestBody CollegeDTO collegeDTO) throws URISyntaxException {
        log.debug("REST request to save College : {}", collegeDTO);
        if (collegeDTO.getId() != null) {
            throw new BadRequestAlertException("A new college cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollegeDTO result = collegeService.save(collegeDTO);
        return ResponseEntity.created(new URI("/api/colleges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /colleges} : Updates an existing college.
     *
     * @param collegeDTO the collegeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collegeDTO,
     * or with status {@code 400 (Bad Request)} if the collegeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collegeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/colleges")
    public ResponseEntity<CollegeDTO> updateCollege(@RequestBody CollegeDTO collegeDTO) throws URISyntaxException {
        log.debug("REST request to update College : {}", collegeDTO);
        if (collegeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CollegeDTO result = collegeService.save(collegeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collegeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /colleges} : get all the colleges.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of colleges in body.
     */
    @GetMapping("/colleges")
    public List<CollegeDTO> getAllColleges() {
        log.debug("REST request to get all Colleges");
        return collegeService.findAll();
    }

    /**
     * {@code GET  /colleges/:id} : get the "id" college.
     *
     * @param id the id of the collegeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collegeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/colleges/{id}")
    public ResponseEntity<CollegeDTO> getCollege(@PathVariable Long id) {
        log.debug("REST request to get College : {}", id);
        Optional<CollegeDTO> collegeDTO = collegeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collegeDTO);
    }

    /**
     * {@code DELETE  /colleges/:id} : delete the "id" college.
     *
     * @param id the id of the collegeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/colleges/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable Long id) {
        log.debug("REST request to delete College : {}", id);
        collegeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/colleges?query=:query} : search for the college corresponding
     * to the query.
     *
     * @param query the query of the college search.
     * @return the result of the search.
     */
    @GetMapping("/_search/colleges")
    public List<CollegeDTO> searchColleges(@RequestParam String query) {
        log.debug("REST request to search Colleges for query {}", query);
        return collegeService.search(query);
    }

}
