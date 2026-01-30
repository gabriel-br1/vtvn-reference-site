package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.Entities.AlgorithmProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmRepo extends CrudRepository<AlgorithmProfileEntity, Long> {
}
