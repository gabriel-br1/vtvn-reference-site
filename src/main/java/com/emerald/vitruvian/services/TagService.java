package com.emerald.vitruvian.services;

import com.emerald.vitruvian.enums.*;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    public TagCharacterNumber convertEnumNumber(String characterNumber){
        return TagCharacterNumber.valueOf(characterNumber);
    }

    public TagCharacterClothing convertEnumClothing(String characterClothing){
        return TagCharacterClothing.valueOf(characterClothing);
    }

    public TagCharacterPose convertEnumPose(String characterPose){
        return TagCharacterPose.valueOf(characterPose);
    }

    public TagCharacterShape convertEnumShape(String characterShape){
        return TagCharacterShape.valueOf(characterShape);
    }

    public TagCharacterType convertEnumType(String characterType){
        return TagCharacterType.valueOf(characterType);
    }

    public TagImageType convertEnumImageType(String imageType){
        return TagImageType.valueOf(imageType);
    }

    public TagNeutralColor convertEnumColor(String neutralColor){
        return TagNeutralColor.valueOf(neutralColor);
    }

    public TagNeutralSaturation convertEnumSaturation(String neutralSaturation){
        return TagNeutralSaturation.valueOf(neutralSaturation);
    }

    public TagSceneryNature convertEnumNature(String sceneryNature){
        return TagSceneryNature.valueOf(sceneryNature);
    }

    public TagSceneryStructure convertEnumStructure(String sceneryStructure){
        return TagSceneryStructure.valueOf(sceneryStructure);
    }

}
