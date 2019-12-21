package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.BankAccounts;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link BankAccounts} entity.
 */
public interface BankAccountsSearchRepository extends JPASearchRepository<BankAccounts, Long> {
}
