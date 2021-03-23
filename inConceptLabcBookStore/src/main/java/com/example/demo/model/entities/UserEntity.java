package com.example.demo.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(catalog = "book_library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@EqualsAndHashCode
@Setter
@Getter
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "full_name")
    private String fullNameOfUser;

    @Column(name = "age")
    private Integer ageOfUser;

    private String email;

    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToOne
    @JoinColumn(name = "posthal_code", referencedColumnName = "postal_code")
    private LocationEntity locationEntity;


}
