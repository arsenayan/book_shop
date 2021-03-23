package com.example.demo.model.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
@Setter
@Getter
public class PublisherEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private Integer contactNumber;

    private Integer postalCode;


    @ManyToMany
    @JoinTable(
            name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    List<BookEntity> bookEntities ;

    public PublisherEntity() {
    }
}
