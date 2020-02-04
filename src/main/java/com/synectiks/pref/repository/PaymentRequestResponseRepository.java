package com.synectiks.pref.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.pref.domain.PaymentRequestResponse;
import com.synectiks.pref.utils.JPASearchRepository;


/**
 * Spring Data  repository for the PaymentRequestResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestResponseRepository extends JPASearchRepository<PaymentRequestResponse, Long> {

}
