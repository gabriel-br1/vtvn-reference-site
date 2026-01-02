package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.repositories.UserRepo;
import com.emerald.vitruvian.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class AccountRESTController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("userPosts")
    @ResponseBody
    public List<UserEntity> getAllUsers(){
        List<UserEntity> users = StreamSupport.stream(userRepo.findAll().spliterator(), false).toList();
        return userService.formatUserEntityJSON(users);
    }

}
