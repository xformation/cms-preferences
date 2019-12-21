package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Batch} entity.
 */
public interface BatchSearchRepository extends JPASearchRepository<Batch, Long> {
}
