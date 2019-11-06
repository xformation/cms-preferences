package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.HolidayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Holiday} and its DTO {@link HolidayDTO}.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface HolidayMapper extends EntityMapper<HolidayDTO, Holiday> {

    @Mapping(source = "academicyear.id", target = "academicyearId")
    HolidayDTO toDto(Holiday holiday);

    @Mapping(source = "academicyearId", target = "academicyear")
    Holiday toEntity(HolidayDTO holidayDTO);

    default Holiday fromId(Long id) {
        if (id == null) {
            return null;
        }
        Holiday holiday = new Holiday();
        holiday.setId(id);
        return holiday;
    }
}
