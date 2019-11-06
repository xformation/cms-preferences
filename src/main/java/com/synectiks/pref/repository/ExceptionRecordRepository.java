package com.synectiks.pref.repository;

import com.synectiks.pref.domain.ExceptionRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExceptionRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExceptionRecordRepository extends JpaRepository<ExceptionRecord, Long> {

}
