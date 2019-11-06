package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.ExceptionRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ExceptionRecord} entity.
 */
public interface ExceptionRecordSearchRepository extends ElasticsearchRepository<ExceptionRecord, Long> {
}
