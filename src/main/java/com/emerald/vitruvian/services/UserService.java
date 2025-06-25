package com.emerald.vitruvian.services;

import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    public boolean add(UserDTO userDTO){
        if(confirmPassword(userDTO)){
            userRepo.save(userMapper.toEntity(userDTO));
            return true;
        }
        return false;
    }

    private boolean confirmPassword(UserDTO userDTO){
        return userDTO.getPassword().equals(userDTO.getPasswordConfirm());
    }

}
