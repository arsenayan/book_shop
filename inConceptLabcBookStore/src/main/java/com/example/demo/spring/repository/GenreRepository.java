package com.example.demo.spring.repository;


import com.example.demo.model.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
    List<GenreEntity> findByNameContainingIgnoreCaseOrderByName(String name);


}
