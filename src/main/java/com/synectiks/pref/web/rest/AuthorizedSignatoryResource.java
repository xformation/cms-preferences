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

import com.synectiks.pref.service.AuthorizedSignatoryService;
import com.synectiks.pref.service.dto.AuthorizedSignatoryDTO;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.pref.domain.AuthorizedSignatory}.
 */
@RestController
@RequestMapping("/api")
public class AuthorizedSignatoryResource {

    private final Logger log = LoggerFactory.getLogger(AuthorizedSignatoryResource.class);

    private static final String ENTITY_NAME = "authorizedSignatory";

    
    private String applicationName;

    private final AuthorizedSignatoryService authorizedSignatoryService;

    public AuthorizedSignatoryResource(AuthorizedSignatoryService authorizedSignatoryService) {
        this.authorizedSignatoryService = authorizedSignatoryService;
    }

    /**
     * {@code POST  /authorized-signatories} : Create a new authorizedSignatory.
     *
     * @param authorizedSignatoryDTO the authorizedSignatoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new authorizedSignatoryDTO, or with status {@code 400 (Bad Request)} if the authorizedSignatory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/authorized-signatories")
    public ResponseEntity<AuthorizedSignatoryDTO> createAuthorizedSignatory(@RequestBody AuthorizedSignatoryDTO authorizedSignatoryDTO) throws URISyntaxException {
        log.debug("REST request to save AuthorizedSignatory : {}", authorizedSignatoryDTO);
        if (authorizedSignatoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new authorizedSignatory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorizedSignatoryDTO result = authorizedSignatoryService.save(authorizedSignatoryDTO);
        return ResponseEntity.created(new URI("/api/authorized-signatories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /authorized-signatories} : Updates an existing authorizedSignatory.
     *
     * @param authorizedSignatoryDTO the authorizedSignatoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated authorizedSignatoryDTO,
     * or with status {@code 400 (Bad Request)} if the authorizedSignatoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the authorizedSignatoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/authorized-signatories")
    public ResponseEntity<AuthorizedSignatoryDTO> updateAuthorizedSignatory(@RequestBody AuthorizedSignatoryDTO authorizedSignatoryDTO) throws URISyntaxException {
        log.debug("REST request to update AuthorizedSignatory : {}", authorizedSignatoryDTO);
        if (authorizedSignatoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthorizedSignatoryDTO result = authorizedSignatoryService.save(authorizedSignatoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, authorizedSignatoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /authorized-signatories} : get all the authorizedSignatories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorizedSignatories in body.
     */
    @GetMapping("/authorized-signatories")
    public List<AuthorizedSignatoryDTO> getAllAuthorizedSignatories() {
        log.debug("REST request to get all AuthorizedSignatories");
        return authorizedSignatoryService.findAll();
    }

    /**
     * {@code GET  /authorized-signatories/:id} : get the "id" authorizedSignatory.
     *
     * @param id the id of the authorizedSignatoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the authorizedSignatoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/authorized-signatories/{id}")
    public ResponseEntity<AuthorizedSignatoryDTO> getAuthorizedSignatory(@PathVariable Long id) {
        log.debug("REST request to get AuthorizedSignatory : {}", id);
        Optional<AuthorizedSignatoryDTO> authorizedSignatoryDTO = authorizedSignatoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorizedSignatoryDTO);
    }

    /**
     * {@code DELETE  /authorized-signatories/:id} : delete the "id" authorizedSignatory.
     *
     * @param id the id of the authorizedSignatoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/authorized-signatories/{id}")
    public ResponseEntity<Void> deleteAuthorizedSignatory(@PathVariable Long id) {
        log.debug("REST request to delete AuthorizedSignatory : {}", id);
        authorizedSignatoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/authorized-signatories?query=:query} : search for the authorizedSignatory corresponding
     * to the query.
     *
     * @param query the query of the authorizedSignatory search.
     * @return the result of the search.
     */
    @GetMapping("/_search/authorized-signatories")
    public List<AuthorizedSignatoryDTO> searchAuthorizedSignatories(@RequestParam String query) {
        log.debug("REST request to search AuthorizedSignatories for query {}", query);
        return authorizedSignatoryService.search(query);
    }

}
