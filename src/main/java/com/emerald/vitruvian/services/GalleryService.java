package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.GalleryMapper;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.GalleryDTO;
import com.emerald.vitruvian.repositories.GalleryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepo galleryRepo;

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public void add(UserEntity user, GalleryDTO galleryDTO){
        GalleryEntity galleryEntity = galleryMapper.toEntity(galleryDTO);
        galleryRepo.save(galleryEntity);
        List<GalleryEntity> galleries = user.getGalleries();
        galleries.add(galleryEntity);
        user.setGalleries(galleries);
        userService.edit(userMapper.toDTO(user), user.getId());
    }

    public void edit(GalleryDTO galleryDTO, long id){
        GalleryEntity oldGallery = galleryRepo.findById(id).orElse(new GalleryEntity());
        oldGallery.setGalleryName(galleryDTO.getGalleryName());
        oldGallery.setDescription(galleryDTO.getDescription());
        GalleryEntity newGallery = new GalleryEntity();

        galleryMapper.updateGalleryEntity(oldGallery, newGallery);
        galleryRepo.save(newGallery);
    }

    public void addImage(ImageEntryEntity imageEntry, String galleryList){
        String[] galleryArray = parseGalleryList(galleryList);
        for(String gallery : galleryArray){
            for(GalleryEntity galleryEntity : galleryRepo.findAll()){
                if(gallery.equals(galleryEntity.getGalleryName())){
                    System.out.println("old size" + galleryEntity.getImages().size());
                    galleryEntity.getImages().add(imageEntry);
                    System.out.println("new size" + galleryEntity.getImages().size());
                    edit(galleryMapper.toDTO(galleryEntity), galleryEntity.getId());
                }
            }
        }
    }

    public List<GalleryEntity> lastImage(UserEntity user){
        List<GalleryEntity> galleries = user.getGalleries();

        for(GalleryEntity gallery : galleries){
            if(gallery.getImages().isEmpty()){
                ImageEntryEntity image = new ImageEntryEntity();
                image.setImageId(0);
                image.setFileName("placeholder.jpg");
                gallery.setLastImage(image);
            } else {
                List<ImageEntryEntity> images = gallery.getImages();
                gallery.setLastImage(images.getLast());
            }
        }

        return galleries;
    }

    private String[] parseGalleryList(String galleryList){
        for(int i = 0; i < galleryList.length(); i++){
            if(galleryList.charAt(i) != '*'){
                String trimmedList = galleryList.substring(i);
                return trimmedList.split("\\*");
            }
        }
        return new String[0];
    }

    public List<GalleryEntity> getUserGalleries(UserEntity user){
        return StreamSupport.stream(galleryRepo.findAll().spliterator(), false)
                .filter(n -> n.getUser().getId() == user.getId())
                .toList();
    }

}
