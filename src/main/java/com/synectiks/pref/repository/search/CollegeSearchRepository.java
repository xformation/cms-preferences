package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.College;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link College} entity.
 */
public interface CollegeSearchRepository extends ElasticsearchRepository<College, Long> {
}
