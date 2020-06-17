package com.synectiks.pref.web.rest;
import com.synectiks.pref.service.NotificationsService;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.web.rest.util.HeaderUtil;
import com.synectiks.pref.service.dto.NotificationsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;



/**
 * REST controller for managing Notifications.
 */
@RestController
@RequestMapping("/api")
public class NotificationsResource {

    private final Logger log = LoggerFactory.getLogger(NotificationsResource.class);

    private static final String ENTITY_NAME = "notifications";

    private final NotificationsService notificationsService;

    public NotificationsResource(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    /**
     * POST  /notifications : Create a new notifications.
     *
     * @param notificationsDTO the notificationsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificationsDTO, or with status 400 (Bad Request) if the notifications has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notifications")
    public ResponseEntity<NotificationsDTO> createNotifications(@Valid @RequestBody NotificationsDTO notificationsDTO) throws URISyntaxException {
        log.debug("REST request to save Notifications : {}", notificationsDTO);
        if (notificationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifications cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationsDTO result = notificationsService.save(notificationsDTO);
        return ResponseEntity.created(new URI("/api/notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notifications : Updates an existing notifications.
     *
     * @param notificationsDTO the notificationsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificationsDTO,
     * or with status 400 (Bad Request) if the notificationsDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificationsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notifications")
    public ResponseEntity<NotificationsDTO> updateNotifications(@Valid @RequestBody NotificationsDTO notificationsDTO) throws URISyntaxException {
        log.debug("REST request to update Notifications : {}", notificationsDTO);
        if (notificationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationsDTO result = notificationsService.save(notificationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notifications : get all the notifications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of notifications in body
     */
    @GetMapping("/notifications")
    public List<NotificationsDTO> getAllNotifications() {
        log.debug("REST request to get all Notifications");
        return notificationsService.findAll();
    }

    /**
     * GET  /notifications/:id : get the "id" notifications.
     *
     * @param id the id of the notificationsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificationsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notifications/{id}")
    public ResponseEntity<NotificationsDTO> getNotifications(@PathVariable Long id) {
        log.debug("REST request to get Notifications : {}", id);
        Optional<NotificationsDTO> notificationsDTO = notificationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationsDTO);
    }

    /**
     * DELETE  /notifications/:id : delete the "id" notifications.
     *
     * @param id the id of the notificationsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<Void> deleteNotifications(@PathVariable Long id) {
        log.debug("REST request to delete Notifications : {}", id);
        notificationsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/notifications?query=:query : search for the notifications corresponding
     * to the query.
     *
     * @param query the query of the notifications search
     * @return the result of the search
     */
    @GetMapping("/_search/notifications")
    public List<NotificationsDTO> searchNotifications(@RequestParam String query) {
        log.debug("REST request to search Notifications for query {}", query);
        return notificationsService.search(query);
    }

}
