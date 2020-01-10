package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Course;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Course} entity.
 */
public interface CourseSearchRepository extends JPASearchRepository<Course, Long> {
}
