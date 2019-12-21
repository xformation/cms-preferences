package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.AttendanceMaster;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AttendanceMaster} entity.
 */
public interface AttendanceMasterSearchRepository extends JPASearchRepository<AttendanceMaster, Long> {
}
