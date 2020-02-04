package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.State;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the State entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StateRepository extends JPASearchRepository<State, Long> {

}
