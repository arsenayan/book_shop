package com.example.demo.services.impl;

import com.example.demo.model.entities.BookEntity;
import com.example.demo.services.dao.BookDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with book, the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class BookService implements BookDao {
    @Override
    public Page<BookEntity> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId) {
        return null;
    }

    @Override
    public List<BookEntity> getAll() {
        return null;
    }

    @Override
    public Page<BookEntity> search(String... searchString) {
        return null;
    }

    @Override
    public BookEntity get(long id) {
        return null;
    }

    @Override
    public BookEntity save(BookEntity obj) {
        return null;
    }

    @Override
    public void delete(BookEntity obj) {

    }

    @Override
    public Page<BookEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<BookEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
