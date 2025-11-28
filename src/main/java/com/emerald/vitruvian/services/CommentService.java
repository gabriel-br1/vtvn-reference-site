package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.CommentEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.CommentMapper;
import com.emerald.vitruvian.models.CommentDTO;
import com.emerald.vitruvian.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private CommentMapper commentMapper;

    public void add(CommentDTO commentDTO){
        CommentEntity commentEntity = commentMapper.toEntity(commentDTO);
        commentRepo.save(commentEntity);
        System.out.println(commentEntity.getCommentText());
        System.out.println(commentEntity.getImage().getTitle());
        System.out.println(commentEntity.getUser().getEmail());
    }

}
