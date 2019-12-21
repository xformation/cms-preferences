package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.MetaLecture;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MetaLecture} entity.
 */
public interface MetaLectureSearchRepository extends JPASearchRepository<MetaLecture, Long> {
}
