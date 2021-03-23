package com.inconcept.task.service;

import com.inconcept.task.persistence.entity.AuthorEntity;
import com.inconcept.task.persistence.repository.AuthorRepository;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public ContentQuery<AuthorEntity> getAuthors(SearchCriteria searchCriteria) {
        Page<AuthorEntity> authorEntityPage = authorRepository.findAllWithPage(searchCriteria.composePageRequest());
        return new ContentQuery<AuthorEntity>(authorEntityPage.getTotalPages(), authorEntityPage.getContent());
    }

    public void save(AuthorEntity authorEntity) {
        authorRepository.save(authorEntity);
    }

    public void deleteById(Long id) throws Exception {
        authorRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Author which id %d not found", id)));
        authorRepository.deleteById(id);
    }
}
