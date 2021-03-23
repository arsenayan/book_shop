package com.inconcept.task.persistence.repository;

import com.inconcept.task.persistence.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @Query("select u from AuthorEntity u")
    Page<AuthorEntity> findAllWithPage(Pageable pageable);
}
