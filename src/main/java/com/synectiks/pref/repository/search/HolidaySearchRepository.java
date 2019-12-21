package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Holiday} entity.
 */
public interface HolidaySearchRepository extends JPASearchRepository<Holiday, Long> {
}
