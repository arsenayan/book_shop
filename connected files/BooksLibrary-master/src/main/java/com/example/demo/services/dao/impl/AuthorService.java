package com.example.demo.services.dao.impl;

import com.example.demo.model.AuthorEntity;
import com.example.demo.services.dao.AuthorDao;
import com.example.demo.spring.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This is our server to work with author, the code have to call via service not via Repository
@Service // with this annotation we are creating our bean
@Transactional
public class AuthorService  implements AuthorDao {
	
	@Autowired
	private AuthorRepository authorRepository;
	
    @Override
    public List<AuthorEntity> getAll() {
        return authorRepository.findAll();
    }
    
    public List<AuthorEntity> getAll(Sort sort) { return authorRepository.findAll(sort); }
    
    @Override
    public List<AuthorEntity> search(String... searchString) { return authorRepository.findByFioContainingIgnoreCaseOrderByFio(searchString[0]); }

    @Override
    public AuthorEntity get(long id) { return authorRepository.getOne(id); }

    @Override
    public AuthorEntity save(AuthorEntity obj) {
        return authorRepository.save(obj);
    }

    @Override
    public void delete(AuthorEntity obj) { authorRepository.delete(obj); }

    @Override
    public Page<AuthorEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
		return authorRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField))); }

    @Override
    public Page<AuthorEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
              return authorRepository.findByFioContainingIgnoreCaseOrderByFio(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }
}
