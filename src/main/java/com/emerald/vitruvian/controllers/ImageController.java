package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.ImageEntryService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/uploadCharacter")
    public String renderCharacterUpload(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadCharacter";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/uploadScenery")
    public String renderSceneryUpload(Model model){
        model.addAttribute("ImageEntryDTO", new ImageEntryDTO());
        return "pages/uploadScenery";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/createCharacter")
    public String createCharacter(@Valid @ModelAttribute ImageEntryDTO imageEntryDTO,
                              BindingResult result,
                              Model model){

        if(result.hasErrors()){
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadCharacter";
        }

        UserEntity user = userRepo.findById(userService.getPrincipalId());

        imageEntryDTO.getTagDTO().setTagImageType("CHARACTER");
        imageEntryService.add(imageEntryDTO, user);
        return "pages/uploadSuccess";

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/createScenery")
    public String createScenery(@Valid @ModelAttribute ImageEntryDTO imageEntryDTO,
                                  BindingResult result,
                                  Model model){

        if(result.hasErrors()){
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadScenery";
        }

        UserEntity user = userRepo.findById(userService.getPrincipalId());

        imageEntryDTO.getTagDTO().setTagImageType("SCENERY");
        imageEntryService.add(imageEntryDTO, user);
        return "pages/uploadSuccess";

    }

    @GetMapping("/image/{id}")
    public String renderImagePage(@PathVariable long id,
            Model model){

        UserDTO user = userMapper.toDTO(userRepo.findById(userService.getPrincipalId()));
        if (user != null){
            model.addAttribute("userId", user.getId());
        } else {
            model.addAttribute("userId", -1);
        }

        ImageEntryDTO imageEntryDTO = imageEntryService.getById(id);
        model.addAttribute("imageUserId", imageEntryDTO.getUser());

        model.addAttribute("TagImageType", imageEntryService.getTagImageType(imageEntryDTO));

        String tags = imageEntryService.getImageTags(imageEntryDTO);
        model.addAttribute("path", imageEntryDTO.getPath());
        model.addAttribute("title", imageEntryDTO.getTitle());
        model.addAttribute("imageTags", tags);

        System.out.println(user.getId());
        System.out.println(imageEntryDTO.getUser());

        return "pages/image";
    }

    @GetMapping("/results")
    public String renderSearchResults(String tagSearch,
                                      Model model){
        List<ImageEntryDTO> resultEntries = imageEntryService.getByTags(tagSearch);
        model.addAttribute("resultEntries", resultEntries);
        return "pages/searchResults";
    }

}
