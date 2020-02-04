package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Teach;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Teach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachRepository extends JPASearchRepository<Teach, Long> {

}
