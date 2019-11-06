package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.BankAccounts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link BankAccounts} entity.
 */
public interface BankAccountsSearchRepository extends ElasticsearchRepository<BankAccounts, Long> {
}
