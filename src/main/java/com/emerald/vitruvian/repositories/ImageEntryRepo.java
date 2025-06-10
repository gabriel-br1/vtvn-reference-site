package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEntryRepo extends CrudRepository<ImageEntryEntity, Long>{
}
