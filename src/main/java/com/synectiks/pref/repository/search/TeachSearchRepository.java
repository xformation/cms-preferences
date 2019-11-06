package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Teach;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Teach} entity.
 */
public interface TeachSearchRepository extends ElasticsearchRepository<Teach, Long> {
}
