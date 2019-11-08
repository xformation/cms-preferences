package com.synectiks.pref.repository;

import com.synectiks.pref.domain.MetaLecture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MetaLecture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetaLectureRepository extends JpaRepository<MetaLecture, Long> {

}
