package com.emerald.vitruvian.Entities;

import jakarta.persistence.*;

import java.util.HashMap;
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

    @Override
    public String toString() {
        return "AlgorithmEntity{" +
                "id=" + id +
                ", user=" + user +
                ", tagsByFrequency=" + tagsByFrequency +
                '}';
    }
}
