package com.emerald.vitruvian.Entities;

import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.models.TagDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Entity
@Table(name = "Image entries")
public class ImageEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Column(nullable = false)
    private String title;

    @Transient
    private String path;

    @Transient
    private MultipartFile image;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageType;

    @Column(nullable = false)
    private byte[] imageData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Transient
    private TagDTO tagDTO;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity user;

    //tag section

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TagImageType tagImageType;

    //character specific tags

    @Column(name = "Character number")
    @Enumerated(EnumType.STRING)
    private TagCharacterNumber tagCharacterNumber;

    @Column(name = "Character pose")
    @Enumerated(EnumType.STRING)
    private TagCharacterPose tagCharacterPose;

    @Column(name = "Character type")
    @Enumerated(EnumType.STRING)
    private TagCharacterType tagCharacterType;

    @Column(name = "Character shape")
    @Enumerated(EnumType.STRING)
    private TagCharacterShape tagCharacterShape;

    @Column(name = "Character clothing")
    @Enumerated(EnumType.STRING)
    private TagCharacterClothing tagCharacterClothing;

    //scenery specific tags

    @Column(name = "Scenery nature")
    @Enumerated(EnumType.STRING)
    private TagSceneryNature tagSceneryNature;

    @Column(name = "Scenery structure")
    @Enumerated(EnumType.STRING)
    private TagSceneryStructure tagSceneryStructure;

    //neutral tags

    @Column(name = "Neutral color")
    @Enumerated(EnumType.STRING)
    private TagNeutralColor tagNeutralColor;

    @Column(name = "Neutral saturation")
    @Enumerated(EnumType.STRING)
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
        return "ImageEntryEntity{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", image=" + image +
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
