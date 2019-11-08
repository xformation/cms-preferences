package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Lecture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Lecture} entity.
 */
public interface LectureSearchRepository extends ElasticsearchRepository<Lecture, Long> {
}
