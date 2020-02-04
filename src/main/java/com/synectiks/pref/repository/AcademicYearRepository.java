package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AcademicYear entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicYearRepository extends JPASearchRepository<AcademicYear, Long> {

}
