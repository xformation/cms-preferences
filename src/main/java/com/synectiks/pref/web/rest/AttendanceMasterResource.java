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

import com.synectiks.pref.service.AttendanceMasterService;
import com.synectiks.pref.service.dto.AttendanceMasterDTO;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.pref.domain.AttendanceMaster}.
 */
@RestController
@RequestMapping("/api")
public class AttendanceMasterResource {

    private final Logger log = LoggerFactory.getLogger(AttendanceMasterResource.class);

    private static final String ENTITY_NAME = "attendanceMaster";

    
    private String applicationName;

    private final AttendanceMasterService attendanceMasterService;

    public AttendanceMasterResource(AttendanceMasterService attendanceMasterService) {
        this.attendanceMasterService = attendanceMasterService;
    }

    /**
     * {@code POST  /attendance-masters} : Create a new attendanceMaster.
     *
     * @param attendanceMasterDTO the attendanceMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attendanceMasterDTO, or with status {@code 400 (Bad Request)} if the attendanceMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attendance-masters")
    public ResponseEntity<AttendanceMasterDTO> createAttendanceMaster(@RequestBody AttendanceMasterDTO attendanceMasterDTO) throws URISyntaxException {
        log.debug("REST request to save AttendanceMaster : {}", attendanceMasterDTO);
        if (attendanceMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new attendanceMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttendanceMasterDTO result = attendanceMasterService.save(attendanceMasterDTO);
        return ResponseEntity.created(new URI("/api/attendance-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attendance-masters} : Updates an existing attendanceMaster.
     *
     * @param attendanceMasterDTO the attendanceMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attendanceMasterDTO,
     * or with status {@code 400 (Bad Request)} if the attendanceMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attendanceMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attendance-masters")
    public ResponseEntity<AttendanceMasterDTO> updateAttendanceMaster(@RequestBody AttendanceMasterDTO attendanceMasterDTO) throws URISyntaxException {
        log.debug("REST request to update AttendanceMaster : {}", attendanceMasterDTO);
        if (attendanceMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttendanceMasterDTO result = attendanceMasterService.save(attendanceMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attendanceMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attendance-masters} : get all the attendanceMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attendanceMasters in body.
     */
    @GetMapping("/attendance-masters")
    public List<AttendanceMasterDTO> getAllAttendanceMasters() {
        log.debug("REST request to get all AttendanceMasters");
        return attendanceMasterService.findAll();
    }

    /**
     * {@code GET  /attendance-masters/:id} : get the "id" attendanceMaster.
     *
     * @param id the id of the attendanceMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attendanceMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attendance-masters/{id}")
    public ResponseEntity<AttendanceMasterDTO> getAttendanceMaster(@PathVariable Long id) {
        log.debug("REST request to get AttendanceMaster : {}", id);
        Optional<AttendanceMasterDTO> attendanceMasterDTO = attendanceMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attendanceMasterDTO);
    }

    /**
     * {@code DELETE  /attendance-masters/:id} : delete the "id" attendanceMaster.
     *
     * @param id the id of the attendanceMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attendance-masters/{id}")
    public ResponseEntity<Void> deleteAttendanceMaster(@PathVariable Long id) {
        log.debug("REST request to delete AttendanceMaster : {}", id);
        attendanceMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/attendance-masters?query=:query} : search for the attendanceMaster corresponding
     * to the query.
     *
     * @param query the query of the attendanceMaster search.
     * @return the result of the search.
     */
    @GetMapping("/_search/attendance-masters")
    public List<AttendanceMasterDTO> searchAttendanceMasters(@RequestParam String query) {
        log.debug("REST request to search AttendanceMasters for query {}", query);
        return attendanceMasterService.search(query);
    }

}
