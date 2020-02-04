package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Lecture;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Lecture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LectureRepository extends JPASearchRepository<Lecture, Long> {

}
