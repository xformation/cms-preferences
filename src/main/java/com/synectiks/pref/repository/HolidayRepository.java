package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Holiday entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HolidayRepository extends JPASearchRepository<Holiday, Long> {

}
