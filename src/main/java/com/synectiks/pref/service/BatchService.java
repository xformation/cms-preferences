package com.synectiks.pref.service;

import com.synectiks.pref.service.dto.BatchDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.pref.domain.Batch}.
 */
public interface BatchService {

    /**
     * Save a batch.
     *
     * @param batchDTO the entity to save.
     * @return the persisted entity.
     */
    BatchDTO save(BatchDTO batchDTO);

    /**
     * Get all the batches.
     *
     * @return the list of entities.
     */
    List<BatchDTO> findAll();


    /**
     * Get the "id" batch.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BatchDTO> findOne(Long id);

    /**
     * Delete the "id" batch.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the batch corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<BatchDTO> search(String query);
}
