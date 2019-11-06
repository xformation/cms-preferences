package com.synectiks.pref.service;

import com.synectiks.pref.service.dto.CurrencyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.pref.domain.Currency}.
 */
public interface CurrencyService {

    /**
     * Save a currency.
     *
     * @param currencyDTO the entity to save.
     * @return the persisted entity.
     */
    CurrencyDTO save(CurrencyDTO currencyDTO);

    /**
     * Get all the currencies.
     *
     * @return the list of entities.
     */
    List<CurrencyDTO> findAll();


    /**
     * Get the "id" currency.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CurrencyDTO> findOne(Long id);

    /**
     * Delete the "id" currency.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the currency corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<CurrencyDTO> search(String query);
}
