package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.enums.*;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Component
public class ImageEntryDTO {

    private long imageId;

    @NotBlank(message = "Fill in the title")
    @Size(max = 50, message = "Title is too long")
    private String title;

    private String imageName;

    private String imageType;

    private byte[] imageData;

    private TagDTO tagDTO;

    private UserEntity user;

    //tag section

    private TagImageType tagImageType;

    //character specific tags

    private TagCharacterNumber tagCharacterNumber;

    private TagCharacterPose tagCharacterPose;

    private TagCharacterType tagCharacterType;

    private TagCharacterShape tagCharacterShape;

    private TagCharacterClothing tagCharacterClothing;

    //scenery specific tags

    private TagSceneryNature tagSceneryNature;

    private TagSceneryStructure tagSceneryStructure;

    //neutral tags

    private TagNeutralColor tagNeutralColor;

    private TagNeutralSaturation tagNeutralSaturation;




    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TagDTO getTagDTO() {
        return tagDTO;
    }

    public void setTagDTO(TagDTO tagDTO) {
        this.tagDTO = tagDTO;
    }

    public TagImageType getTagImageType() {
        return tagImageType;
    }

    public void setTagImageType(TagImageType tagImageType) {
        this.tagImageType = tagImageType;
    }

    public TagCharacterNumber getTagCharacterNumber() {
        return tagCharacterNumber;
    }

    public void setTagCharacterNumber(TagCharacterNumber tagCharacterNumber) {
        this.tagCharacterNumber = tagCharacterNumber;
    }

    public TagCharacterPose getTagCharacterPose() {
        return tagCharacterPose;
    }

    public void setTagCharacterPose(TagCharacterPose tagCharacterPose) {
        this.tagCharacterPose = tagCharacterPose;
    }

    public TagCharacterType getTagCharacterType() {
        return tagCharacterType;
    }

    public void setTagCharacterType(TagCharacterType tagCharacterType) {
        this.tagCharacterType = tagCharacterType;
    }

    public TagCharacterShape getTagCharacterShape() {
        return tagCharacterShape;
    }

    public void setTagCharacterShape(TagCharacterShape tagCharacterShape) {
        this.tagCharacterShape = tagCharacterShape;
    }

    public TagCharacterClothing getTagCharacterClothing() {
        return tagCharacterClothing;
    }

    public void setTagCharacterClothing(TagCharacterClothing tagCharacterClothing) {
        this.tagCharacterClothing = tagCharacterClothing;
    }

    public TagSceneryNature getTagSceneryNature() {
        return tagSceneryNature;
    }

    public void setTagSceneryNature(TagSceneryNature tagSceneryNature) {
        this.tagSceneryNature = tagSceneryNature;
    }

    public TagSceneryStructure getTagSceneryStructure() {
        return tagSceneryStructure;
    }

    public void setTagSceneryStructure(TagSceneryStructure tagSceneryStructure) {
        this.tagSceneryStructure = tagSceneryStructure;
    }

    public TagNeutralColor getTagNeutralColor() {
        return tagNeutralColor;
    }

    public void setTagNeutralColor(TagNeutralColor tagNeutralColor) {
        this.tagNeutralColor = tagNeutralColor;
    }

    public TagNeutralSaturation getTagNeutralSaturation() {
        return tagNeutralSaturation;
    }

    public void setTagNeutralSaturation(TagNeutralSaturation tagNeutralSaturation) {
        this.tagNeutralSaturation = tagNeutralSaturation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "ImageEntryDTO{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                ", tagDTO=" + tagDTO +
                ", user=" + user +
                ", tagImageType=" + tagImageType +
                ", tagCharacterNumber=" + tagCharacterNumber +
                ", tagCharacterPose=" + tagCharacterPose +
                ", tagCharacterType=" + tagCharacterType +
                ", tagCharacterShape=" + tagCharacterShape +
                ", tagCharacterClothing=" + tagCharacterClothing +
                ", tagSceneryNature=" + tagSceneryNature +
                ", tagSceneryStructure=" + tagSceneryStructure +
                ", tagNeutralColor=" + tagNeutralColor +
                ", tagNeutralSaturation=" + tagNeutralSaturation +
                '}';
    }
}
