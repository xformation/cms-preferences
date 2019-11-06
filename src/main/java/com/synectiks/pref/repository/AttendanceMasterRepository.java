package com.synectiks.pref.repository;

import com.synectiks.pref.domain.AttendanceMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AttendanceMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendanceMasterRepository extends JpaRepository<AttendanceMaster, Long> {

}
