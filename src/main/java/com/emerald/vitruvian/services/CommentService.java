package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.CommentEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.CommentMapper;
import com.emerald.vitruvian.models.CommentDTO;
import com.emerald.vitruvian.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

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

    // finds comment entities tied to an image through its id
    public List<CommentEntity> findImageComments(ImageEntryEntity image){
        return StreamSupport.stream(commentRepo.findAll().spliterator(), false)
                .filter(n -> n.getImage().getImageId() == image.getImageId())
                .toList();
    }

    public List<CommentEntity> formatCommentEntityJSON(List<CommentEntity> comments) {
        for(CommentEntity comment : comments){
            UserEntity idUser = new UserEntity();
            idUser.setId(comment.getUser().getId());
            comment.setUser(idUser);

            ImageEntryEntity idImage = new ImageEntryEntity();
            idImage.setImageId(comment.getImage().getImageId());
            comment.setImage(idImage);
        }
        return comments;
    }
}
