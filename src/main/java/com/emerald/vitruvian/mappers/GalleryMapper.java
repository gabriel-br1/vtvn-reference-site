package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.models.GalleryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GalleryMapper {
    GalleryEntity toEntity(GalleryDTO source);

    GalleryDTO toDTO(GalleryEntity source);

    void updateGalleryDTO(GalleryDTO source, @MappingTarget GalleryDTO target);

    void updateGalleryEntity(GalleryEntity source, @MappingTarget GalleryEntity target);
}
