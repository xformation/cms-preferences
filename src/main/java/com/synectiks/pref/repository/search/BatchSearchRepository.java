package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Batch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Batch} entity.
 */
public interface BatchSearchRepository extends ElasticsearchRepository<Batch, Long> {
}
