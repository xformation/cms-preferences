package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Holiday;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Holiday} entity.
 */
public interface HolidaySearchRepository extends ElasticsearchRepository<Holiday, Long> {
}
