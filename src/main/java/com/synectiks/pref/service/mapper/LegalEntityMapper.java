package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.LegalEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LegalEntity} and its DTO {@link LegalEntityDTO}.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, CollegeMapper.class, StateMapper.class, CityMapper.class})
public interface LegalEntityMapper extends EntityMapper<LegalEntityDTO, LegalEntity> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "state.id", target = "stateId")
    @Mapping(source = "city.id", target = "cityId")
    LegalEntityDTO toDto(LegalEntity legalEntity);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "stateId", target = "state")
    @Mapping(source = "cityId", target = "city")
    LegalEntity toEntity(LegalEntityDTO legalEntityDTO);

    default LegalEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setId(id);
        return legalEntity;
    }
}
