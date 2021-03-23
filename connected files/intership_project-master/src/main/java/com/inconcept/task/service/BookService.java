package com.inconcept.task.service;


import com.inconcept.task.persistence.entity.BookEntity;
import com.inconcept.task.persistence.entity.PublisherEntity;
import com.inconcept.task.persistence.repository.BookRepository;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.BookDto;
import com.inconcept.task.service.model.ContentQuery;
import com.inconcept.task.service.model.UploadImgThread;
import com.inconcept.task.service.utils.BookCSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookCSVParser bookCSVConfig;
    private final PublisherService publisherService;

    @Value("${image.upload.bookUrl}")
    private String bookImgUrl;

    @Autowired
    public BookService(BookRepository bookRepository, BookCSVParser bookCSVConfig, PublisherService publisherService) {
        this.bookRepository = bookRepository;
        this.bookCSVConfig = bookCSVConfig;
        this.publisherService = publisherService;
    }

    public ContentQuery<BookDto> getBooks(SearchCriteria searchCriteria) {
        Page<BookEntity> bookDtoPage = bookRepository.findAllWithPage(searchCriteria.composePageRequest());
        return new ContentQuery<BookDto>(bookDtoPage.getTotalPages(), BookDto.castEntityToDto(bookDtoPage.getContent()));
    }

    public void saveCsvToDataBase(MultipartFile file) throws Exception {
        if (!BookCSVParser.hasCSVFormat(file)) {
            throw new Exception("File should be csv extension");
        }
        try {
            List<BookDto> dtoList = bookCSVConfig.parseBookCsv(file.getInputStream());
//            UploadImgThread uploadImgThread = new UploadImgThread(bookImgUrl, dtoList.stream().map(BookDto::getPicUrl).collect(Collectors.toList()));
//            uploadImgThread.start();
            bookRepository.saveAll(mappingEntities(dtoList));

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public BookDto addBook(BookDto bookDto) {
        BookEntity bookEntity = BookDto.castDtoToEntity(bookDto);
        PublisherEntity publisherEntity = publisherService.searchPublisher(bookDto.getPublisher());

        if (publisherEntity == null) {
            publisherEntity = new PublisherEntity();
            publisherEntity.setFio(bookDto.getPublisher());
        }

        publisherService.save(publisherEntity);
        bookEntity.setPublisher(publisherEntity);
        bookRepository.save(bookEntity);
        return bookDto;
    }


    public Double getBookRate(Long bookId) {
        BookEntity bookEntity = bookRepository.findOneById(bookId);
        BookDto bookDto = BookDto.castEntityToDto(bookEntity);
        return bookDto.getAvgRating();
    }

    public BookDto getBook(Long id) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));
        return BookDto.castEntityToDto(bookEntity);
    }

    public void deleteBookById(Long id) throws Exception {
        bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(Long id, BookDto bookDto) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));

        if (bookDto.getTitle() != null) {
            bookEntity.setTitle(bookDto.getTitle());
        }

        bookEntity = bookRepository.save(bookEntity);
        return BookDto.castEntityToDto(bookEntity);
    }


    private List<BookEntity> mappingEntities(List<BookDto> bookDtoList) {
        List<PublisherEntity> listPublishers = new ArrayList<>();
        List<BookEntity> bookEntityList = new ArrayList<>();

        for (BookDto dto : bookDtoList) {
            PublisherEntity publisherEntity = publisherService.searchPublisher(dto.getPublisher());

            if (publisherEntity == null) {
                publisherEntity = new PublisherEntity();
                publisherEntity.setFio(dto.getPublisher());
            }

            listPublishers.add(publisherEntity);
            BookEntity bookEntity = BookDto.castDtoToEntity(dto);
            bookEntity.setPublisher(publisherEntity);
            bookEntityList.add(bookEntity);
        }
        publisherService.saveAllPublisher(listPublishers);
        return bookEntityList;
    }
}
