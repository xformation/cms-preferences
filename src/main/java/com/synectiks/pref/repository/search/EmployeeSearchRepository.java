package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Employee;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Employee} entity.
 */
public interface EmployeeSearchRepository extends JPASearchRepository<Employee, Long> {
}
