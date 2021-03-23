package com.example.demo.services.impl;

import com.example.demo.model.entities.GenreEntity;
import com.example.demo.services.dao.GenreDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with genre, the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class GenreService implements GenreDao {
    @Override
    public List<GenreEntity> getAll() {
        return null;
    }

    @Override
    public Page<GenreEntity> search(String... searchString) {
        return null;
    }

    @Override
    public GenreEntity get(long id) {
        return null;
    }

    @Override
    public GenreEntity save(GenreEntity obj) {
        return null;
    }

    @Override
    public void delete(GenreEntity obj) {

    }

    @Override
    public Page<GenreEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<GenreEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
