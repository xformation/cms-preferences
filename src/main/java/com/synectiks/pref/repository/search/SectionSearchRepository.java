package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Section;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Section} entity.
 */
public interface SectionSearchRepository extends ElasticsearchRepository<Section, Long> {
}
