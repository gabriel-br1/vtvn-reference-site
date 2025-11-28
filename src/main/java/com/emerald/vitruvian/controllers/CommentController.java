package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.CommentDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.CommentService;
import com.emerald.vitruvian.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/postComment/{commentImageId}")
    public String postComment(@ModelAttribute CommentDTO comment,
                              BindingResult result,
                              @PathVariable("commentImageId") long commentImageId,
                              Model model){
        if(result.hasErrors()){
            //add error message
            model.addAttribute("CommentDTO", comment);
            model.addAttribute("ImageEntryEntity", comment.getImage());
            return "pages/imagePrincipal";
        }

        UserEntity userEntity = userRepo.findById(userService.getPrincipalId());

        comment.setImage(imageEntryRepo.findById(commentImageId).orElse(new ImageEntryEntity()));
        comment.setUser(userEntity);

        commentService.add(comment);

        model.addAttribute("ImageEntryEntity", imageEntryRepo.findById(commentImageId).orElse(new ImageEntryEntity()));
        model.addAttribute("imageEntry", imageEntryMapper.toDTO(imageEntryRepo.findById(commentImageId).orElse(new ImageEntryEntity())));
        model.addAttribute("CommentDTO", new CommentDTO());
        return "pages/imagePrincipal";
    }

}
