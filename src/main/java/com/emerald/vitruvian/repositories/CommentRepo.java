package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.Entities.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<CommentEntity, Long>{
}
