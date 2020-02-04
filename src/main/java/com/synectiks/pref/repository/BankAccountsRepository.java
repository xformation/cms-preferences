package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.BankAccounts;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the BankAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountsRepository extends JPASearchRepository<BankAccounts, Long> {

}
