package com.emerald.vitruvian.Entities;

import com.emerald.vitruvian.models.TagsDTO;
import jakarta.persistence.*;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.YesNoConverter;

import java.util.List;

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

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity user;

    @ManyToMany(mappedBy = "likedImages")
    private List<UserEntity> likedBy;

    @ManyToMany(mappedBy = "images")
    private List<GalleryEntity> galleries;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int isProfile;

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

    public List<UserEntity> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<UserEntity> likedBy) {
        this.likedBy = likedBy;
    }

    public int getIsProfile() {
        return isProfile;
    }

    public void setIsProfile(int isProfile) {
        this.isProfile = isProfile;
    }

    public List<GalleryEntity> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<GalleryEntity> galleries) {
        this.galleries = galleries;
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
                ", likedBy=" + likedBy +
                ", galleries=" + galleries +
                ", isProfile=" + isProfile +
                '}';
    }
}
