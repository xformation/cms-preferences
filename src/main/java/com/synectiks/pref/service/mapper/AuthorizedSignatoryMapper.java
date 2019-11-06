package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.AuthorizedSignatoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuthorizedSignatory} and its DTO {@link AuthorizedSignatoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, CollegeMapper.class})
public interface AuthorizedSignatoryMapper extends EntityMapper<AuthorizedSignatoryDTO, AuthorizedSignatory> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "college.id", target = "collegeId")
    AuthorizedSignatoryDTO toDto(AuthorizedSignatory authorizedSignatory);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "collegeId", target = "college")
    AuthorizedSignatory toEntity(AuthorizedSignatoryDTO authorizedSignatoryDTO);

    default AuthorizedSignatory fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuthorizedSignatory authorizedSignatory = new AuthorizedSignatory();
        authorizedSignatory.setId(id);
        return authorizedSignatory;
    }
}
