package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String renderLogin(){
        return "pages/login";
    }

    @GetMapping("/register")
    public String renderRegister(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "pages/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("userDTO", userDTO);
            return "pages/register";
        }

        boolean registrationSuccess = userService.add(userDTO);

        if(!registrationSuccess){
            userDTO.setPasswordConfirm("");
            model.addAttribute("userDTO", userDTO);
            return "pages/register";
        }

        return "pages/registerSuccess";
    }

}
