package com.emerald.vitruvian.models;

import org.springframework.stereotype.Component;

@Component
public class TagDTO {

    private String tagCharacterClothing;

    private String tagCharacterNumber;

    private String tagCharacterPose;

    private String tagCharacterShape;

    private String tagCharacterType;

    private String tagImageType;

    private String tagNeutralColor;

    private String tagNeutralSaturation;

    private String tagSceneryNature;

    private String tagSceneryStructure;

    public String getTagCharacterClothing() {
        return tagCharacterClothing;
    }

    public void setTagCharacterClothing(String tagCharacterClothing) {
        this.tagCharacterClothing = tagCharacterClothing;
    }

    public String getTagCharacterNumber() {
        return tagCharacterNumber;
    }

    public void setTagCharacterNumber(String tagCharacterNumber) {
        this.tagCharacterNumber = tagCharacterNumber;
    }

    public String getTagCharacterPose() {
        return tagCharacterPose;
    }

    public void setTagCharacterPose(String tagCharacterPose) {
        this.tagCharacterPose = tagCharacterPose;
    }

    public String getTagCharacterShape() {
        return tagCharacterShape;
    }

    public void setTagCharacterShape(String tagCharacterShape) {
        this.tagCharacterShape = tagCharacterShape;
    }

    public String getTagCharacterType() {
        return tagCharacterType;
    }

    public void setTagCharacterType(String tagCharacterType) {
        this.tagCharacterType = tagCharacterType;
    }

    public String getTagImageType() {
        return tagImageType;
    }

    public void setTagImageType(String tagImageType) {
        this.tagImageType = tagImageType;
    }

    public String getTagNeutralColor() {
        return tagNeutralColor;
    }

    public void setTagNeutralColor(String tagNeutralColor) {
        this.tagNeutralColor = tagNeutralColor;
    }

    public String getTagNeutralSaturation() {
        return tagNeutralSaturation;
    }

    public void setTagNeutralSaturation(String tagNeutralSaturation) {
        this.tagNeutralSaturation = tagNeutralSaturation;
    }

    public String getTagSceneryNature() {
        return tagSceneryNature;
    }

    public void setTagSceneryNature(String tagSceneryNature) {
        this.tagSceneryNature = tagSceneryNature;
    }

    public String getTagSceneryStructure() {
        return tagSceneryStructure;
    }

    public void setTagSceneryStructure(String tagSceneryStructure) {
        this.tagSceneryStructure = tagSceneryStructure;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "tagCharacterClothing='" + tagCharacterClothing + '\'' +
                ", tagCharacterNumber='" + tagCharacterNumber + '\'' +
                ", tagCharacterPose='" + tagCharacterPose + '\'' +
                ", tagCharacterShape='" + tagCharacterShape + '\'' +
                ", tagCharacterType='" + tagCharacterType + '\'' +
                ", tagImageType='" + tagImageType + '\'' +
                ", tagNeutralColor='" + tagNeutralColor + '\'' +
                ", tagNeutralSaturation='" + tagNeutralSaturation + '\'' +
                ", tagSceneryNature='" + tagSceneryNature + '\'' +
                ", tagSceneryStructure='" + tagSceneryStructure + '\'' +
                '}';
    }
}
