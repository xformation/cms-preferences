package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.AcademicYear;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AcademicYear} entity.
 */
public interface AcademicYearSearchRepository extends ElasticsearchRepository<AcademicYear, Long> {
}
