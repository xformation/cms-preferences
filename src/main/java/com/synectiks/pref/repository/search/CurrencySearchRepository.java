package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Currency;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Currency} entity.
 */
public interface CurrencySearchRepository extends JPASearchRepository<Currency, Long> {
}
