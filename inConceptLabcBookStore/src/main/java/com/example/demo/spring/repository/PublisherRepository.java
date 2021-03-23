package com.example.demo.spring.repository;


import com.example.demo.model.entities.PublisherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    @Query("SELECT b from PublisherEntity b ")
    Page<PublisherEntity> findByNameContainingIgnoreCaseOrderByName(String s, Pageable pageable);
}
