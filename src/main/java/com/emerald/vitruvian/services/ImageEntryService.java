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
            "neither", "just", "the", "as", "if", "then", "rather",
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
        ImageEntryEntity newImage = imageEntryMapper.toEntity(imageEntryDTO);
//        newImage.setImageId(imageEntryEntity.getImageId());
//        newImage.setUser(imageEntryEntity.getUser());
//        newImage.setFileName(imageEntryEntity.getFileName());
        imageEntryMapper.updateImageEntryEntity(oldImage, newImage);
        imageEntryRepo.save(newImage);
    }

    public List<ImageEntryDTO> getAllImages(){
        return StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false)
                .map(n -> imageEntryMapper.toDTO(n))
                .toList();
    }

    public List<ImageEntryDTO> getByTags(String tagSearch){
        List<ImageEntryDTO> resultList = new ArrayList<>();
        for(ImageEntryDTO image : getAllImages()){
            int tagCount = 0;
            String tagsNameInput =  tagSearch + " " + image.getTitle();
            String[] titleSplit = image
                    .getTitle()
                    .split(" ");

            String[] imageTags = image
                    .getTagsDTO()
                    .getTags()
                    .toArray(new String[0]);

            String[] imageTitleTags = new String[titleSplit.length + imageTags.length];
            System.arraycopy(titleSplit, 0, imageTitleTags, 0, titleSplit.length);
            System.arraycopy(imageTags, 0, imageTitleTags, titleSplit.length, imageTags.length);
            String[] searchImageTags = tagsNameInput.split(" ");
            for(String tagQuery : searchImageTags){
                for(String imageTag : imageTitleTags){
                    if(tagQuery.equals(imageTag.toLowerCase())){
                        tagCount++;
                    }
                }
            }
            if(tagCount == searchImageTags.length){
                resultList.add(image);
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
        String[] separatedDesc = imageEntryDTO.getDescription().split(" ");
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
}
