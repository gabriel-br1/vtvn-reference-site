package com.emerald.vitruvian.controllers;

import ch.qos.logback.core.util.StringUtil;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.FileUploadService;
import com.emerald.vitruvian.services.ImageEntryService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileUploadService uploadService;

    @GetMapping("/script.js")
    public ResponseEntity<Resource> serveJs(){
        Resource resource = new ClassPathResource("static/script.js");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("application/javascript"))
                .body(resource);
    }

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
                              @RequestParam("image") MultipartFile image,
                              BindingResult result,
                              Model model){

        System.out.println(image.getOriginalFilename());

        if(result.hasErrors()){
            model.addAttribute("ImageEntryDTO", imageEntryDTO);
            return "pages/uploadCharacter";
        }

        if(!image.isEmpty()){
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            imageEntryDTO.setFileName(fileName);

            UserEntity user = userRepo.findById(userService.getPrincipalId());
            imageEntryDTO.setUser(user);
            imageEntryDTO.getTagDTO().setTagImageType("CHARACTER");

            ImageEntryEntity savedImage = imageEntryService.add(imageEntryDTO);
            String uploadDir = "src/main/resources/static/images/" + savedImage.getImageId();

            try {
                uploadService.uploadFile(uploadDir, fileName, image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return "pages/uploadSuccess";
        }
        return "error";
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
        imageEntryService.add(imageEntryDTO);
        return "pages/uploadSuccess";

    }

    @GetMapping("/image/{id}")
    public String renderImagePage(@PathVariable("id") long id,
            Model model){

        ImageEntryEntity imageEntryEntity = imageEntryRepo.findById(id).orElse(new ImageEntryEntity());
        ImageEntryDTO imageEntryDTO = imageEntryMapper.toDTO(imageEntryEntity);

        UserEntity userEntity = userRepo.findById(userService.getPrincipalId());
        if (userEntity != null){
            if (userEntity.getId() == imageEntryEntity.getUser().getId()){
                model.addAttribute("TagImageType", imageEntryService.getTagImageType(imageEntryDTO));
                model.addAttribute("ImageEntryDTO", imageEntryDTO);
                returnImageDetails(imageEntryDTO, model);
                return "pages/imagePrincipal";
            }
        }

        returnImageDetails(imageEntryDTO, model);

        return "pages/image";
    }

    @GetMapping("/results")
    public String renderSearchResults(String tagSearch,
                                      Model model){
        List<ImageEntryDTO> resultEntries = imageEntryService.getByTags(tagSearch);
        model.addAttribute("resultEntries", resultEntries);
        return "pages/searchResults";
    }

    @GetMapping("/updateCharacter/{id}")
    public String renderUpdateCharacter(@PathVariable long id,
                                       Model model){

        ImageEntryDTO imageEntryDTO = imageEntryMapper.toDTO(imageEntryRepo.findById(id).orElse(new ImageEntryEntity()));
        imageEntryDTO.setTagDTO(imageEntryService.assignTagDTO(imageEntryDTO));

        model.addAttribute("ImageEntryDTO", imageEntryDTO);
        model.addAttribute("TagDTO", imageEntryDTO.getTagDTO());

        return "pages/editCharacter";
    }

    @GetMapping("/updateScenery/{id}")
    public String renderUpdateScenery(@PathVariable long id,
                                      Model model){

        ImageEntryDTO imageEntryDTO = imageEntryMapper.toDTO(imageEntryRepo.findById(id).orElse(new ImageEntryEntity()));
        imageEntryDTO.setTagDTO(imageEntryService.assignTagDTO(imageEntryDTO));

        model.addAttribute("ImageEntryDTO", imageEntryDTO);
        model.addAttribute("TagDTO", imageEntryDTO.getTagDTO());

        return "pages/editScenery";
    }

    @PostMapping("/updateCharacter/{id}")
    public String editCharacter(@PathVariable long id,
                                @Valid ImageEntryDTO imageEntryDTO,
                                BindingResult result,
                                Model model){

        if(result.hasErrors()){
            return renderUpdateScenery(id, model);
        }

        imageEntryDTO.getTagDTO().setTagImageType("CHARACTER");
        imageEntryService.edit(imageEntryDTO, id);

        return renderImagePage(id, model);
    }

    @PostMapping("/updateScenery/{id}")
    public String editScenery(@PathVariable long id,
                              @Valid ImageEntryDTO imageEntryDTO,
                              BindingResult result,
                              Model model){

        if(result.hasErrors()){
            return renderUpdateScenery(id, model);
        }

        imageEntryDTO.getTagDTO().setTagImageType("SCENERY");
        imageEntryService.edit(imageEntryDTO, id);

        return renderImagePage(id, model);
    }

    private void returnImageDetails(ImageEntryDTO imageEntryDTO,
                                    Model model){
        String tags = imageEntryService.getImageTags(imageEntryDTO);
//        model.addAttribute("path", imageEntryDTO.getPath());
        model.addAttribute("title", imageEntryDTO.getTitle());
        model.addAttribute("imageTags", tags);
    }

}
