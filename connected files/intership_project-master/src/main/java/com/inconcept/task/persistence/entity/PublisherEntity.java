package com.inconcept.task.persistence.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher")
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Full_Name")
    private String fio;

    @OneToMany(mappedBy = "publisher", targetEntity = BookEntity.class, fetch = FetchType.LAZY)
    private List<BookEntity> entityList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<BookEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<BookEntity> entityList) {
        this.entityList = entityList;
    }
}
