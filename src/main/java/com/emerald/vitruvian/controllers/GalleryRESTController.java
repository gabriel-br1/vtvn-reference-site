package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.repositories.GalleryRepo;
import com.emerald.vitruvian.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class GalleryRESTController {

    @Autowired
    private GalleryRepo galleryRepo;

    @Autowired
    private GalleryService galleryService;

    @GetMapping("galleryPosts")
    @ResponseBody
    public List<GalleryEntity> getAllGalleries(){
        List<GalleryEntity> galleries = StreamSupport.stream(galleryRepo.findAll().spliterator(), false).toList();
        return galleryService.formatGalleryEntityJSON(galleries);
    }

}
