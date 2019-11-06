package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.CollegeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link College} and its DTO {@link CollegeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CollegeMapper extends EntityMapper<CollegeDTO, College> {



    default College fromId(Long id) {
        if (id == null) {
            return null;
        }
        College college = new College();
        college.setId(id);
        return college;
    }
}
