package com.example.demo.services.dao;


import com.example.demo.model.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

//here we will write specific behavior of Book
public interface BookDao  extends GeneralDao<BookEntity>{



   // getting genre by paging
   Page<BookEntity> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId);
}
