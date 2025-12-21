package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.services.FileUploadService;
import com.emerald.vitruvian.services.ImageEntryService;
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
    private FileUploadService fileUploadService;

    @GetMapping("/")
    public String renderHome(Model model){
        List<ImageEntryDTO> imageEntries = imageEntryService.getAllImages();
        model.addAttribute("imageEntries", imageEntries);
        return "pages/index";
    }

}
