package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.UserPreference;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserPreference} entity.
 */
public interface UserPreferenceSearchRepository extends JPASearchRepository<UserPreference, Long> {
}
