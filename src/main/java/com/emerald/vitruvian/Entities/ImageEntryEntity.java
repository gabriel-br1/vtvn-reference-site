package com.emerald.vitruvian.Entities;

import com.emerald.vitruvian.enums.*;
import com.emerald.vitruvian.models.TagDTO;
import com.emerald.vitruvian.models.TagsDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Arrays;

@Entity
@Table(name = "Image entries")
public class ImageEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String fileName;

    @Transient
    private TagsDTO tagsDTO;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String tags;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Transient
//    private TagDTO tagDTO;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity user;

//    //tag section
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private TagImageType tagImageType;
//
//    //character specific tags
//
//    @Column(name = "Character number")
//    @Enumerated(EnumType.STRING)
//    private TagCharacterNumber tagCharacterNumber;
//
//    @Column(name = "Character pose")
//    @Enumerated(EnumType.STRING)
//    private TagCharacterPose tagCharacterPose;
//
//    @Column(name = "Character type")
//    @Enumerated(EnumType.STRING)
//    private TagCharacterType tagCharacterType;
//
//    @Column(name = "Character shape")
//    @Enumerated(EnumType.STRING)
//    private TagCharacterShape tagCharacterShape;
//
//    @Column(name = "Character clothing")
//    @Enumerated(EnumType.STRING)
//    private TagCharacterClothing tagCharacterClothing;
//
//    //scenery specific tags
//
//    @Column(name = "Scenery nature")
//    @Enumerated(EnumType.STRING)
//    private TagSceneryNature tagSceneryNature;
//
//    @Column(name = "Scenery structure")
//    @Enumerated(EnumType.STRING)
//    private TagSceneryStructure tagSceneryStructure;
//
//    //neutral tags
//
//    @Column(name = "Neutral color")
//    @Enumerated(EnumType.STRING)
//    private TagNeutralColor tagNeutralColor;
//
//    @Column(name = "Neutral saturation")
//    @Enumerated(EnumType.STRING)
//    private TagNeutralSaturation tagNeutralSaturation;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TagsDTO getTagsDTO() {
        return tagsDTO;
    }

    public void setTagsDTO(TagsDTO tagsDTO) {
        this.tagsDTO = tagsDTO;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ImageEntryEntity{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                ", fileName='" + fileName + '\'' +
                ", tagsDTO=" + tagsDTO +
                ", tags='" + tags + '\'' +
                ", user=" + user +
                '}';
    }
}
