package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Term;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Term} entity.
 */
public interface TermSearchRepository extends JPASearchRepository<Term, Long> {
}
