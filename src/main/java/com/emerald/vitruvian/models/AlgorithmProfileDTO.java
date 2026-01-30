package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AlgorithmProfileDTO {

    private UserEntity user;

    private String tagsByFrequency;

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
        return "AlgorithmProfileDTO{" +
                "user=" + user +
                ", tagsByFrequency=" + tagsByFrequency +
                '}';
    }
}
