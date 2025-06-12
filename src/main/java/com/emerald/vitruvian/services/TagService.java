package com.emerald.vitruvian.services;

import com.emerald.vitruvian.enums.*;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    public TagCharacterNumber convertEnumNumber(String characterNumber){
        String specificTag = characterNumber.substring(10).toUpperCase();
        return TagCharacterNumber.valueOf(specificTag);
    }

    public TagCharacterClothing convertEnumClothing(String characterClothing){
        String specificTag = characterClothing.substring(9).toUpperCase();
        return TagCharacterClothing.valueOf(specificTag);
    }

    public TagCharacterPose convertEnumPose(String characterPose){
        String specificTag = characterPose.substring(5).toUpperCase();
        return TagCharacterPose.valueOf(specificTag);
    }

    public TagCharacterShape convertEnumShape(String characterShape){
        String specificTag = characterShape.substring(6).toUpperCase();
        return TagCharacterShape.valueOf(specificTag);
    }

    public TagCharacterType convertEnumType(String characterType){
        String specificTag = characterType.substring(5).toUpperCase();
        return TagCharacterType.valueOf(specificTag);
    }

    public TagImageType convertEnumImageType(String imageType){
        return TagImageType.valueOf(imageType);
    }

    public TagNeutralColor convertEnumColor(String neutralColor){
        String specificTag = neutralColor.substring(6).toUpperCase();
        return TagNeutralColor.valueOf(specificTag);
    }

    public TagNeutralSaturation convertEnumSaturation(String neutralSaturation){
        String specificTag = neutralSaturation.substring(11).toUpperCase();
        return TagNeutralSaturation.valueOf(specificTag);
    }

    public TagSceneryNature convertEnumNature(String sceneryNature){
        String specificTag = sceneryNature.substring(7).toUpperCase();
        return TagSceneryNature.valueOf(specificTag);
    }

    public TagSceneryStructure convertEnumStructure(String sceneryStructure){
        String specificTag = sceneryStructure.substring(10).toUpperCase();
        return TagSceneryStructure.valueOf(specificTag);
    }

}
