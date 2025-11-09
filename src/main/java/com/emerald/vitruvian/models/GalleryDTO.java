package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GalleryDTO {

    @NotBlank(message = "Please fill in the gallery name.")
    private String galleryName;

    @NotBlank(message = "Please fill in the gallery description.")
    private String description;

    private List<ImageEntryEntity> images;

    private UserEntity user;

    private ImageEntryEntity lastImage;

    private long newImageId;

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public List<ImageEntryEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntryEntity> images) {
        this.images = images;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageEntryEntity getLastImage() {
        return lastImage;
    }

    public void setLastImage(ImageEntryEntity lastImage) {
        this.lastImage = lastImage;
    }

    public long getNewImageId() {
        return newImageId;
    }

    public void setNewImageId(long newImageId) {
        this.newImageId = newImageId;
    }

    @Override
    public String toString() {
        return "GalleryDTO{" +
                "galleryName='" + galleryName + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", user=" + user +
                ", lastImage=" + lastImage +
                ", newImageId=" + newImageId +
                '}';
    }
}
