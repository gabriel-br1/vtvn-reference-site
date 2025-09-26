package com.emerald.vitruvian.repositories;

import com.emerald.vitruvian.Entities.ReportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends CrudRepository<ReportEntity, Long> {
}
