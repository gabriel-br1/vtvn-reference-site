package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDTO {

    private long id;

    @NotBlank(message = "fill in your email address")
    private String email;

    @NotBlank(message = "fill in your password")
    @Size(message = "password must be between 6 and 24 characters")
    @Min(value = 6)
    @Max(value = 24)
    private String password;

    @NotBlank(message = "fill in your password confirmation")
    private String passwordConfirm;

    private List<ImageEntryEntity> likedImages;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ImageEntryEntity> getLikedImages() {
        return likedImages;
    }

    public void setLikedImages(List<ImageEntryEntity> likedImages) {
        this.likedImages = likedImages;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", likedImages=" + likedImages +
                '}';
    }
}
