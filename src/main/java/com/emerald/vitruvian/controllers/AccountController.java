package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.exceptions.DuplicateEmailException;
import com.emerald.vitruvian.exceptions.PasswordsDoNotMatchException;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.UserService;
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
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String renderLogin(){
        return "pages/login";
    }

    @GetMapping("/register")
    public String renderRegister(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "pages/register";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/{id}")
    public String renderUserPage(@PathVariable long id,
            Model model){
        UserEntity user = userRepo.findById(userService.getPrincipalId());
        if(user.getId() == id){
            userMapper.toDTO(user);
            model.addAttribute("user", user);
            return "pages/user";
        }
        return "error";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("userDTO", userDTO);
            return "pages/register";
        }

        try {
            userService.add(userDTO);
        } catch (Exception e){
            userDTO.setPasswordConfirm("");
            model.addAttribute("userDTO", userDTO);
            return "pages/register";
        }

        return "pages/registerSuccess";
    }

}
