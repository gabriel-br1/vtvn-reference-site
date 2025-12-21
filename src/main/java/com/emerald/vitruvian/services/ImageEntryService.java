package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.TagsDTO;
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

    // hash set of neutral words without specific meaning to exclude when parsing image description for tags
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

    // gets all images that were not uploaded as profile images and returns them as a list
    public List<ImageEntryDTO> getAllImages(){
        return StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false)
                .filter(n -> n.getIsProfile() == 0)
                .map(n -> imageEntryMapper.toDTO(n))
                .toList();
    }

    // finds the profile picture of a user through the isProfile attribute and returns its DTO
    public ImageEntryDTO getProfilePicture(UserEntity user){
        return StreamSupport.stream(user.getImages().spliterator(), false)
                .filter(n -> n.getIsProfile() == 1)
                .findFirst()
                .map(n -> imageEntryMapper.toDTO(n))
                .orElse(new ImageEntryDTO());
    }

    // takes the search String as input and iterates over it as an array of Strings, the image tags and all images in the database by matching the image tags with the search tags
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

    // returns a space separated String of tags from the tags of an image DTO
    public String getImageTags(ImageEntryDTO imageEntryDTO){
        String[] tags = imageEntryDTO
                .getTagsDTO()
                .getTags()
                .toArray(new String[0]);
        return String.join(" ", tags);
    }

    /* splits the image description and checks each String against Strings in the filterWords hash set, adding the new
    tag from the split description to a Tags DTO, which it returns
    the method contains a loop that increases a count variable each time the potential new tag word does not equal the
    filter word, and adds the word if in the end the count equals the size of the filter words hash set */
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

    // updates the user's liked images list of image entities with a new entry
    public void likeImage(UserEntity user, ImageEntryEntity image) {
        List<ImageEntryEntity> likedImages = user.getLikedImages();
        likedImages.add(image);
        user.setLikedImages(likedImages);

        long imageId = image.getImageId();
        ImageEntryDTO imageDTO = imageEntryMapper.toDTO(image);

        edit(imageDTO, imageId);
    }

    // checks for the '*' reserved character in the title of the image
    public boolean checkReservedChars(String title){
        for(int i = 0; i < title.length(); i++){
            if(title.charAt(i) == '*'){
                return true;
            }
        }
        return false;
    }
}
