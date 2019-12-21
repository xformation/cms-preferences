package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Section;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Section} entity.
 */
public interface SectionSearchRepository extends JPASearchRepository<Section, Long> {
}
