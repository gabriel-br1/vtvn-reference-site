package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.AlgorithmProfileEntity;
import com.emerald.vitruvian.models.AlgorithmProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AlgorithmMapper {

    AlgorithmProfileEntity toEntity(AlgorithmProfileDTO source);

    AlgorithmProfileDTO toDTO(AlgorithmProfileEntity source);

    void updateAlgorithmProfileDTO(AlgorithmProfileDTO source, @MappingTarget AlgorithmProfileDTO target);

    void updateAlgorithmProfileEntity(AlgorithmProfileEntity source, @MappingTarget AlgorithmProfileEntity target);

}
