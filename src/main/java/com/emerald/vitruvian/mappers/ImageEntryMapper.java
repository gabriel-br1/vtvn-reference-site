package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.models.ImageEntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ImageEntryMapper{

    ImageEntryEntity toEntity(ImageEntryDTO source);

    ImageEntryDTO toDTO(ImageEntryEntity source);

    void updateImageEntryDTO(ImageEntryDTO source, @MappingTarget ImageEntryDTO target);

    void updateImageEntryEntity(ImageEntryEntity source, @MappingTarget ImageEntryEntity target);

}
