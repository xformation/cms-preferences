package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.LegalEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link LegalEntity} entity.
 */
public interface LegalEntitySearchRepository extends ElasticsearchRepository<LegalEntity, Long> {
}
