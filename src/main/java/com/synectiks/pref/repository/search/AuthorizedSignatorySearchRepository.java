package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.AuthorizedSignatory;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AuthorizedSignatory} entity.
 */
public interface AuthorizedSignatorySearchRepository extends JPASearchRepository<AuthorizedSignatory, Long> {
}
