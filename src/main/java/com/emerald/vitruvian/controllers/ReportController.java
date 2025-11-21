package com.emerald.vitruvian.controllers;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.models.ReportDTO;
import com.emerald.vitruvian.repositories.ImageEntryRepo;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.ReportService;
import com.emerald.vitruvian.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageEntryRepo imageEntryRepo;

    @Autowired
    private ImageController imageController;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/report/{id}")
    public String renderReport(@PathVariable("id") long id,
                               Model model){
        model.addAttribute("ReportDTO", new ReportDTO());
        model.addAttribute("pathId", id);
        return "pages/reportForm";
    }

    @PostMapping("/report/{id}")
    public String postReport(@Valid @ModelAttribute ReportDTO report,
                             BindingResult result,
                             @PathVariable long id,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("ReportDTO", report);
            return "pages/reportForm";
        }

        ImageEntryEntity image = imageEntryRepo
                .findById(id)
                .orElse(new ImageEntryEntity());

        UserEntity user = userRepo
                .findById(userService
                        .getPrincipalId());

        reportService.add(report, image, user);

        return imageController.renderImagePage(id, model);
    }

}
