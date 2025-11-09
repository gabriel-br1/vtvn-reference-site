package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.GalleryMapper;
import com.emerald.vitruvian.models.GalleryDTO;
import com.emerald.vitruvian.repositories.GalleryRepo;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.GalleryService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GalleryController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private HomeController homeController;

    @Autowired
    private GalleryRepo galleryRepo;

    @Autowired
    private GalleryMapper galleryMapper;

    @GetMapping("/profile/addGallery")
    public String renderCreateGallery(Model model){
        model.addAttribute("GalleryDTO", new GalleryDTO());
        return "pages/addGallery";
    }

    @GetMapping("/profile/Gallery/{galleryId}")
    public String renderGallery(@PathVariable("galleryId") long galleryId,
            Model model){
        GalleryEntity gallery = galleryRepo.findById(galleryId).orElse(new GalleryEntity());
        List<ImageEntryEntity> images = gallery.getImages();

        model.addAttribute("imageEntries", images);
        model.addAttribute("galleryId", galleryId);
        return "pages/gallery";
    }

    @GetMapping("/profile/addToGallery/{imageId}")
    public String renderAddToGallery(@PathVariable("imageId") long imageId,
                                Model model){
        UserEntity user = userRepo.findById(userService.getPrincipalId());
        List<GalleryEntity> galleries = galleryService.lastImage(user);

        model.addAttribute("Galleries", galleries);
        model.addAttribute("imageId", imageId);

        return "pages/addToGallery";
    }

    @GetMapping("/profile/editGallery/{galleryId}")
    public String renderUpdateGallery(@PathVariable("galleryId") long galleryId,
                                    Model model){
        GalleryDTO gallery = galleryMapper
                .toDTO(galleryRepo
                        .findById(galleryId)
                        .orElse(new GalleryEntity()));

        model.addAttribute("GalleryDTO", gallery);
        model.addAttribute("galleryId", galleryId);

        return "pages/editGallery";
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
        galleryService.add(user, galleryDTO);

        return homeController.renderHome(model);
    }

    @PostMapping("/profile/addToGallery/")
    public String addToGallery(@RequestParam long imageId,
                               @RequestParam String galleryNames){

        ImageEntryEntity image = imageEntryRepo
                .findById(imageId)
                .orElse(new ImageEntryEntity());

        galleryService.addImage(image, galleryNames);
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println(galleryNames);
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXX");

        return "pages/uploadSuccess";
    }

    @PostMapping("/profile/editGallery/{galleryId}")
    public String updateGallery(@ModelAttribute GalleryDTO galleryDTO,
                                @PathVariable("galleryId") long galleryId,
                                Model model,
                                BindingResult result){
        if(result.hasErrors()){
            model.addAttribute(galleryDTO);
            return "pages/editGallery";
        }

        galleryService.edit(galleryDTO, galleryId);

        return homeController.renderHome(model);
    }

    @PostMapping("/profile/deleteGallery/{galleryId}")
    public String deleteGallery(@PathVariable("galleryId") long galleryId,
                                Model model){
        galleryRepo
                .delete(galleryRepo
                        .findById(galleryId)
                        .orElse(new GalleryEntity()));

        return homeController.renderHome(model);
    }

}
