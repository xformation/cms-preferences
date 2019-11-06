package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.BranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Branch} and its DTO {@link BranchDTO}.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class, CityMapper.class, StateMapper.class})
public interface BranchMapper extends EntityMapper<BranchDTO, Branch> {

    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "state.id", target = "stateId")
    BranchDTO toDto(Branch branch);

    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "stateId", target = "state")
    Branch toEntity(BranchDTO branchDTO);

    default Branch fromId(Long id) {
        if (id == null) {
            return null;
        }
        Branch branch = new Branch();
        branch.setId(id);
        return branch;
    }
}
