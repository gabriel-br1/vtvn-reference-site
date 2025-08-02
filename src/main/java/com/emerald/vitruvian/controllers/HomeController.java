package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.mappers.ImageEntryMapper;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.services.ImageEntryService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ImageEntryService imageEntryService;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageEntryMapper imageEntryMapper;

    @GetMapping("/")
    public String renderHome(Model model){
        List<ImageEntryDTO> imageEntries = imageEntryService.getAllImages();
        model.addAttribute("imageEntries", imageEntries);
        return "pages/index";
    }

    //converts images from blob to viewable images on the home page
    @GetMapping("/home/getter/{id}")
    public void showEntryImage(@PathVariable("id") long id,
                               HttpServletResponse response){
        response.setContentType("image/jpeg");
        ImageEntryDTO imageEntry = imageEntryMapper.toDTO(imageEntryRepo.findById(id).orElse(new ImageEntryEntity()));
        InputStream inputStream = new ByteArrayInputStream(imageEntry.getImageData());
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
