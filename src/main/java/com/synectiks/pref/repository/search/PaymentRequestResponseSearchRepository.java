package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.PaymentRequestResponse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentRequestResponse} entity.
 */
public interface PaymentRequestResponseSearchRepository extends ElasticsearchRepository<PaymentRequestResponse, Long> {
}
