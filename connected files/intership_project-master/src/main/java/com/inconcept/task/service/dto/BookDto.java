package com.inconcept.task.service.dto;


import com.inconcept.task.persistence.entity.AuthorEntity;
import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.persistence.entity.PublisherEntity;
import com.inconcept.task.persistence.entity.RateEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BookDto {
    private String title;
    private String publishDate;
    private String publisher;
    private Double avgRating;
    private String picUrl;
    private List<String> bookAuthors = new ArrayList<>();


    public BookDto(String title, String publishDate, String publisher, Double avgRating, List<String> bookAuthors) {
        this.title = title;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.avgRating = avgRating;
        this.bookAuthors = bookAuthors;
    }

    public BookDto() {

    }

    public BookDto(String title, String publishDate, String publisher, String picUrl) {
        this.title = title;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public List<String> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<String> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public static Double getAvgRatingBook(List<RateEntity> rateEntityList) {
        if (!CollectionUtils.isEmpty(rateEntityList)) {
            double temp = rateEntityList.stream().map(RateEntity::getRate).mapToDouble(Double::doubleValue).sum();
            return temp / rateEntityList.size();
        }
        return null;
    }

    public static List<BookDto> castEntityToDto(List<BookEntity> bookDtoList) {
        return bookDtoList.stream().map(b -> castEntityToDto(b)).collect(Collectors.toList());
    }

    public static BookDto castEntityToDto(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }
        BookDto bookDto = new BookDto();
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setPublishDate(bookEntity.getPublishDate());
        bookDto.setPublisher(bookEntity.getPublisher().getFio());
        bookDto.setAvgRating(BookDto.getAvgRatingBook(bookEntity.getListBookRates()));

        List<AuthorEntity> authorEntitySet = bookEntity.getBookAuthors();
        if (!CollectionUtils.isEmpty(authorEntitySet)) {
            bookDto.setBookAuthors(bookEntity.getBookAuthors().stream().map(AuthorEntity::getfio).collect(Collectors.toList()));
        }
        return bookDto;
    }


    public static List<BookEntity> castDtoToEntity(List<BookDto> bookDtos) {
        return bookDtos.stream().map(b -> castDtoToEntity(b)).collect(Collectors.toList());
    }

    public static BookEntity castDtoToEntity(BookDto bookDto) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setPublishDate(bookDto.getPublishDate());
        bookEntity.setPicUrl(bookDto.getPicUrl());
        List<AuthorEntity> authorList = null;
        if (!CollectionUtils.isEmpty(bookDto.getBookAuthors())) {
            authorList = new ArrayList<>();
            for (String author : bookDto.getBookAuthors()) {
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setfio(author);
                authorList.add(authorEntity);
            }
            bookEntity.getBookAuthors().addAll(authorList);
        }
        return bookEntity;
    }
}
