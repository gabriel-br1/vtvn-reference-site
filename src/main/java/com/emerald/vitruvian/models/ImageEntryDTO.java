package com.emerald.vitruvian.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class ImageEntryDTO {

    private long imageId;

    @NotBlank(message = "Fill in the title")
    @Size(max = 50, message = "Title is too long")
    private String title;

    @NotBlank(message = "Fill in the path")
    private String path;

}
