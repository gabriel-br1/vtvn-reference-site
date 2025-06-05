package com.emerald.vitruvian.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @GetMapping("/uploadCharacter")
    public String renderImageUpload(){
        return "pages/uploadCharacter";
    }

}
