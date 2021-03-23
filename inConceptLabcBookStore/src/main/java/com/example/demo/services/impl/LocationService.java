package com.example.demo.services.impl;

import com.example.demo.model.entities.LocationEntity;
import com.example.demo.services.dao.LocationDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with location, the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class LocationService implements LocationDao {
    @Override
    public List<LocationEntity> getAll() {
        return null;
    }

    @Override
    public Page<LocationEntity> search(String... searchString) {
        return null;
    }

    @Override
    public LocationEntity get(long id) {
        return null;
    }

    @Override
    public LocationEntity save(LocationEntity obj) {
        return null;
    }

    @Override
    public void delete(LocationEntity obj) {

    }

    @Override
    public Page<LocationEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<LocationEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
