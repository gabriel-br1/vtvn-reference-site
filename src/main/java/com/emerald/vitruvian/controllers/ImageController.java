package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.services.ImageEntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {

    @Autowired
    private ImageEntryService imageEntryService;

    @GetMapping("/uploadCharacter")
    public String renderImageUpload(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadCharacter";
    }

    @PostMapping("/createCharacter")
    public String createCharacter(@Valid @ModelAttribute ImageEntryDTO imageEntryDTO,
                              BindingResult result,
                              Model model){

        if(result.hasErrors()){
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadCharacter";
        }

        imageEntryDTO.getTagDTO().setTagImageType("CHARACTER");
        imageEntryService.add(imageEntryDTO);
        return "pages/index";

    }

}
