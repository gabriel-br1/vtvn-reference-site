package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.ReportEntity;
import com.emerald.vitruvian.models.ReportDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ReportMapper {

    ReportEntity toEntity(ReportDTO source);

    ReportDTO toDTO(ReportEntity source);

}
