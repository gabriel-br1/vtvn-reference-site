package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.models.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO toDTO(UserEntity source);

    public UserEntity toEntity(UserDTO source);

}
