package com.emerald.vitruvian.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagsDTO {

    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag){
        if(tag != null){
            tags.add(tag);
        }
    }

    @Override
    public String toString() {
        return "TagsDTO{" +
                "tags=" + tags +
                '}';
    }
}
