package com.synectiks.pref.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.pref.domain.UserPreference;
import com.synectiks.pref.repository.UserPreferenceRepository;
import com.synectiks.pref.repository.search.UserPreferenceSearchRepository;
import com.synectiks.pref.service.UserPreferenceService;
import com.synectiks.pref.service.dto.UserPreferenceDTO;
import com.synectiks.pref.service.mapper.UserPreferenceMapper;

/**
 * Service Implementation for managing {@link UserPreference}.
 */
@Service
@Transactional
public class UserPreferenceServiceImpl implements UserPreferenceService {

    private final Logger log = LoggerFactory.getLogger(UserPreferenceServiceImpl.class);

    private final UserPreferenceRepository userPreferenceRepository;

    private final UserPreferenceMapper userPreferenceMapper;

//    private final UserPreferenceSearchRepository userPreferenceSearchRepository;

    public UserPreferenceServiceImpl(UserPreferenceRepository userPreferenceRepository, UserPreferenceMapper userPreferenceMapper, UserPreferenceSearchRepository userPreferenceSearchRepository) {
        this.userPreferenceRepository = userPreferenceRepository;
        this.userPreferenceMapper = userPreferenceMapper;
//        this.userPreferenceSearchRepository = userPreferenceSearchRepository;
    }

    /**
     * Save a userPreference.
     *
     * @param userPreferenceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserPreferenceDTO save(UserPreferenceDTO userPreferenceDTO) {
        log.debug("Request to save UserPreference : {}", userPreferenceDTO);
        UserPreference userPreference = userPreferenceMapper.toEntity(userPreferenceDTO);
        userPreference = userPreferenceRepository.save(userPreference);
        UserPreferenceDTO result = userPreferenceMapper.toDto(userPreference);
//        userPreferenceSearchRepository.save(userPreference);
        return result;
    }

    /**
     * Get all the userPreferences.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserPreferenceDTO> findAll() {
        log.debug("Request to get all UserPreferences");
        return userPreferenceRepository.findAll().stream()
            .map(userPreferenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one userPreference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserPreferenceDTO> findOne(Long id) {
        log.debug("Request to get UserPreference : {}", id);
        return userPreferenceRepository.findById(id)
            .map(userPreferenceMapper::toDto);
    }

    /**
     * Delete the userPreference by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPreference : {}", id);
        userPreferenceRepository.deleteById(id);
//        userPreferenceSearchRepository.deleteById(id);
    }

    /**
     * Search for the userPreference corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserPreferenceDTO> search(String query) {
        log.debug("Request to search UserPreferences for query {}", query);
        return null;
    }
}
