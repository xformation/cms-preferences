package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Branch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Branch} entity.
 */
public interface BranchSearchRepository extends ElasticsearchRepository<Branch, Long> {
}
