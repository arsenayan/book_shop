package com.inconcept.task.controller;

import com.inconcept.task.persistence.entity.AuthorEntity;
import com.inconcept.task.service.AuthorService;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ContentQuery<AuthorEntity> getAuthors(SearchCriteria searchCriteria) {
        return authorService.getAuthors(searchCriteria);
    }

    @PostMapping()
    public ResponseEntity<AuthorEntity> save(@RequestBody AuthorEntity authorEntity) throws Exception {

        if (authorEntity == null) {
            throw new Exception("Author cannot be null");
        }
        authorService.save(authorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            throw new Exception("Id cannot be null");
        }
        authorService.deleteById(id);
    }
}
