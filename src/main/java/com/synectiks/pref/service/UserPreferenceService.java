package com.synectiks.pref.service;

import com.synectiks.pref.service.dto.UserPreferenceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.pref.domain.UserPreference}.
 */
public interface UserPreferenceService {

    /**
     * Save a userPreference.
     *
     * @param userPreferenceDTO the entity to save.
     * @return the persisted entity.
     */
    UserPreferenceDTO save(UserPreferenceDTO userPreferenceDTO);

    /**
     * Get all the userPreferences.
     *
     * @return the list of entities.
     */
    List<UserPreferenceDTO> findAll();


    /**
     * Get the "id" userPreference.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserPreferenceDTO> findOne(Long id);

    /**
     * Delete the "id" userPreference.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the userPreference corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<UserPreferenceDTO> search(String query);
}
