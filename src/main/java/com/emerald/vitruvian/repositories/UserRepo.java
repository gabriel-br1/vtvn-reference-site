package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    UserEntity findById(long id);
}
