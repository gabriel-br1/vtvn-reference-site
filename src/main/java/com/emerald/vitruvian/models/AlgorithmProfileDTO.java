package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.UserEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AlgorithmProfileDTO {

    private UserEntity user;

    private String tagsByFrequency;

    private List<Long> imagesInAlgorithm;

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
        return "AlgorithmProfileDTO{" +
                "user=" + user +
                ", tagsByFrequency='" + tagsByFrequency + '\'' +
                ", imagesInAlgorithm=" + imagesInAlgorithm +
                '}';
    }
}
