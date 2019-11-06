package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Teacher} entity.
 */
public interface TeacherSearchRepository extends ElasticsearchRepository<Teacher, Long> {
}
