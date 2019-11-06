package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.TermDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Term} and its DTO {@link TermDTO}.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface TermMapper extends EntityMapper<TermDTO, Term> {

    @Mapping(source = "academicyear.id", target = "academicyearId")
    TermDTO toDto(Term term);

    @Mapping(source = "academicyearId", target = "academicyear")
    Term toEntity(TermDTO termDTO);

    default Term fromId(Long id) {
        if (id == null) {
            return null;
        }
        Term term = new Term();
        term.setId(id);
        return term;
    }
}
