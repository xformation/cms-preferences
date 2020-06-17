package com.synectiks.pref.repository;

import com.synectiks.pref.domain.Notifications;
import com.synectiks.pref.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Notifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationsRepository extends JPASearchRepository<Notifications, Long> {

}
