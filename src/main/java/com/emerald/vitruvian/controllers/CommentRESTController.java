package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.CommentEntity;
import com.emerald.vitruvian.repositories.CommentRepo;
import com.emerald.vitruvian.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class CommentRESTController {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private CommentService commentService;

    @GetMapping("commentPosts")
    @ResponseBody
    public List<CommentEntity> getAllComments(){
        List<CommentEntity> comments = StreamSupport.stream(commentRepo.findAll().spliterator(), false).toList();
        return commentService.formatCommentEntityJSON(comments);
    }

}
