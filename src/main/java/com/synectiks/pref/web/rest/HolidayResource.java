package com.synectiks.pref.web.rest;

import com.synectiks.pref.service.HolidayService;
import com.synectiks.pref.web.rest.errors.BadRequestAlertException;
import com.synectiks.pref.service.dto.HolidayDTO;

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
 * REST controller for managing {@link com.synectiks.pref.domain.Holiday}.
 */
@RestController
@RequestMapping("/api")
public class HolidayResource {

    private final Logger log = LoggerFactory.getLogger(HolidayResource.class);

    private static final String ENTITY_NAME = "holiday";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HolidayService holidayService;

    public HolidayResource(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * {@code POST  /holidays} : Create a new holiday.
     *
     * @param holidayDTO the holidayDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new holidayDTO, or with status {@code 400 (Bad Request)} if the holiday has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/holidays")
    public ResponseEntity<HolidayDTO> createHoliday(@RequestBody HolidayDTO holidayDTO) throws URISyntaxException {
        log.debug("REST request to save Holiday : {}", holidayDTO);
        if (holidayDTO.getId() != null) {
            throw new BadRequestAlertException("A new holiday cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HolidayDTO result = holidayService.save(holidayDTO);
        return ResponseEntity.created(new URI("/api/holidays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /holidays} : Updates an existing holiday.
     *
     * @param holidayDTO the holidayDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated holidayDTO,
     * or with status {@code 400 (Bad Request)} if the holidayDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the holidayDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/holidays")
    public ResponseEntity<HolidayDTO> updateHoliday(@RequestBody HolidayDTO holidayDTO) throws URISyntaxException {
        log.debug("REST request to update Holiday : {}", holidayDTO);
        if (holidayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HolidayDTO result = holidayService.save(holidayDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, holidayDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /holidays} : get all the holidays.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of holidays in body.
     */
    @GetMapping("/holidays")
    public List<HolidayDTO> getAllHolidays() {
        log.debug("REST request to get all Holidays");
        return holidayService.findAll();
    }

    /**
     * {@code GET  /holidays/:id} : get the "id" holiday.
     *
     * @param id the id of the holidayDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the holidayDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/holidays/{id}")
    public ResponseEntity<HolidayDTO> getHoliday(@PathVariable Long id) {
        log.debug("REST request to get Holiday : {}", id);
        Optional<HolidayDTO> holidayDTO = holidayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(holidayDTO);
    }

    /**
     * {@code DELETE  /holidays/:id} : delete the "id" holiday.
     *
     * @param id the id of the holidayDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/holidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        log.debug("REST request to delete Holiday : {}", id);
        holidayService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/holidays?query=:query} : search for the holiday corresponding
     * to the query.
     *
     * @param query the query of the holiday search.
     * @return the result of the search.
     */
    @GetMapping("/_search/holidays")
    public List<HolidayDTO> searchHolidays(@RequestParam String query) {
        log.debug("REST request to search Holidays for query {}", query);
        return holidayService.search(query);
    }

}
