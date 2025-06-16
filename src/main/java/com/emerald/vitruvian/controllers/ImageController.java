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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {

    @Autowired
    private ImageEntryService imageEntryService;

    @GetMapping("/uploadCharacter")
    public String renderCharacterUpload(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadCharacter";
    }

    @GetMapping("/uploadScenery")
    public String renderSceneryUpload(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadScenery";
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
        return "pages/uploadSuccess";

    }

    @PostMapping("/createScenery")
    public String createScenery(@Valid @ModelAttribute ImageEntryDTO imageEntryDTO,
                                  BindingResult result,
                                  Model model){

        if(result.hasErrors()){
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadScenery";
        }

        imageEntryDTO.getTagDTO().setTagImageType("SCENERY");
        imageEntryService.add(imageEntryDTO);
        return "pages/uploadSuccess";

    }

    @GetMapping("/image/{id}")
    public String renderImagePage(@PathVariable long id,
            Model model){
        ImageEntryDTO imageEntryDTO = imageEntryService.getById(id);
        String tags = imageEntryService.getImageTags(imageEntryDTO);
        model.addAttribute("path", imageEntryDTO.getPath());
        model.addAttribute("title", imageEntryDTO.getTitle());
        model.addAttribute("imageTags", tags);
        return "pages/image";
    }

}
