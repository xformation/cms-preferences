package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Department;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Department} entity.
 */
public interface DepartmentSearchRepository extends JPASearchRepository<Department, Long> {
}
