package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.GalleryMapper;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.GalleryDTO;
import com.emerald.vitruvian.repositories.GalleryRepo;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepo galleryRepo;

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

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

    // adds an image from the database to a user's galleries passed in as a list of gallery names separated by a special character in the String format
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

    // removes an image from the database from a user's gallery passed in as a list of image names separated by a special character in the String format
    public void removeImage(GalleryEntity galleryEntity, String imageNames){
        String[] imageArray = parseGalleryList(imageNames);
        List<ImageEntryEntity> imagesToRemove = new ArrayList<>();
        for(String image : imageArray){
            for(ImageEntryEntity imageEntity : galleryEntity.getImages()){
                if(imageEntity.getTitle().equals(image)){
                    System.out.println("removing " + imageEntity.getTitle());
                    System.out.println();
                    ImageEntryEntity imageToRemove = StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false)
                            .filter(n -> n.getTitle().equals(image))
                            .findFirst()
                            .orElse(new ImageEntryEntity());
                    imagesToRemove.add(imageToRemove);
                }
            }
        }

        List<Integer> ids = new ArrayList<>();

        for(ImageEntryEntity imageEntry : imagesToRemove){
            for(int i = 0; i < galleryEntity.getImages().size(); i++){
                if(galleryEntity.getImages().get(i).getImageId() == imageEntry.getImageId()){
                    ids.add(i);
                }
            }
        }

        List<ImageEntryEntity> imageEntryEntityList = galleryEntity.getImages();
        boolean firstLoop = true;

        for(int i : ids){

            if(firstLoop){
                imageEntryEntityList.remove(i);
                firstLoop = false;
            } else {
                imageEntryEntityList.remove(i-1);
            }

        }

        galleryEntity.setImages(imageEntryEntityList);
        edit(galleryMapper.toDTO(galleryEntity), galleryEntity.getId());
    }

    // sets the last image for each of a user's galleries to show as their thumbnail and returns the list of the updated gallery entities
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

    // splits the String of gallery names or image names by the '*' character and returns a new String array consisting of gallery names or image names
    // in case of no '*' (no valid gallery names in the String), returns an empty String array
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

    // checks for the '*' reserved character in the title of the gallery
    public boolean checkReservedChars(String title){
        for(int i = 0; i < title.length(); i++){
            if(title.charAt(i) == '*'){
                return true;
            }
        }
        return false;
    }

    public List<GalleryEntity> formatGalleryEntityJSON(List<GalleryEntity> galleries) {
        for(GalleryEntity gallery : galleries){
            UserEntity idUser = new UserEntity();
            idUser.setId(gallery.getUser().getId());
            gallery.setUser(idUser);

            if(gallery.getLastImage() != null){
                ImageEntryEntity idLastImage = new ImageEntryEntity();
                idLastImage.setImageId(gallery.getLastImage().getImageId());
                gallery.setLastImage(idLastImage);
            } else {
                gallery.setLastImage(null);
            }

            if(gallery.getImages() != null){
                List<ImageEntryEntity> newIdImages = new ArrayList<>();
                int l = 0;
                for(ImageEntryEntity image : gallery.getImages()){
                    ImageEntryEntity newImage = new ImageEntryEntity();
                    newImage.setImageId(image.getImageId());
                    newIdImages.add(l, newImage);
                    l++;
                }
                gallery.setImages(newIdImages);
            } else {
                gallery.setImages(List.of(new ImageEntryEntity()));
            }
        }
        return galleries;
    }
}
