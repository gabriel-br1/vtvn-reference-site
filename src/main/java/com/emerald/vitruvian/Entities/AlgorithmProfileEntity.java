package com.emerald.vitruvian.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class AlgorithmProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private UserEntity user;

    @Column(columnDefinition = "LONGTEXT")
    private String tagsByFrequency;

    @Column
    private List<Long> imagesInAlgorithm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTagsByFrequency() {
        return tagsByFrequency;
    }

    public void setTagsByFrequency(String tagsByFrequency) {
        this.tagsByFrequency = tagsByFrequency;
    }

    public List<Long> getImagesInAlgorithm() {
        return imagesInAlgorithm;
    }

    public void setImagesInAlgorithm(List<Long> imagesInAlgorithm) {
        this.imagesInAlgorithm = imagesInAlgorithm;
    }

    @Override
    public String toString() {
        return "AlgorithmProfileEntity{" +
                "id=" + id +
                ", user=" + user +
                ", tagsByFrequency='" + tagsByFrequency + '\'' +
                ", imagesInAlgorithm=" + imagesInAlgorithm +
                '}';
    }
}
