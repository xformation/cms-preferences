package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.AttendanceMaster;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AttendanceMaster} entity.
 */
public interface AttendanceMasterSearchRepository extends ElasticsearchRepository<AttendanceMaster, Long> {
}
