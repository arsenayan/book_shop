package com.example.demo.services.impl;

import com.example.demo.model.entities.UserEntity;
import com.example.demo.services.dao.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with user and admin , the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class UserService implements UserDao {
    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public Page<UserEntity> search(String... searchString) {
        return null;
    }

    @Override
    public UserEntity get(long id) {
        return null;
    }

    @Override
    public UserEntity save(UserEntity obj) {
        return null;
    }

    @Override
    public void delete(UserEntity obj) {

    }

    @Override
    public Page<UserEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<UserEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
