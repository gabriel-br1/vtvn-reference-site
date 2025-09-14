package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO toDTO(UserEntity source);

    public UserEntity toEntity(UserDTO source);

    void updateUserDTO(UserDTO source, @MappingTarget UserDTO target);

    void updateUserEntity(UserEntity source, @MappingTarget UserEntity target);

}
