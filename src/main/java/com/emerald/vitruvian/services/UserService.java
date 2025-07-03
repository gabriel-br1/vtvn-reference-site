package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.exceptions.DuplicateEmailException;
import com.emerald.vitruvian.exceptions.EmailNotFoundException;
import com.emerald.vitruvian.exceptions.PasswordsDoNotMatchException;
import com.emerald.vitruvian.mappers.UserMapper;
import com.emerald.vitruvian.models.UserDTO;
import com.emerald.vitruvian.models.UserPrincipal;
import com.emerald.vitruvian.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService{

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    public void add(UserDTO userDTO) throws PasswordsDoNotMatchException, DuplicateEmailException{
        if(confirmPassword(userDTO)){
            if(confirmEmail(userDTO)){
                userDTO.setPassword(encoder.encode(userDTO.getPassword()));
                userRepo.save(userMapper.toEntity(userDTO));
            }
            System.out.println("email");
            throw new DuplicateEmailException("An account under this email address already exists.");
        }
        System.out.println("password");
        throw new PasswordsDoNotMatchException("Passwords do not match.");
    }

    private boolean confirmPassword(UserDTO userDTO){
        return userDTO.getPassword().equals(userDTO.getPasswordConfirm());
    }

    private boolean confirmEmail(UserDTO userDTO){
        List<UserDTO> users = getAll();
        for(UserDTO user : users){
            if(userDTO.getEmail().equals(user.getEmail())){
                return false;
            }
        }
        return true;
    }

    private List<UserDTO> getAll(){
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("User not found."));
        return new UserPrincipal(user);
    }

    public long getPrincipalId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            return principal.getUser().getId();
        }
        return -1;
    }
}
