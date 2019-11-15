package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.LegalEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LegalEntity} and its DTO {@link LegalEntityDTO}.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface LegalEntityMapper extends EntityMapper<LegalEntityDTO, LegalEntity> {

    @Mapping(source = "branch.id", target = "branchId")
    LegalEntityDTO toDto(LegalEntity legalEntity);

    @Mapping(source = "branchId", target = "branch")
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
