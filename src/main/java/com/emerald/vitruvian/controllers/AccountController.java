package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class AccountController {

    @Autowired
    private HomeController homeController;

    @Autowired
    private ImageController imageController;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private FileUploadService uploadService;

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/login")
    public String renderLogin(){
        return "pages/login";
    }

    @GetMapping("/register")
    public String renderRegister(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "pages/register";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/loginSuccess")
    public String renderLoginSuccess(Model model){
        UserDTO user = userMapper.toDTO(userRepo.findById(userService.getPrincipalId()));
        model.addAttribute("UserDTO", user);
        return "pages/loginSuccess";
    }

    @PostMapping("/createToken")
    public String createToken(@ModelAttribute UserDTO user,
                              Model model){
        String token = jwtService.generateToken(user.getEmail());
        return homeController.renderHome(model);
    }

//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
//    @GetMapping("/user/{id}")
//    public String renderUserPage(@PathVariable long id,
//            Model model){
//        UserEntity user = userRepo.findById(userService.getPrincipalId());
//        if(user.getId() == id){
//            userMapper.toDTO(user);
//            model.addAttribute("user", user);
//            return "pages/user";
//        }
//        return "error";
//    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("userDTO", userDTO);
            return "pages/register";
        }

        try {
            userService.add(userDTO);
        } catch (Exception e){
            System.out.println(e.getMessage());
            userDTO.setPasswordConfirm("");
            model.addAttribute("userDTO", userDTO);
            return "pages/register";
        }

        return "pages/registerSuccess";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/renderProfile")
    public String renderProfile(Model model){
        UserEntity user = userRepo.findById(userService.getPrincipalId());
        ImageEntryDTO profilePicture = imageEntryService.getProfilePicture(user);
        UserDTO userdto = userMapper.toDTO(user);
        List<GalleryEntity> galleries = galleryService.lastImage(user);
        model.addAttribute("lastLikedImage", userService.lastLikedImage(user));
        model.addAttribute("user", userdto);
        model.addAttribute("imagePath", profilePicture.getFileName());
        model.addAttribute("Galleries", galleries);

        return "pages/user";
    }

    @GetMapping("/profile/upload")
    public String renderUploadPfp(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadProfilePicture";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/profile/liked")
    public String renderLiked(Model model){
        UserEntity user = userRepo.findById(userService.getPrincipalId());
        userMapper.toDTO(user);
        model.addAttribute("user", user);
        model.addAttribute("imageEntries", user.getLikedImages());

        return "pages/gallery";
    }

    @PostMapping("/profile/upload")
    public String uploadPfp(@Valid @ModelAttribute ImageEntryDTO imageEntryDTO,
                            @RequestParam("image") MultipartFile image,
                            BindingResult result,
                            Model model){

        if(result.hasErrors()){
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadProfilePicture";
        }

        if(!image.isEmpty()){
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            imageEntryDTO.setFileName(fileName);

            UserEntity user = userRepo
                    .findById(userService
                            .getPrincipalId());
            imageEntryDTO.setUser(user);

            ImageEntryDTO profilePicture = imageEntryService.getProfilePicture(user);

            if(!profilePicture.getTitle().isEmpty()){
                imageController.deleteImage(profilePicture.getImageId(), model);
            }

            imageEntryDTO.setIsProfile(1);

            String uploadDir = "src/main/resources/static/images/";

            try {
                uploadService.uploadFile(uploadDir, fileName, image);
            } catch (IOException e) {
                model.addAttribute("status", e.getMessage());
                return "error";
            }

            ImageEntryEntity savedImage = imageEntryService.add(imageEntryDTO);

        } else {
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadProfilePicture";
        }
        return "pages/uploadSuccess";
    }

}
