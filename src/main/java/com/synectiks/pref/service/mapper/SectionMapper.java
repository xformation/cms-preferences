package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.SectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Section} and its DTO {@link SectionDTO}.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class})
public interface SectionMapper extends EntityMapper<SectionDTO, Section> {

    @Mapping(source = "batch.id", target = "batchId")
    SectionDTO toDto(Section section);

    @Mapping(source = "batchId", target = "batch")
    Section toEntity(SectionDTO sectionDTO);

    default Section fromId(Long id) {
        if (id == null) {
            return null;
        }
        Section section = new Section();
        section.setId(id);
        return section;
    }
}
