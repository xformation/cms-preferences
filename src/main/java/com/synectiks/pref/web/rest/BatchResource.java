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

import com.synectiks.pref.service.BatchService;
import com.synectiks.pref.service.dto.BatchDTO;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.pref.domain.Batch}.
 */
@RestController
@RequestMapping("/api")
public class BatchResource {

    private final Logger log = LoggerFactory.getLogger(BatchResource.class);

    private static final String ENTITY_NAME = "batch";

    
    private String applicationName;

    private final BatchService batchService;

    public BatchResource(BatchService batchService) {
        this.batchService = batchService;
    }

    /**
     * {@code POST  /batches} : Create a new batch.
     *
     * @param batchDTO the batchDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new batchDTO, or with status {@code 400 (Bad Request)} if the batch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batches")
    public ResponseEntity<BatchDTO> createBatch(@RequestBody BatchDTO batchDTO) throws URISyntaxException {
        log.debug("REST request to save Batch : {}", batchDTO);
        if (batchDTO.getId() != null) {
            throw new BadRequestAlertException("A new batch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BatchDTO result = batchService.save(batchDTO);
        return ResponseEntity.created(new URI("/api/batches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /batches} : Updates an existing batch.
     *
     * @param batchDTO the batchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batchDTO,
     * or with status {@code 400 (Bad Request)} if the batchDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the batchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/batches")
    public ResponseEntity<BatchDTO> updateBatch(@RequestBody BatchDTO batchDTO) throws URISyntaxException {
        log.debug("REST request to update Batch : {}", batchDTO);
        if (batchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BatchDTO result = batchService.save(batchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batchDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /batches} : get all the batches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batches in body.
     */
    @GetMapping("/batches")
    public List<BatchDTO> getAllBatches() {
        log.debug("REST request to get all Batches");
        return batchService.findAll();
    }

    /**
     * {@code GET  /batches/:id} : get the "id" batch.
     *
     * @param id the id of the batchDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batchDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batches/{id}")
    public ResponseEntity<BatchDTO> getBatch(@PathVariable Long id) {
        log.debug("REST request to get Batch : {}", id);
        Optional<BatchDTO> batchDTO = batchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batchDTO);
    }

    /**
     * {@code DELETE  /batches/:id} : delete the "id" batch.
     *
     * @param id the id of the batchDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batches/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        log.debug("REST request to delete Batch : {}", id);
        batchService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/batches?query=:query} : search for the batch corresponding
     * to the query.
     *
     * @param query the query of the batch search.
     * @return the result of the search.
     */
    @GetMapping("/_search/batches")
    public List<BatchDTO> searchBatches(@RequestParam String query) {
        log.debug("REST request to search Batches for query {}", query);
        return batchService.search(query);
    }

}
