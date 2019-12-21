package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Country;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Country} entity.
 */
public interface CountrySearchRepository extends JPASearchRepository<Country, Long> {
}
