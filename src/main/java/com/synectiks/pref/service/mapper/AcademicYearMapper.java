package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.AcademicYearDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AcademicYear} and its DTO {@link AcademicYearDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AcademicYearMapper extends EntityMapper<AcademicYearDTO, AcademicYear> {



    default AcademicYear fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(id);
        return academicYear;
    }
}
