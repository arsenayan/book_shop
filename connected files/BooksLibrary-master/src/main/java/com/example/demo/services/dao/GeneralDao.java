package com.example.demo.services.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

// General behavior of our DAO interfaces
public interface GeneralDao<T> {
	
    //  getting all without paging
    List<T> getAll();
    
    // searching everything by putting how many parameters we want
    List<T> search(String... searchString);
    
    // getting object by id
    T get(long id);
    
    // update or save method together
    T save(T obj);
    
    // deleting object
    void delete(T obj);
    
    // getting all pages by pageable
    Page<T> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection);
    
    // searching all by pageable
    Page<T> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString);
    
}
