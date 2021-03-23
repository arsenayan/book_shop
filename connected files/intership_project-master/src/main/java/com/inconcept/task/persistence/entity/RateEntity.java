package com.inconcept.task.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "bookratings")
public class RateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Book_Id", referencedColumnName = "Id")
    private BookEntity book;


    @ManyToOne
    @JoinColumn(name = "User_Id", referencedColumnName = "Id")
    private UserEntity user;

    @Column(name = "Rate",nullable = false)
    private double rate;


    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
