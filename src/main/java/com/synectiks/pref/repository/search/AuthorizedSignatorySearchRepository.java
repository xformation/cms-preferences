package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.AuthorizedSignatory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AuthorizedSignatory} entity.
 */
public interface AuthorizedSignatorySearchRepository extends ElasticsearchRepository<AuthorizedSignatory, Long> {
}
