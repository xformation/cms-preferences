package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.NotificationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Notifications and its DTO NotificationsDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface NotificationsMapper extends EntityMapper<NotificationsDTO, Notifications> {

    @Mapping(source = "academicYear.id", target = "academicYearId")
    NotificationsDTO toDto(Notifications notifications);

    @Mapping(source = "academicYearId", target = "academicYear")
    Notifications toEntity(NotificationsDTO notificationsDTO);

    default Notifications fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notifications notifications = new Notifications();
        notifications.setId(id);
        return notifications;
    }
}
