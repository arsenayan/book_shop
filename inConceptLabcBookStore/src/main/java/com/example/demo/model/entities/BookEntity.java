package com.example.demo.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(catalog = "book_library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@EqualsAndHashCode
@Setter
@Getter
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "title")
    private String titleOfBook;

    @Column(name = "image")
    private Integer idOfImage;


    @Column(name = "description")
    private String descriptionOfBook;


    @ManyToMany(mappedBy = "book")
    List<AuthorEntity> authorEntities ;

    @ManyToMany(mappedBy = "book")
    List<GenreEntity> genreEntities;

    @ManyToMany(mappedBy = "book")
    List<PublisherEntity> publisherEntities;

 }
