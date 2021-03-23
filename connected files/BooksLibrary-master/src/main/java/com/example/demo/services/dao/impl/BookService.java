package com.example.demo.services.dao.impl;

import com.example.demo.model.BookEntity;
import com.example.demo.services.dao.BookDao;
import com.example.demo.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService implements BookDao
{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookEntity> getAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<BookEntity> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return bookRepository.findAllWithoutContent(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<BookEntity> search(String... searchString) {
        return null;
    }


    @Override
    public Page<BookEntity> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        // чтобы название метода не было слишком длинным - можно использовать @Query c HQL (если больше 2-х переменных)
        return bookRepository.findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(searchString[0], searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }


    @Override
    public BookEntity save(BookEntity book) {

        // wr are saving the book into db
        bookRepository.save(book);

        if (book.getContent()!=null) {
            // but also checking there is content or not
            bookRepository.updateContent(book.getContent(), book.getId());
        }

        return book;

    }

    @Override
    public void delete(BookEntity book) {
        bookRepository.delete(book);
    }

    @Override
    public BookEntity get(long id) {
        return null;// bookRepository.findOne(id);
    }
	
	@Override
	public List<BookEntity> searchTopBooks(int limit)
	{
		return null;
	}
	
	@Override
    public byte[] getContent(long id) {
        return bookRepository.getContent(id);
    }

    public List<BookEntity> findTopBooks(int limit) {
        return bookRepository.findTopBooks(new PageRequest(0,limit, new Sort(Sort.Direction.DESC, "viewCount")));
    }

    @Override
    public Page<BookEntity> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId) {
        return bookRepository.findByGenre(genreId, new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }


   /* @Override
    public void updateViewCount(long viewCount, long id) {
        bookRepository.updateViewCount(viewCount, id);
    }

    @Override
    public void updateRating(long totalRating, long totalVoteCount, int avgRating, long id) {
        bookRepository.updateRating(totalRating, totalVoteCount, avgRating, id);
    }
*/

}