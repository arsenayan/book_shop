package com.inconcept.task.persistence.repository;

import com.inconcept.task.persistence.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    PublisherEntity findOneByFio(String fio);

    boolean existsPublisherEntitiesByFio(String fio);
}
