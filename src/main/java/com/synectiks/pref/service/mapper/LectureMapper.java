package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.LectureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lecture} and its DTO {@link LectureDTO}.
 */
@Mapper(componentModel = "spring", uses = {AttendanceMasterMapper.class})
public interface LectureMapper extends EntityMapper<LectureDTO, Lecture> {

    @Mapping(source = "attendancemaster.id", target = "attendancemasterId")
    LectureDTO toDto(Lecture lecture);

    @Mapping(source = "attendancemasterId", target = "attendancemaster")
    Lecture toEntity(LectureDTO lectureDTO);

    default Lecture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lecture lecture = new Lecture();
        lecture.setId(id);
        return lecture;
    }
}
