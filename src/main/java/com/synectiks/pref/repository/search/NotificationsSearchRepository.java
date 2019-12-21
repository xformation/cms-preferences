package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Notifications;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Notifications} entity.
 */
public interface NotificationsSearchRepository extends JPASearchRepository<Notifications, Long> {
}
