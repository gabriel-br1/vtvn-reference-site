package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.AlgorithmService;
import com.emerald.vitruvian.services.FileUploadService;
import com.emerald.vitruvian.services.ImageEntryService;
import com.emerald.vitruvian.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private AlgorithmService algorithmService;

    @GetMapping("/")
    public String renderHome(Model model){
        UserEntity user = userRepo.findById(userService.getPrincipalId());
        if(user != null){
            if(user.getAlgorithmProfile() != null && user.getAlgorithmProfile().getTagsByFrequency() != null){
                List<ImageEntryDTO> imageEntriesAlgo = algorithmService.imagesAlgorithmOrder(user, imageEntryService.getAllImages());
                model.addAttribute("imageEntries", imageEntriesAlgo);
            } else {
                List<ImageEntryDTO> imageEntries = imageEntryService.getAllImages();
                model.addAttribute("imageEntries", imageEntries);
            }
        } else {
            List<ImageEntryDTO> imageEntries = imageEntryService.getAllImages();
            model.addAttribute("imageEntries", imageEntries);
        }
        return "pages/index";
    }

}
