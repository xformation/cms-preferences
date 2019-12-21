package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AcademicYear} entity.
 */
public interface AcademicYearSearchRepository extends JPASearchRepository<AcademicYear, Long> {
}
