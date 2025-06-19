package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.models.TagDTO;
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
            for(String tag : tags){
                if(image.getTagCharacterClothing() != null && image.getTagCharacterClothing().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagCharacterNumber() != null && image.getTagCharacterNumber().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagCharacterPose() != null && image.getTagCharacterPose().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagCharacterShape() != null && image.getTagCharacterShape().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagCharacterType() != null && image.getTagCharacterType().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagImageType() != null && image.getTagImageType().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagNeutralColor() != null && image.getTagNeutralColor().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagNeutralSaturation() != null && image.getTagNeutralSaturation().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagSceneryNature() != null && image.getTagSceneryNature().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
                if(image.getTagSceneryStructure() != null && image.getTagSceneryStructure().name().equals(tag.toUpperCase()) && !resultList.contains(image)){
                    resultList.add(image);
                }
            }
        }
        return resultList;
    }

    public String getImageTags(ImageEntryDTO imageEntryDTO){
        TagDTO tagDto = convertTagsString(imageEntryDTO);
        String tags = "";
        if (tagDto.getTagImageType() != null){
            tags += tagDto.getTagImageType() + " ";
        }
        if (tagDto.getTagCharacterClothing() != null){
            tags += tagDto.getTagCharacterClothing() + " ";
        }
        if (tagDto.getTagCharacterNumber() != null){
            tags += tagDto.getTagCharacterNumber() + " ";
        }
        if (tagDto.getTagCharacterPose() != null){
            tags += tagDto.getTagCharacterPose() + " ";
        }
        if (tagDto.getTagCharacterShape() != null){
            tags += tagDto.getTagCharacterShape() + " ";
        }
        if (tagDto.getTagCharacterType() != null){
            tags += tagDto.getTagCharacterType() + " ";
        }
        if (tagDto.getTagNeutralColor() != null){
            tags += tagDto.getTagNeutralColor() + " ";
        }
        if (tagDto.getTagNeutralSaturation() != null){
            tags += tagDto.getTagNeutralSaturation() + " ";
        }
        if (tagDto.getTagSceneryNature() != null){
            tags += tagDto.getTagSceneryNature() + " ";
        }
        if (tagDto.getTagSceneryStructure() != null){
            tags += tagDto.getTagSceneryStructure() + " ";
        }
        return tags;
    }

    private ImageEntryDTO assignEnums(ImageEntryDTO imageEntryDTO){
        if(isCharacter(imageEntryDTO)){
            if(!imageEntryDTO.getTagDTO().getTagCharacterClothing().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterClothing().substring(9).toUpperCase();
                imageEntryDTO.setTagCharacterClothing(TagCharacterClothing.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterNumber().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterNumber().substring(10).toUpperCase();
                imageEntryDTO.setTagCharacterNumber(TagCharacterNumber.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterPose().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterPose().substring(5).toUpperCase();
                imageEntryDTO.setTagCharacterPose(TagCharacterPose.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterShape().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterShape().substring(6).toUpperCase();
                imageEntryDTO.setTagCharacterShape(TagCharacterShape.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterType().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagCharacterType().substring(5).toUpperCase();
                imageEntryDTO.setTagCharacterType(TagCharacterType.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagImageType().isEmpty()){
                imageEntryDTO.setTagImageType(TagImageType.valueOf(imageEntryDTO.getTagDTO().getTagImageType()));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralColor().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralColor().substring(6).toUpperCase();
                imageEntryDTO.setTagNeutralColor(TagNeutralColor.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralSaturation().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralSaturation().substring(11).toUpperCase();
                imageEntryDTO.setTagNeutralSaturation(TagNeutralSaturation.valueOf(specificTag));
            }
        } else {
            if(!imageEntryDTO.getTagDTO().getTagNeutralColor().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralColor().substring(6).toUpperCase();
                imageEntryDTO.setTagNeutralColor(TagNeutralColor.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralSaturation().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagNeutralSaturation().substring(11).toUpperCase();
                imageEntryDTO.setTagNeutralSaturation(TagNeutralSaturation.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagSceneryNature().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagSceneryNature().substring(7).toUpperCase();
                imageEntryDTO.setTagSceneryNature(TagSceneryNature.valueOf(specificTag));
            }
            if(!imageEntryDTO.getTagDTO().getTagSceneryStructure().isEmpty()){
                String specificTag = imageEntryDTO.getTagDTO().getTagSceneryStructure().substring(10).toUpperCase();
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

    private TagDTO convertTagsString(ImageEntryDTO imageEntryDTO){
        TagDTO tagDTO = new TagDTO();
        if(imageEntryDTO.getTagCharacterClothing() != null){
            tagDTO.setTagCharacterClothing(imageEntryDTO.getTagCharacterClothing().name().toLowerCase());
        }
        if(imageEntryDTO.getTagCharacterNumber() != null){
            tagDTO.setTagCharacterNumber(imageEntryDTO.getTagCharacterNumber().name().toLowerCase());
        }
        if(imageEntryDTO.getTagCharacterPose() != null){
            tagDTO.setTagCharacterPose(imageEntryDTO.getTagCharacterPose().name().toLowerCase());
        }
        if(imageEntryDTO.getTagCharacterShape() != null){
            tagDTO.setTagCharacterShape(imageEntryDTO.getTagCharacterShape().name().toLowerCase());
        }
        if(imageEntryDTO.getTagCharacterType() != null){
            tagDTO.setTagCharacterType(imageEntryDTO.getTagCharacterType().name().toLowerCase());
        }
        if(imageEntryDTO.getTagImageType() != null){
            tagDTO.setTagImageType(imageEntryDTO.getTagImageType().name().toLowerCase());
        }
        if(imageEntryDTO.getTagNeutralColor() != null){
            tagDTO.setTagNeutralColor(imageEntryDTO.getTagNeutralColor().name().toLowerCase());
        }
        if(imageEntryDTO.getTagNeutralSaturation() != null){
            tagDTO.setTagNeutralSaturation(imageEntryDTO.getTagNeutralSaturation().name().toLowerCase());
        }
        if(imageEntryDTO.getTagSceneryNature() != null){
            tagDTO.setTagSceneryNature(imageEntryDTO.getTagSceneryNature().name().toLowerCase());
        }
        if(imageEntryDTO.getTagSceneryStructure() != null){
            tagDTO.setTagSceneryStructure(imageEntryDTO.getTagSceneryStructure().name().toLowerCase());
        }
        return tagDTO;
    }

}
