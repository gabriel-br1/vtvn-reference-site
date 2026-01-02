package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.services.ImageEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class ImageRESTController {

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryService imageEntryService;

    @GetMapping("imagePosts")
    @ResponseBody
    public List<ImageEntryEntity> getAllImages(){
        List<ImageEntryEntity> images = StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false).toList();
        return imageEntryService.formatImageEntityJSON(images);
    }

}
