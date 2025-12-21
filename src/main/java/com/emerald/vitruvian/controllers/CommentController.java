package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.CommentEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.CommentDTO;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.CommentService;
import com.emerald.vitruvian.services.ImageEntryService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private ImageController imageController;

    @PostMapping("/postComment/{commentImageId}")
    public String postComment(@Valid @ModelAttribute CommentDTO comment,
                              BindingResult result,
                              @PathVariable("commentImageId") long commentImageId,
                              Model model){

        UserEntity userEntity = userRepo.findById(userService.getPrincipalId());
        comment.setImage(imageEntryRepo.findById(commentImageId).orElse(new ImageEntryEntity()));
        comment.setUser(userEntity);

        List<CommentEntity> comments = commentService.findImageComments(comment.getImage());
        for(CommentEntity commentEntity : comments){
            ImageEntryDTO profilePicture = imageEntryService.getProfilePicture(commentEntity.getUser());
            if(profilePicture.getTitle() == null){
                commentEntity.setProfileImagePath("placeholder.jpg");
            } else {
                commentEntity.setProfileImagePath(profilePicture.getFileName());
            }
            commentEntity.setProfileName(commentEntity.getUser().getEmail());
        }

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(ObjectError objectError : result.getAllErrors()){
                errors.add(objectError.getDefaultMessage());
            }
            model.addAttribute("Errors", errors);
            if (userEntity != null){
                if (userEntity.getId() == comment.getImage().getUser().getId()){
                    returnImagePage(commentImageId, comments, model);
                    return "pages/imagePrincipal";
                }
            }

            returnImagePage(commentImageId, comments, model);
            return "pages/image";
        }

        commentService.add(comment);

        return imageController.renderImagePage(commentImageId, model);
    }

    private void returnImagePage(long commentImageId, List<CommentEntity> comments, Model model){
        model.addAttribute("ImageEntryEntity", imageEntryRepo.findById(commentImageId).orElse(new ImageEntryEntity()));
        model.addAttribute("imageEntry", imageEntryMapper.toDTO(imageEntryRepo.findById(commentImageId).orElse(new ImageEntryEntity())));
        model.addAttribute("CommentDTO", new CommentDTO());
        if(comments != null){
            model.addAttribute("Comments", comments);
        } else {
            model.addAttribute("Comments", List.of(new CommentEntity()));
        }
    }

}
