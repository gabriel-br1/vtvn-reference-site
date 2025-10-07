package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ImageEntryDTO {

    private long imageId;

    @NotBlank(message = "Fill in the title")
    @Size(max = 50, message = "Title is too long")
    private String title;

    private String fileName;

    private TagsDTO tagsDTO;

    private String tags;

    @NotBlank(message = "Fill in the description")
    private String description;

    private UserEntity user;

    private List<UserEntity> likedBy;

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

    public TagsDTO getTagsDTO() {
        return tagsDTO;
    }

    public void setTagsDTO(TagsDTO tagsDTO) {
        this.tagsDTO = tagsDTO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "ImageEntryDTO{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                ", fileName='" + fileName + '\'' +
                ", tagsDTO=" + tagsDTO +
                ", tags='" + tags + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", likedBy=" + likedBy +
                ", isProfile=" + isProfile +
                '}';
    }
}
