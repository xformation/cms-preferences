package com.synectiks.pref.web.rest;

import com.synectiks.pref.service.UserPreferenceService;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.service.dto.UserPreferenceDTO;

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
 * REST controller for managing {@link com.synectiks.pref.domain.UserPreference}.
 */
@RestController
@RequestMapping("/api")
public class UserPreferenceResource {

    private final Logger log = LoggerFactory.getLogger(UserPreferenceResource.class);

    private static final String ENTITY_NAME = "userPreference";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserPreferenceService userPreferenceService;

    public UserPreferenceResource(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    /**
     * {@code POST  /user-preferences} : Create a new userPreference.
     *
     * @param userPreferenceDTO the userPreferenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userPreferenceDTO, or with status {@code 400 (Bad Request)} if the userPreference has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-preferences")
    public ResponseEntity<UserPreferenceDTO> createUserPreference(@RequestBody UserPreferenceDTO userPreferenceDTO) throws URISyntaxException {
        log.debug("REST request to save UserPreference : {}", userPreferenceDTO);
        if (userPreferenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new userPreference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPreferenceDTO result = userPreferenceService.save(userPreferenceDTO);
        return ResponseEntity.created(new URI("/api/user-preferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-preferences} : Updates an existing userPreference.
     *
     * @param userPreferenceDTO the userPreferenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPreferenceDTO,
     * or with status {@code 400 (Bad Request)} if the userPreferenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userPreferenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-preferences")
    public ResponseEntity<UserPreferenceDTO> updateUserPreference(@RequestBody UserPreferenceDTO userPreferenceDTO) throws URISyntaxException {
        log.debug("REST request to update UserPreference : {}", userPreferenceDTO);
        if (userPreferenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserPreferenceDTO result = userPreferenceService.save(userPreferenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userPreferenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-preferences} : get all the userPreferences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPreferences in body.
     */
    @GetMapping("/user-preferences")
    public List<UserPreferenceDTO> getAllUserPreferences() {
        log.debug("REST request to get all UserPreferences");
        return userPreferenceService.findAll();
    }

    /**
     * {@code GET  /user-preferences/:id} : get the "id" userPreference.
     *
     * @param id the id of the userPreferenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPreferenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-preferences/{id}")
    public ResponseEntity<UserPreferenceDTO> getUserPreference(@PathVariable Long id) {
        log.debug("REST request to get UserPreference : {}", id);
        Optional<UserPreferenceDTO> userPreferenceDTO = userPreferenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userPreferenceDTO);
    }

    /**
     * {@code DELETE  /user-preferences/:id} : delete the "id" userPreference.
     *
     * @param id the id of the userPreferenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-preferences/{id}")
    public ResponseEntity<Void> deleteUserPreference(@PathVariable Long id) {
        log.debug("REST request to delete UserPreference : {}", id);
        userPreferenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/user-preferences?query=:query} : search for the userPreference corresponding
     * to the query.
     *
     * @param query the query of the userPreference search.
     * @return the result of the search.
     */
    @GetMapping("/_search/user-preferences")
    public List<UserPreferenceDTO> searchUserPreferences(@RequestParam String query) {
        log.debug("REST request to search UserPreferences for query {}", query);
        return userPreferenceService.search(query);
    }

}
