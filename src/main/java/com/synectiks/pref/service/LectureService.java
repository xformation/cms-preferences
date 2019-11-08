package com.synectiks.pref.service;

import com.synectiks.pref.service.dto.LectureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.pref.domain.Lecture}.
 */
public interface LectureService {

    /**
     * Save a lecture.
     *
     * @param lectureDTO the entity to save.
     * @return the persisted entity.
     */
    LectureDTO save(LectureDTO lectureDTO);

    /**
     * Get all the lectures.
     *
     * @return the list of entities.
     */
    List<LectureDTO> findAll();


    /**
     * Get the "id" lecture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LectureDTO> findOne(Long id);

    /**
     * Delete the "id" lecture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the lecture corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<LectureDTO> search(String query);
}
