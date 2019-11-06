package com.synectiks.pref.service;

import com.synectiks.pref.service.dto.HolidayDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.pref.domain.Holiday}.
 */
public interface HolidayService {

    /**
     * Save a holiday.
     *
     * @param holidayDTO the entity to save.
     * @return the persisted entity.
     */
    HolidayDTO save(HolidayDTO holidayDTO);

    /**
     * Get all the holidays.
     *
     * @return the list of entities.
     */
    List<HolidayDTO> findAll();


    /**
     * Get the "id" holiday.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HolidayDTO> findOne(Long id);

    /**
     * Delete the "id" holiday.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the holiday corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<HolidayDTO> search(String query);
}
