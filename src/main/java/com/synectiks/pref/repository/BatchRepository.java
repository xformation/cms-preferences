package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Batch;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Batch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchRepository extends JPASearchRepository<Batch, Long> {

}
