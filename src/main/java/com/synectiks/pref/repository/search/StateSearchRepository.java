package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.State;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link State} entity.
 */
public interface StateSearchRepository extends ElasticsearchRepository<State, Long> {
}
