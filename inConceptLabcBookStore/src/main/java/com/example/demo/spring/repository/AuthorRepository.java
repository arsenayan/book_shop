package com.example.demo.spring.repository;


import com.example.demo.model.entities.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {

    List<AuthorEntity> findByFioContainingIgnoreCaseOrderByFio(String fio);
    Page<AuthorEntity> findByFioContainingIgnoreCaseOrderByFio(String fio, Pageable pageable);

}
