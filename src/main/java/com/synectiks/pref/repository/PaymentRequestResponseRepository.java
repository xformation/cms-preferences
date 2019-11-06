package com.synectiks.pref.repository;

import com.synectiks.pref.domain.PaymentRequestResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymentRequestResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestResponseRepository extends JpaRepository<PaymentRequestResponse, Long> {

}
