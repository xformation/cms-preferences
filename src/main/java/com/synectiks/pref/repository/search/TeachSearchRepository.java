package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Teach} entity.
 */
public interface TeachSearchRepository extends JPASearchRepository<Teach, Long> {
}
