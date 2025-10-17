package com.emerald.vitruvian.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Galleries")
public class GalleryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String galleryName;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @ManyToMany
    private List<ImageEntryEntity> images;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "GalleryEntity{" +
                "id=" + id +
                ", galleryName='" + galleryName + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", user=" + user +
                '}';
    }
}
