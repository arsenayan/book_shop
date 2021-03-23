package com.example.demo.services.impl;

import com.example.demo.model.entities.PublisherEntity;
import com.example.demo.services.dao.PublisherDao;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with publisher, the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class PublisherService implements PublisherDao {
    @Override
    public List<PublisherEntity> getAll() {
        return null;
    }

    @Override
    public Page<PublisherEntity> search(String... searchString) {
        return null;
    }

    @Override
    public PublisherEntity get(long id) {
        return null;
    }

    @Override
    public PublisherEntity save(PublisherEntity obj) {
        return null;
    }

    @Override
    public void delete(PublisherEntity obj) {

    }

    @Override
    public Page<PublisherEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<PublisherEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
