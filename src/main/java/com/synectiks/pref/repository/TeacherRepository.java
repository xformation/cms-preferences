package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Teacher;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Teacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherRepository extends JPASearchRepository<Teacher, Long> {

}
