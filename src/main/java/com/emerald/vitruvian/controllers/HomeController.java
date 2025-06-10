package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.services.ImageEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ImageEntryService imageEntryService;

    @GetMapping("/")
    public String renderHome(Model model){
        model.addAttribute("imageEntries", imageEntryService.getAllImages());
        return "pages/index";
    }

}
