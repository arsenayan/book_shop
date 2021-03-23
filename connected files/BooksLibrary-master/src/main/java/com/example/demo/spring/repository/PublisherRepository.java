package com.example.demo.spring.repository;

import com.example.demo.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    List<PublisherEntity> findByNameContainingIgnoreCaseOrderByName(String name);
}
