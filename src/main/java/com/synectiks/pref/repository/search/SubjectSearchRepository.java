package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Subject;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Subject} entity.
 */
public interface SubjectSearchRepository extends JPASearchRepository<Subject, Long> {
}
