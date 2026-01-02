package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.CommentEntity;
import com.emerald.vitruvian.Entities.GalleryEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService{

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    // adds user to the database after checking that the password matches the confirm password and that the email address is not a duplicate
    public void add(UserDTO userDTO) throws PasswordsDoNotMatchException, DuplicateEmailException{
        if(confirmPassword(userDTO)){
            if(confirmEmail(userDTO)){
                userDTO.setPassword(encoder.encode(userDTO.getPassword()));
                userRepo.save(userMapper.toEntity(userDTO));
                return;
            }
            System.out.println("email");
            throw new DuplicateEmailException("An account under this email address already exists.");
        }
        System.out.println("password");
        throw new PasswordsDoNotMatchException("Passwords do not match.");
    }

    public void edit(UserDTO user, long id){
        UserEntity oldUser = userRepo.findById(id);
        UserEntity newUser = userMapper.toEntity(user);
        userMapper.updateUserEntity(oldUser, newUser);
        userRepo.save(newUser);
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

    // returns the file name of the last image that was liked by the user to be used as the liked images gallery thumbnail
    public String lastLikedImage(UserEntity user){
        if(user.getLikedImages().isEmpty()){
            return "placeholder.jpg";
        } else {
            return user.getLikedImages().getLast().getFileName();
        }
    }

    public List<UserEntity> formatUserEntityJSON(List<UserEntity> userEntityList){
        for(UserEntity user : userEntityList){
            if(user.getLikedImages() != null){
                List<ImageEntryEntity> newIdLiked = new ArrayList<>();
                int i = 0;
                for(ImageEntryEntity image : user.getLikedImages()){
                    ImageEntryEntity newImage = new ImageEntryEntity();
                    newImage.setImageId(image.getImageId());
                    newIdLiked.add(i, newImage);
                    i++;
                }
                user.setLikedImages(newIdLiked);
            } else {
                user.setLikedImages(List.of(new ImageEntryEntity()));
            }

            if(user.getGalleries() != null){
                List<GalleryEntity> newIdGalleries = new ArrayList<>();
                int j = 0;
                for(GalleryEntity gallery : user.getGalleries()){
                    GalleryEntity newGallery = new GalleryEntity();
                    newGallery.setId(gallery.getId());
                    newIdGalleries.add(j, newGallery);
                    j++;
                }
                user.setGalleries(newIdGalleries);
            } else {
                user.setGalleries(List.of(new GalleryEntity()));
            }

            if(user.getComments() != null){
                List<CommentEntity> newIdComments = new ArrayList<>();
                int k = 0;
                for(CommentEntity comment : user.getComments()){
                    CommentEntity newComment = new CommentEntity();
                    newComment.setId(comment.getId());
                    newIdComments.add(k, newComment);
                    k++;
                }
                user.setComments(newIdComments);
            } else {
                user.setComments(List.of(new CommentEntity()));
            }

            if(user.getImages() != null){
                List<ImageEntryEntity> newIdImages = new ArrayList<>();
                int l = 0;
                for(ImageEntryEntity image : user.getImages()){
                    ImageEntryEntity newImage = new ImageEntryEntity();
                    newImage.setImageId(image.getImageId());
                    newIdImages.add(l, newImage);
                    l++;
                }
                user.setImages(newIdImages);
            } else {
                user.setImages(List.of(new ImageEntryEntity()));
            }
        }
        return userEntityList;
    }

}
