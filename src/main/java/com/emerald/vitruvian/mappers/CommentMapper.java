package com.emerald.vitruvian.mappers;

import com.emerald.vitruvian.Entities.CommentEntity;
import com.emerald.vitruvian.models.CommentDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CommentMapper {

    CommentEntity toEntity(CommentDTO source);

    CommentDTO toDTO(CommentEntity source);

}
