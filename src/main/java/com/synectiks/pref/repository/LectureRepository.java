package com.synectiks.pref.repository;

import com.synectiks.pref.domain.Lecture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Lecture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

}
