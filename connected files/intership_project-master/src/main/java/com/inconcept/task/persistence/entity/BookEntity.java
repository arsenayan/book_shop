package com.inconcept.task.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class
BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Book_title", nullable = false)
    private String title;

    @Column(name = "Year_Of_Publication")
    private String publishDate;

    @ManyToOne
    @JoinColumn(name = "Publisher_Id", referencedColumnName = "Id")
    private PublisherEntity publisher;

    @Column(name = "Pic_Url")
    private String picUrl;

    @OneToMany(mappedBy = "book", targetEntity = RateEntity.class, fetch = FetchType.LAZY)
    private List<RateEntity> listBookRates;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "authorbook",
            joinColumns = @JoinColumn(name = "Book_Id"),
            inverseJoinColumns = @JoinColumn(name = "Author_Id"))
    private List<AuthorEntity> bookAuthors = new ArrayList<>();


    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public List<RateEntity> getListBookRates() {
        return listBookRates;
    }

    public void setListBookRates(List<RateEntity> listBookRates) {
        this.listBookRates = listBookRates;
    }

    public List<AuthorEntity> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<AuthorEntity> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}
