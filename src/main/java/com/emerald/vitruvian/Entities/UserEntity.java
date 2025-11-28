package com.emerald.vitruvian.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user")
    private List<ImageEntryEntity> images;

    @ManyToMany
    private List<ImageEntryEntity> likedImages;

    @OneToMany(mappedBy = "user")
    private List<GalleryEntity> galleries;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<ImageEntryEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntryEntity> images) {
        this.images = images;
    }

    public List<ImageEntryEntity> getLikedImages() {
        return likedImages;
    }

    public void setLikedImages(List<ImageEntryEntity> likedImages) {
        this.likedImages = likedImages;
    }

    public List<GalleryEntity> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<GalleryEntity> galleries) {
        this.galleries = galleries;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", images=" + images +
                ", likedImages=" + likedImages +
                ", galleries=" + galleries +
                ", comments=" + comments +
                '}';
    }
}
