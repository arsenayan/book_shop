package com.example.demo.services.dao.impl;

import com.example.demo.model.PublisherEntity;
import com.example.demo.services.dao.PublisherDao;
import com.example.demo.spring.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PublisherService implements PublisherDao {

    @Autowired
    private PublisherRepository publisherRepository;


    @Override
    public List<PublisherEntity> getAll() {
        return publisherRepository.findAll();
    }

    public List<PublisherEntity> getAll(Sort sort) {
        return publisherRepository.findAll(sort);
    }



    @Override
    public Page<PublisherEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return publisherRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }


    @Override
    public List<PublisherEntity> search(String... searchString) {
        return publisherRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public Page<PublisherEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
		//Page<PublisherEntity> publisherEntities = publisherRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0],  PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    	
        return null;// publisherRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }


    @Override
    public PublisherEntity save(PublisherEntity publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void delete(PublisherEntity publisher){
        publisherRepository.delete(publisher);
    }

    @Override
    public PublisherEntity get(long id) {
        return null; // publisherRepository.findOne(id);
    }




}
