package com.inconcept.task.controller;

import com.inconcept.task.service.BookService;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.BookDto;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping()
    public ContentQuery<BookDto> getBooks(SearchCriteria searchCriteria) {
        return bookService.getBooks(searchCriteria);
    }

    @PostMapping()
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) throws Exception {
        if (bookDto.getTitle() == null) {
            throw new Exception("Fill required fields");
        }
        BookDto book = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PostMapping("/uploadCsv")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new Exception("File cannot be null");
        }
        bookService.saveCsvToDataBase(file);
        return ResponseEntity.ok("Uploaded the file successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable() Long id) throws Exception {
        return ResponseEntity.ok(bookService.getBook(id));
    }


    @GetMapping("/rate")
    public Double getBookRating(@RequestParam("bookId") Long id) {
        return bookService.getBookRate(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable() Long id, @RequestBody BookDto bookDto) throws Exception {
        BookDto dto = bookService.updateBook(id, bookDto);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            throw new Exception("Id cannot be null");
        }
        bookService.deleteBookById(id);
    }
}
