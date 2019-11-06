package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.UserPreferenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserPreference} and its DTO {@link UserPreferenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserPreferenceMapper extends EntityMapper<UserPreferenceDTO, UserPreference> {



    default UserPreference fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserPreference userPreference = new UserPreference();
        userPreference.setId(id);
        return userPreference;
    }
}
