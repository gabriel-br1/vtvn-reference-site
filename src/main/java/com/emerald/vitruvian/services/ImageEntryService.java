package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.TagDTO;
import com.emerald.vitruvian.models.TagsDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ImageEntryService {

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    public void add(ImageEntryDTO imageEntryDTO){
        assignEnums(imageEntryDTO);
        ImageEntryEntity newImage = imageEntryMapper.toEntity(imageEntryDTO);
        imageEntryRepo.save(newImage);
    }

    public List<ImageEntryDTO> getAllImages(){
        return StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false)
                .map(n -> imageEntryMapper.toDTO(n))
                .toList();
    }

    public ImageEntryDTO getById(long id){
        ImageEntryEntity imageEntryEntity =  StreamSupport.stream(imageEntryRepo.findAll().spliterator(), false)
                .filter(n -> n.getImageId() == id)
                .findFirst()
                .orElse(new ImageEntryEntity());
        return imageEntryMapper.toDTO(imageEntryEntity);
    }

    public List<ImageEntryDTO> getByTags(String[] tags){
        List<ImageEntryDTO> resultList = new ArrayList<>();
        for(ImageEntryDTO image : getAllImages()){
            int tagCount = 0;
            for(String tagQuery : tags){
                for(String imageTag : convertTagsString(image).getTags()){
                    if(tagQuery.toUpperCase().equals(imageTag)){
                        tagCount++;
                    }
                }
            }
            if(tagCount == tags.length){
                resultList.add(image);
            }
        }
        return resultList;
    }

    public String getImageTags(ImageEntryDTO imageEntryDTO){
        TagsDTO tagsDTO = convertTagsString(imageEntryDTO);
        String tags = "";
        for(String tag : tagsDTO.getTags()){
            tags += tag.toLowerCase() + " ";
        }
        return tags;
    }

    private ImageEntryDTO assignEnums(ImageEntryDTO imageEntryDTO){
        if(isCharacter(imageEntryDTO)){
            if(!imageEntryDTO.getTagDTO().getTagCharacterClothing().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterClothing().toUpperCase();
                imageEntryDTO.setTagCharacterClothing(TagCharacterClothing.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterNumber().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterNumber().toUpperCase();
                imageEntryDTO.setTagCharacterNumber(TagCharacterNumber.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterPose().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterPose().toUpperCase();
                imageEntryDTO.setTagCharacterPose(TagCharacterPose.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterShape().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterShape().toUpperCase();
                imageEntryDTO.setTagCharacterShape(TagCharacterShape.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterType().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterType().toUpperCase();
                imageEntryDTO.setTagCharacterType(TagCharacterType.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagImageType().isEmpty()){
                imageEntryDTO.setTagImageType(TagImageType.valueOf(imageEntryDTO.getTagDTO().getTagImageType()));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralColor().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralColor().toUpperCase();
                imageEntryDTO.setTagNeutralColor(TagNeutralColor.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralSaturation().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralSaturation().toUpperCase();
                imageEntryDTO.setTagNeutralSaturation(TagNeutralSaturation.valueOf(specificTag));
            }
        } else {
            if(!imageEntryDTO.getTagDTO().getTagNeutralColor().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralColor().toUpperCase();
                imageEntryDTO.setTagNeutralColor(TagNeutralColor.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralSaturation().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralSaturation().toUpperCase();
                imageEntryDTO.setTagNeutralSaturation(TagNeutralSaturation.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagSceneryNature().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagSceneryNature().toUpperCase();
                imageEntryDTO.setTagSceneryNature(TagSceneryNature.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagSceneryStructure().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagSceneryStructure().toUpperCase();
                imageEntryDTO.setTagSceneryStructure(TagSceneryStructure.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagImageType().isEmpty()){
                imageEntryDTO.setTagImageType(TagImageType.valueOf(imageEntryDTO.getTagDTO().getTagImageType()));
            }
        }

        return imageEntryDTO;
    }

    private boolean isCharacter(ImageEntryDTO imageEntryDTO){
        return imageEntryDTO.getTagDTO().getTagImageType().equals("CHARACTER");
    }

    private TagsDTO convertTagsString(ImageEntryDTO imageEntryDTO){
        TagsDTO tags = new TagsDTO();
        if(imageEntryDTO.getTagCharacterClothing() != null){
            tags.addTag(imageEntryDTO.getTagCharacterClothing().name());
        }
        if(imageEntryDTO.getTagCharacterNumber() != null){
            tags.addTag(imageEntryDTO.getTagCharacterNumber().name());
        }
        if(imageEntryDTO.getTagCharacterPose() != null){
            tags.addTag(imageEntryDTO.getTagCharacterPose().name());
        }
        if(imageEntryDTO.getTagCharacterShape() != null){
            tags.addTag(imageEntryDTO.getTagCharacterShape().name());
        }
        if(imageEntryDTO.getTagCharacterType() != null){
            tags.addTag(imageEntryDTO.getTagCharacterType().name());
        }
        if(imageEntryDTO.getTagImageType() != null){
            tags.addTag(imageEntryDTO.getTagImageType().name());
        }
        if(imageEntryDTO.getTagNeutralColor() != null){
            tags.addTag(imageEntryDTO.getTagNeutralColor().name());
        }
        if(imageEntryDTO.getTagNeutralSaturation() != null){
            tags.addTag(imageEntryDTO.getTagNeutralSaturation().name());
        }
        if(imageEntryDTO.getTagSceneryNature() != null){
            tags.addTag(imageEntryDTO.getTagSceneryNature().name());
        }
        if(imageEntryDTO.getTagSceneryStructure() != null){
            tags.addTag(imageEntryDTO.getTagSceneryStructure().name());
        }
        return tags;
    }

}
