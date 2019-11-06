package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.ExceptionRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExceptionRecord} and its DTO {@link ExceptionRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExceptionRecordMapper extends EntityMapper<ExceptionRecordDTO, ExceptionRecord> {



    default ExceptionRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExceptionRecord exceptionRecord = new ExceptionRecord();
        exceptionRecord.setId(id);
        return exceptionRecord;
    }
}
