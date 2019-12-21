package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ExceptionRecord} entity.
 */
public interface ExceptionRecordSearchRepository extends JPASearchRepository<ExceptionRecord, Long> {
}
