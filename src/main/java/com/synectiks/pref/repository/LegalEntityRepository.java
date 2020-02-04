package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.LegalEntity;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the LegalEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LegalEntityRepository extends JPASearchRepository<LegalEntity, Long> {

}
