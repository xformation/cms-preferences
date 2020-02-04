package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.AttendanceMaster;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AttendanceMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendanceMasterRepository extends JPASearchRepository<AttendanceMaster, Long> {

}
