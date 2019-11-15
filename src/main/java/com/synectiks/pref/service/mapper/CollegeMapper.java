package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.CollegeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link College} and its DTO {@link CollegeDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface CollegeMapper extends EntityMapper<CollegeDTO, College> {

    @Mapping(source = "country.id", target = "countryId")
    CollegeDTO toDto(College college);

    @Mapping(source = "countryId", target = "country")
    College toEntity(CollegeDTO collegeDTO);

    default College fromId(Long id) {
        if (id == null) {
            return null;
        }
        College college = new College();
        college.setId(id);
        return college;
    }
}
