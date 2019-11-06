package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.UserPreference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserPreference} entity.
 */
public interface UserPreferenceSearchRepository extends ElasticsearchRepository<UserPreference, Long> {
}
