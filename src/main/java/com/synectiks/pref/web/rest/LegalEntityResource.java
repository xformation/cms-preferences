package com.synectiks.pref.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.pref.service.LegalEntityService;
import com.synectiks.pref.service.dto.LegalEntityDTO;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.pref.domain.LegalEntity}.
 */
@RestController
@RequestMapping("/api")
public class LegalEntityResource {

    private final Logger log = LoggerFactory.getLogger(LegalEntityResource.class);

    private static final String ENTITY_NAME = "legalEntity";

    
    private String applicationName;

    private final LegalEntityService legalEntityService;

    public LegalEntityResource(LegalEntityService legalEntityService) {
        this.legalEntityService = legalEntityService;
    }

    /**
     * {@code POST  /legal-entities} : Create a new legalEntity.
     *
     * @param legalEntityDTO the legalEntityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new legalEntityDTO, or with status {@code 400 (Bad Request)} if the legalEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/legal-entities")
    public ResponseEntity<LegalEntityDTO> createLegalEntity(@RequestBody LegalEntityDTO legalEntityDTO) throws URISyntaxException {
        log.debug("REST request to save LegalEntity : {}", legalEntityDTO);
        if (legalEntityDTO.getId() != null) {
            throw new BadRequestAlertException("A new legalEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LegalEntityDTO result = legalEntityService.save(legalEntityDTO);
        return ResponseEntity.created(new URI("/api/legal-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /legal-entities} : Updates an existing legalEntity.
     *
     * @param legalEntityDTO the legalEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated legalEntityDTO,
     * or with status {@code 400 (Bad Request)} if the legalEntityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the legalEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/legal-entities")
    public ResponseEntity<LegalEntityDTO> updateLegalEntity(@RequestBody LegalEntityDTO legalEntityDTO) throws URISyntaxException {
        log.debug("REST request to update LegalEntity : {}", legalEntityDTO);
        if (legalEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LegalEntityDTO result = legalEntityService.save(legalEntityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, legalEntityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /legal-entities} : get all the legalEntities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of legalEntities in body.
     */
    @GetMapping("/legal-entities")
    public List<LegalEntityDTO> getAllLegalEntities() {
        log.debug("REST request to get all LegalEntities");
        return legalEntityService.findAll();
    }

    /**
     * {@code GET  /legal-entities/:id} : get the "id" legalEntity.
     *
     * @param id the id of the legalEntityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the legalEntityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/legal-entities/{id}")
    public ResponseEntity<LegalEntityDTO> getLegalEntity(@PathVariable Long id) {
        log.debug("REST request to get LegalEntity : {}", id);
        Optional<LegalEntityDTO> legalEntityDTO = legalEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(legalEntityDTO);
    }

    /**
     * {@code DELETE  /legal-entities/:id} : delete the "id" legalEntity.
     *
     * @param id the id of the legalEntityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/legal-entities/{id}")
    public ResponseEntity<Void> deleteLegalEntity(@PathVariable Long id) {
        log.debug("REST request to delete LegalEntity : {}", id);
        legalEntityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/legal-entities?query=:query} : search for the legalEntity corresponding
     * to the query.
     *
     * @param query the query of the legalEntity search.
     * @return the result of the search.
     */
    @GetMapping("/_search/legal-entities")
    public List<LegalEntityDTO> searchLegalEntities(@RequestParam String query) {
        log.debug("REST request to search LegalEntities for query {}", query);
        return legalEntityService.search(query);
    }

}
