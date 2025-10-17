package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.Entities.GalleryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepo extends CrudRepository<GalleryEntity, Long> {
}
