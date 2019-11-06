package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Term;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Term} entity.
 */
public interface TermSearchRepository extends ElasticsearchRepository<Term, Long> {
}
