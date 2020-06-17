package com.synectiks.pref.service;

import com.synectiks.pref.service.dto.NotificationsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Notifications.
 */
public interface NotificationsService {

    /**
     * Save a notifications.
     *
     * @param notificationsDTO the entity to save
     * @return the persisted entity
     */
    NotificationsDTO save(NotificationsDTO notificationsDTO);

    /**
     * Get all the notifications.
     *
     * @return the list of entities
     */
    List<NotificationsDTO> findAll();


    /**
     * Get the "id" notifications.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NotificationsDTO> findOne(Long id);

    /**
     * Delete the "id" notifications.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the notifications corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<NotificationsDTO> search(String query);
}
