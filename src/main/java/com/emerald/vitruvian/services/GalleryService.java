package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.mappers.GalleryMapper;
import com.emerald.vitruvian.models.GalleryDTO;
import com.emerald.vitruvian.repositories.GalleryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepo galleryRepo;

    @Autowired
    private GalleryMapper galleryMapper;

    public void add(GalleryDTO galleryDTO){
        GalleryEntity galleryEntity = galleryMapper.toEntity(galleryDTO);
        galleryRepo.save(galleryEntity);
    }

}
