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
import com.emerald.vitruvian.services.FileUploadService;
import com.emerald.vitruvian.services.ImageEntryService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentService commentService;

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private FileUploadService uploadService;

    @Autowired
    private HomeController homeController;

    @GetMapping("/script.js")
    public ResponseEntity<Resource> serveJs(){
        Resource resource = new ClassPathResource("static/script.js");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("application/javascript"))
                .body(resource);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/uploadImage")
    public String renderCharacterUpload(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadImage";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/createImage")
    public String createImage(@Valid @ModelAttribute ImageEntryDTO imageEntryDTO,
                              BindingResult result,
                              @RequestParam("image") MultipartFile image,
                              Model model){

        if(result.hasErrors() || imageEntryService.checkReservedChars(imageEntryDTO.getTitle())){
            List<String> errors = new ArrayList<>();
            for(ObjectError objectError : result.getAllErrors()){
                errors.add(objectError.getDefaultMessage());
            }
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            model.addAttribute("Errors", errors);
            return "pages/uploadImage";
        }

        if(!image.isEmpty()){
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            imageEntryDTO.setFileName(fileName);

            UserEntity user = userRepo
                    .findById(userService
                            .getPrincipalId());
            imageEntryDTO.setUser(user);

            imageEntryDTO.setIsProfile(0);

            String uploadDir = "src/main/resources/static/images/";

            try {
                uploadService.uploadFile(uploadDir, fileName, image);
            } catch (IOException e) {
                model.addAttribute("status", e.getMessage());
                return "error";
            }

            ImageEntryEntity savedImage = imageEntryService.add(imageEntryDTO);

        } else{
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadImage";
        }
        return "pages/uploadSuccess";
    }

    @GetMapping("/image/{id}")
    public String renderImagePage(@PathVariable("id") long id,
            Model model){

        ImageEntryEntity imageEntryEntity = imageEntryRepo
                .findById(id)
                .orElse(new ImageEntryEntity());
        ImageEntryDTO imageEntryDTO = imageEntryMapper.toDTO(imageEntryEntity);

        List<CommentEntity> comments = commentService.findImageComments(imageEntryEntity);
        for(CommentEntity comment : comments){
            ImageEntryDTO profilePicture = imageEntryService.getProfilePicture(comment.getUser());
            if(profilePicture.getTitle() == null){
                comment.setProfileImagePath("placeholder.jpg");
            } else {
                comment.setProfileImagePath(profilePicture.getFileName());
            }
            comment.setProfileName(comment.getUser().getEmail());
        }

        UserEntity userEntity = userRepo.findById(userService.getPrincipalId());
        if (userEntity != null){
            if (userEntity.getId() == imageEntryEntity.getUser().getId()){
                model.addAttribute("ImageEntryEntity", imageEntryEntity);
                model.addAttribute("imageEntry", imageEntryDTO);
                model.addAttribute("CommentDTO", new CommentDTO());
                if(comments != null){
                    model.addAttribute("Comments", comments);
                } else {
                    model.addAttribute("Comments", List.of(new CommentEntity()));
                }
                return "pages/imagePrincipal";
            }
        }

        model.addAttribute("imageEntry", imageEntryDTO);
        model.addAttribute("ImageEntryEntity", imageEntryEntity);
        model.addAttribute("CommentDTO", new CommentDTO());
        if(comments != null){
            model.addAttribute("Comments", comments);
        } else {
            model.addAttribute("Comments", List.of(new CommentEntity()));
        }

        return "pages/image";
    }

    @GetMapping("/results")
    public String renderSearchResults(String tagSearch,
                                      Model model){
        List<ImageEntryDTO> resultEntries = imageEntryService.getByTags(tagSearch);
        model.addAttribute("resultEntries", resultEntries);
        return "pages/searchResults";
    }

    @GetMapping("/updateImage/{id}")
    public String renderUpdateImage(@PathVariable long id,
                                       Model model){

        ImageEntryDTO imageEntryDTO = imageEntryMapper
                .toDTO(imageEntryRepo
                        .findById(id)
                        .orElse(new ImageEntryEntity()));

        model.addAttribute("ImageEntryDTO", imageEntryDTO);

        return "pages/editImage";
    }

    @PostMapping("/updateImage/{id}")
    public String updateImage(@PathVariable long id,
                                @Valid ImageEntryDTO imageEntryDTO,
                                BindingResult result,
                                Model model){

        if(result.hasErrors() || imageEntryService.checkReservedChars(imageEntryDTO.getTitle())){
            return renderUpdateImage(id, model);
        }

        imageEntryService.edit(imageEntryDTO, id);

        return renderImagePage(id, model);
    }

    @PostMapping("/deleteImage/{id}")
    public String deleteImage(@PathVariable long id,
                              Model model){

        ImageEntryDTO imageEntryDTO = imageEntryMapper
                .toDTO(imageEntryRepo
                        .findById(id)
                        .orElse(new ImageEntryEntity()));

        String uploadDir = "src/main/resources/static/images/";

        try {
            uploadService.deleteFile(uploadDir, imageEntryDTO.getFileName());
        } catch (IOException e) {
            model.addAttribute("status", e.getMessage());
            return "error";
        }

        imageEntryRepo
                .delete(imageEntryMapper
                        .toEntity(imageEntryDTO));

        return homeController.renderHome(model);
    }

    @PostMapping("/likeImage/{id}")
    public String likeImage(@PathVariable("id") long id,
                            Model model){
        UserEntity user = userRepo
                .findById(userService
                        .getPrincipalId());

        ImageEntryEntity image = imageEntryRepo
                .findById(id)
                .orElse(new ImageEntryEntity());

        imageEntryService.likeImage(user, image);

        return renderImagePage(id, model);
    }

}
