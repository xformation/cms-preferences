package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.MetaLecture;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the MetaLecture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetaLectureRepository extends JPASearchRepository<MetaLecture, Long> {

}
