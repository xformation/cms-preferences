package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Subject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Subject} entity.
 */
public interface SubjectSearchRepository extends ElasticsearchRepository<Subject, Long> {
}
