package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.AuthorizedSignatory;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AuthorizedSignatory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorizedSignatoryRepository extends JPASearchRepository<AuthorizedSignatory, Long> {

}
