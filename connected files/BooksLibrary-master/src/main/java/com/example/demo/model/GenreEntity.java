package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(catalog = "book_library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@EqualsAndHashCode
@Setter @Getter

public class GenreEntity
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Id
    private Long id;

     private String name;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "genre")
    private List<BookEntity> bookEntities;

}
