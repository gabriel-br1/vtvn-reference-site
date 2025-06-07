package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.models.ImageEntryDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {

    @GetMapping("/uploadCharacter")
    public String renderImageUpload(){
        return "pages/uploadCharacter";
    }

//    @PostMapping("/createCharacter")
//    public String createCharacter(@Valid @ModelAttribute ImageEntryDTO,
//                              BindingResult result,
//                              Model model){
//
//    }

}
