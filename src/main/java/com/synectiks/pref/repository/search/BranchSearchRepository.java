package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Branch} entity.
 */
public interface BranchSearchRepository extends JPASearchRepository<Branch, Long> {
}
