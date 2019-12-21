package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.College;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link College} entity.
 */
public interface CollegeSearchRepository extends JPASearchRepository<College, Long> {
}
