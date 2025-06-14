package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

}
