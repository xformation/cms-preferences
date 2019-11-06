package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Notifications;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Notifications} entity.
 */
public interface NotificationsSearchRepository extends ElasticsearchRepository<Notifications, Long> {
}
