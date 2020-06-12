package com.synectiks.pref.repository;

import com.synectiks.pref.domain.Facility;
import com.synectiks.pref.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Facility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityRepository extends JPASearchRepository<Facility, Long> {

}
