package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Term;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Term entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermRepository extends JPASearchRepository<Term, Long> {

}
