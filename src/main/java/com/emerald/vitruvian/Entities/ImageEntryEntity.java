package com.emerald.vitruvian.Entities;

import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.models.TagDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Image entries")
public class ImageEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String path;

    private TagDTO tagDTO;

    //tag section

    @Column(nullable = false)
    private TagImageType tagImageType;

    //character specific tags

    @Column(name = "Character number")
    private TagCharacterNumber tagCharacterNumber;

    @Column(name = "Character pose")
    private TagCharacterPose tagCharacterPose;

    @Column(name = "Character type")
    private TagCharacterType tagCharacterType;

    @Column(name = "Character shape")
    private TagCharacterShape tagCharacterShape;

    @Column(name = "Character clothing")
    private TagCharacterClothing tagCharacterClothing;

    //scenery specific tags

    @Column(name = "Scenery nature")
    private TagSceneryNature tagSceneryNature;

    @Column(name = "Scenery structure")
    private TagSceneryStructure tagSceneryStructure;

    //neutral tags

    @Column(name = "Neutral color")
    private TagNeutralColor tagNeutralColor;

    @Column(name = "Neutral saturation")
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    @Override
    public String toString() {
        return "ImageEntryEntity{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", tagDTO=" + tagDTO +
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
