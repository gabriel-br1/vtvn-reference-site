package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.TagsDTO;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ImageEntryService {

    private static HashSet<String> filterWords = new HashSet<>(Arrays.asList(
            "and", "for", "nor", "but", "or", "yet", "so",
            "both", "or", "whether", "only", "also", "either",
            "neither", "just", "the", "a", "as", "if", "then", "rather",
            "such", "that", "even", "is", "are"));

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    public ImageEntryEntity add(ImageEntryDTO imageEntryDTO){
        imageEntryDTO.setTagsDTO(parseTags(imageEntryDTO));
        imageEntryDTO.setTags(getImageTags(imageEntryDTO));
        ImageEntryEntity newImage = imageEntryMapper.toEntity(imageEntryDTO);
        imageEntryRepo.save(newImage);
        return newImage;
    }

    public void edit(ImageEntryDTO imageEntryDTO, long id){
        ImageEntryEntity oldImage = imageEntryRepo.findById(id).orElse(new ImageEntryEntity());
        if(imageEntryDTO.getDescription() != null){
            imageEntryDTO.setTagsDTO(parseTags(imageEntryDTO));
            imageEntryDTO.setTags(getImageTags(imageEntryDTO));
        }
        oldImage.setTagsDTO(imageEntryDTO.getTagsDTO());
        oldImage.setTags(imageEntryDTO.getTags());
        ImageEntryEntity newImage = new ImageEntryEntity();
        imageEntryMapper.updateImageEntryEntity(oldImage, newImage);
        imageEntryRepo.save(newImage);
    }

    public List<ImageEntryDTO> getAllImages(){
        return StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false)
                .filter(n -> n.getIsProfile() == 0)
                .map(n -> imageEntryMapper.toDTO(n))
                .toList();
    }

    public ImageEntryDTO getProfilePicture(UserEntity user){
        return StreamSupport.stream(user.getImages().spliterator(), false)
                .filter(n -> n.getIsProfile() == 1)
                .findFirst()
                .map(n -> imageEntryMapper.toDTO(n))
                .orElse(new ImageEntryDTO());
    }

    public List<ImageEntryDTO> getByTags(String tagSearch){
        List<ImageEntryDTO> resultList = new ArrayList<>();
        String[] splitSearchTags = tagSearch.split(" ");
        for(ImageEntryDTO image : getAllImages()){
            String[] imageTags = image.getTags().split(" ");
            for(String imageTag : imageTags){
                for(String searchTag : splitSearchTags){
                    if(searchTag.equalsIgnoreCase(imageTag) && !resultList.contains(image)){
                        resultList.add(image);
                        break;
                    }
                }
            }
        }
        return resultList;
    }

    public String getImageTags(ImageEntryDTO imageEntryDTO){
        String[] tags = imageEntryDTO
                .getTagsDTO()
                .getTags()
                .toArray(new String[0]);
        return String.join(" ", tags);
    }

    private TagsDTO parseTags(ImageEntryDTO imageEntryDTO){
        TagsDTO tags = new TagsDTO();
        String descriptionPlusTitle = imageEntryDTO.getDescription() + " " + imageEntryDTO.getTitle();
        String[] separatedDesc = descriptionPlusTitle.split(" ");
        int count = 0;
        for(String word : separatedDesc){
            for(String filterWord : filterWords){
                if(!filterWord.equalsIgnoreCase(word)){
                    count++;
                }
            }
            if(count == filterWords.size()){
                tags.addTag(word);
            }
            count = 0;
        }

        return tags;
    }

    public void likeImage(UserEntity user, ImageEntryEntity image) {
        List<ImageEntryEntity> likedImages = user.getLikedImages();
        likedImages.add(image);
        user.setLikedImages(likedImages);

        long imageId = image.getImageId();
        ImageEntryDTO imageDTO = imageEntryMapper.toDTO(image);

        edit(imageDTO, imageId);
    }

    public boolean checkReservedChars(String title){
        for(int i = 0; i < title.length(); i++){
            if(title.charAt(i) == '*'){
                return true;
            }
        }
        return false;
    }
}
