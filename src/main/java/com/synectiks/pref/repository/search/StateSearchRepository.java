package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.State;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link State} entity.
 */
public interface StateSearchRepository extends JPASearchRepository<State, Long> {
}
