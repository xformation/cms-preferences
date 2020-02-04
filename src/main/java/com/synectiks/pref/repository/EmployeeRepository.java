package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Employee;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JPASearchRepository<Employee, Long> {

}
