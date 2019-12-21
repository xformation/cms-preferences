package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.PaymentRequestResponse;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentRequestResponse} entity.
 */
public interface PaymentRequestResponseSearchRepository extends JPASearchRepository<PaymentRequestResponse, Long> {
}
