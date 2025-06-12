package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ImageEntryService {

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    @Autowired
    private TagService tagService;

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

    private ImageEntryDTO assignEnums(ImageEntryDTO imageEntryDTO){
        if(isCharacter(imageEntryDTO)){
            if(!imageEntryDTO.getTagDTO().getTagCharacterClothing().isEmpty()){
                imageEntryDTO.setTagCharacterClothing(tagService.convertEnumClothing(imageEntryDTO.getTagDTO().getTagCharacterClothing()));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterNumber().isEmpty()){
                imageEntryDTO.setTagCharacterNumber(tagService.convertEnumNumber(imageEntryDTO.getTagDTO().getTagCharacterNumber()));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterPose().isEmpty()){
                imageEntryDTO.setTagCharacterPose(tagService.convertEnumPose(imageEntryDTO.getTagDTO().getTagCharacterPose()));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterShape().isEmpty()){
                imageEntryDTO.setTagCharacterShape(tagService.convertEnumShape(imageEntryDTO.getTagDTO().getTagCharacterShape()));
            }
            if(!imageEntryDTO.getTagDTO().getTagCharacterType().isEmpty()){
                imageEntryDTO.setTagCharacterType(tagService.convertEnumType(imageEntryDTO.getTagDTO().getTagCharacterType()));
            }
            if(!imageEntryDTO.getTagDTO().getTagImageType().isEmpty()){
                imageEntryDTO.setTagImageType(tagService.convertEnumImageType(imageEntryDTO.getTagDTO().getTagImageType()));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralColor().isEmpty()){
                imageEntryDTO.setTagNeutralColor(tagService.convertEnumColor(imageEntryDTO.getTagDTO().getTagNeutralColor()));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralSaturation().isEmpty()){
                imageEntryDTO.setTagNeutralSaturation(tagService.convertEnumSaturation(imageEntryDTO.getTagDTO().getTagNeutralSaturation()));
            }
        } else {
            if(!imageEntryDTO.getTagDTO().getTagNeutralColor().isEmpty()){
                imageEntryDTO.setTagNeutralColor(tagService.convertEnumColor(imageEntryDTO.getTagDTO().getTagNeutralColor()));
            }
            if(!imageEntryDTO.getTagDTO().getTagNeutralSaturation().isEmpty()){
                imageEntryDTO.setTagNeutralSaturation(tagService.convertEnumSaturation(imageEntryDTO.getTagDTO().getTagNeutralSaturation()));
            }
            if(!imageEntryDTO.getTagDTO().getTagSceneryNature().isEmpty()){
                imageEntryDTO.setTagSceneryNature(tagService.convertEnumNature(imageEntryDTO.getTagDTO().getTagSceneryNature()));
            }
            if(!imageEntryDTO.getTagDTO().getTagSceneryStructure().isEmpty()){
                imageEntryDTO.setTagSceneryStructure(tagService.convertEnumStructure(imageEntryDTO.getTagDTO().getTagSceneryStructure()));
            }
            if(!imageEntryDTO.getTagDTO().getTagImageType().isEmpty()){
                imageEntryDTO.setTagImageType(tagService.convertEnumImageType(imageEntryDTO.getTagDTO().getTagImageType()));
            }
        }

        return imageEntryDTO;
    }

    private boolean isCharacter(ImageEntryDTO imageEntryDTO){
        return imageEntryDTO.getTagDTO().getTagImageType().equals("CHARACTER");
    }

}
