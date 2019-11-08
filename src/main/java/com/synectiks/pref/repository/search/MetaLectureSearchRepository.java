package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.MetaLecture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MetaLecture} entity.
 */
public interface MetaLectureSearchRepository extends ElasticsearchRepository<MetaLecture, Long> {
}
