package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class CommentDTO {

    @NotBlank(message = "Invalid input: Empty comment")
    private String commentText;

    private UserEntity user;

    private ImageEntryEntity image;

    private long imageId;

    private String profileImagePath;

    private String profileName;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ImageEntryEntity getImage() {
        return image;
    }

    public void setImage(ImageEntryEntity image) {
        this.image = image;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentText='" + commentText + '\'' +
                ", user=" + user +
                ", image=" + image +
                ", imageId=" + imageId +
                ", profileImage=" + profileImagePath +
                ", profileName='" + profileName + '\'' +
                '}';
    }
}
