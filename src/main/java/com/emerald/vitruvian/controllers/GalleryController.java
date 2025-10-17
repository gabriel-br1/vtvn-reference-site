package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.models.GalleryDTO;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.GalleryService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GalleryController {

    @Autowired
    private AccountController accountController;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/profile/addGallery")
    public String renderCreateGallery(Model model){
        model.addAttribute("GalleryDTO", new GalleryDTO());
        return "pages/addGallery";
    }

    @PostMapping("/profile/addGallery")
    public String createGallery(@Valid @ModelAttribute GalleryDTO galleryDTO,
                                Model model,
                                BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("GalleryDTO", galleryDTO);
            return "pages/addGallery";
        }
        UserEntity user = userRepo.findById(userService.getPrincipalId());
        galleryDTO.setUser(user);
        galleryService.add(galleryDTO);

        return accountController.renderProfile(model);
    }

}
