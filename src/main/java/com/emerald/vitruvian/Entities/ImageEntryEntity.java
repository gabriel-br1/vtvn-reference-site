package com.emerald.vitruvian.Entities;

import com.emerald.vitruvian.models.TagsDTO;
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
    private String fileName;

    @Transient
    private TagsDTO tagsDTO;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String tags;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity user;

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
