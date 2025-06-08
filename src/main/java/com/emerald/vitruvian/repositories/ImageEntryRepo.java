package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.models.ImageEntryDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEntryRepo extends CrudRepository<ImageEntryDTO, Long>{
}
