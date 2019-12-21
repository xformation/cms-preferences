package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.City;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link City} entity.
 */
public interface CitySearchRepository extends JPASearchRepository<City, Long> {
}
