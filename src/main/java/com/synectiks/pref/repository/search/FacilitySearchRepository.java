package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Facility;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Facility entity.
 */
public interface FacilitySearchRepository extends JPASearchRepository<Facility, Long> {
}
