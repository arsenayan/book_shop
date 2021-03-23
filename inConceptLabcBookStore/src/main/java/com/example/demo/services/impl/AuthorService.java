package com.example.demo.services.impl;

import com.example.demo.model.entities.AuthorEntity;
import com.example.demo.services.dao.AuthorDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with author, the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class AuthorService implements AuthorDao {
    @Override
    public List<AuthorEntity> getAll() {
        return null;
    }

    @Override
    public Page<AuthorEntity> search(String... searchString) {
        return null;
    }

    @Override
    public AuthorEntity get(long id) {
        return null;
    }

    @Override
    public AuthorEntity save(AuthorEntity obj) {
        return null;
    }

    @Override
    public void delete(AuthorEntity obj) {

    }

    @Override
    public Page<AuthorEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<AuthorEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
