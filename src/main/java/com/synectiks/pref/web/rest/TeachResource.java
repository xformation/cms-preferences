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

import com.synectiks.pref.service.TeachService;
import com.synectiks.pref.service.dto.TeachDTO;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.pref.domain.Teach}.
 */
@RestController
@RequestMapping("/api")
public class TeachResource {

    private final Logger log = LoggerFactory.getLogger(TeachResource.class);

    private static final String ENTITY_NAME = "teach";

    
    private String applicationName;

    private final TeachService teachService;

    public TeachResource(TeachService teachService) {
        this.teachService = teachService;
    }

    /**
     * {@code POST  /teaches} : Create a new teach.
     *
     * @param teachDTO the teachDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teachDTO, or with status {@code 400 (Bad Request)} if the teach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/teaches")
    public ResponseEntity<TeachDTO> createTeach(@RequestBody TeachDTO teachDTO) throws URISyntaxException {
        log.debug("REST request to save Teach : {}", teachDTO);
        if (teachDTO.getId() != null) {
            throw new BadRequestAlertException("A new teach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeachDTO result = teachService.save(teachDTO);
        return ResponseEntity.created(new URI("/api/teaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /teaches} : Updates an existing teach.
     *
     * @param teachDTO the teachDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teachDTO,
     * or with status {@code 400 (Bad Request)} if the teachDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teachDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/teaches")
    public ResponseEntity<TeachDTO> updateTeach(@RequestBody TeachDTO teachDTO) throws URISyntaxException {
        log.debug("REST request to update Teach : {}", teachDTO);
        if (teachDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeachDTO result = teachService.save(teachDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, teachDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /teaches} : get all the teaches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teaches in body.
     */
    @GetMapping("/teaches")
    public List<TeachDTO> getAllTeaches() {
        log.debug("REST request to get all Teaches");
        return teachService.findAll();
    }

    /**
     * {@code GET  /teaches/:id} : get the "id" teach.
     *
     * @param id the id of the teachDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teachDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/teaches/{id}")
    public ResponseEntity<TeachDTO> getTeach(@PathVariable Long id) {
        log.debug("REST request to get Teach : {}", id);
        Optional<TeachDTO> teachDTO = teachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teachDTO);
    }

    /**
     * {@code DELETE  /teaches/:id} : delete the "id" teach.
     *
     * @param id the id of the teachDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/teaches/{id}")
    public ResponseEntity<Void> deleteTeach(@PathVariable Long id) {
        log.debug("REST request to delete Teach : {}", id);
        teachService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/teaches?query=:query} : search for the teach corresponding
     * to the query.
     *
     * @param query the query of the teach search.
     * @return the result of the search.
     */
    @GetMapping("/_search/teaches")
    public List<TeachDTO> searchTeaches(@RequestParam String query) {
        log.debug("REST request to search Teaches for query {}", query);
        return teachService.search(query);
    }

}
