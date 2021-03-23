package com.inconcept.task.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Full_Name", nullable = false)
    private String fio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfio() {
        return fio;
    }

    public void setfio(String fio) {
        this.fio = fio;
    }
}
