package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Branch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchRepository extends JPASearchRepository<Branch, Long> {

}
