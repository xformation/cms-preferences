package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Lecture;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Lecture} entity.
 */
public interface LectureSearchRepository extends JPASearchRepository<Lecture, Long> {
}
