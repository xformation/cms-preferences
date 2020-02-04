package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Notifications;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Notifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationsRepository extends JPASearchRepository<Notifications, Long> {

}
