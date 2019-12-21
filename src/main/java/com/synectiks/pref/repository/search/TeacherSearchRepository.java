package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Teacher} entity.
 */
public interface TeacherSearchRepository extends JPASearchRepository<Teacher, Long> {
}
