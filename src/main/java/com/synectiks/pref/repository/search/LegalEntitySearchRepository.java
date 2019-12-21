package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.LegalEntity;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link LegalEntity} entity.
 */
public interface LegalEntitySearchRepository extends JPASearchRepository<LegalEntity, Long> {
}
