package com.inconcept.task.persistence.repository;

import com.inconcept.task.persistence.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    LocationEntity findOneByCity(String city);
}
