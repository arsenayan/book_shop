package com.example.demo.services.dao.impl;

import com.example.demo.model.GenreEntity;
import com.example.demo.services.dao.GenreDao;
import com.example.demo.spring.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreService implements GenreDao {
	
	@Autowired
	private GenreRepository genreRepository;
	
	
    @Override
    public List<GenreEntity> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<GenreEntity> search(String... searchString) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public GenreEntity get(long id) {
        return null; //genreRepository.findOne(id);
    }

    @Override
    public GenreEntity save(GenreEntity obj) {
        return genreRepository.save(obj);
    }

    @Override
    public void delete(GenreEntity obj) { genreRepository.delete(obj); }

    @Override
    public Page<GenreEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return genreRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<GenreEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
                 return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
		 
    }
}
