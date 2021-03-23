package com.example.demo.spring.repository;

import com.example.demo.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


 // wen we are getting books the content we prefer to be nothing
 // only we need content when the user wants to reed the book , in that time we will request for content
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>
{

    // by using this method we can search thw book by book`s name or by author, or we can just write HQL request instead of this long method
	List<BookEntity> findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(String name, String fio);

    @Query("select new com.example.demo.model.BookEntity(b.id, b.name, b.pageCount, b.isbn, b.genreEntity, b.authorEntity, b.publisherEntity, b.publishYear, b.image, b.descr, b.viewCount, b.totalRating, b.totalVoteCount, b.avgRating) from BookEntity b")
	Page<BookEntity> findAllWithoutContent(Pageable pageable); // here we getting back all the books by Pagening

    // when the request wants to change some data we need @Modifying
    @Modifying(clearAutomatically = true)
    @Query("update BookEntity b set b.content=:content where b.id =:id")
    void updateContent(@Param("content") byte[] content, @Param("id") long id);

    // we want to show some trending books that is why we created this request to show those books but only the images without any information
    // for creating this request we need to create in the book entity constructor with the same parameters
    @Query("select new com.example.demo.model.BookEntity (b.id, b.image) from BookEntity b")
    List<BookEntity> findTopBooks(Pageable pageable);

    // by colling this method we will get all the books by genre
    @Query("select new com.example.demo.model.BookEntity(b.id, b.name, b.pageCount, b.isbn, b.genreEntity, b.authorEntity, b.publisherEntity, b.publishYear, b.image, b.descr, b.viewCount, b.totalRating, b.totalVoteCount, b.avgRating) from BookEntity b where b.genre.id=:genreId")
    Page<BookEntity> findByGenre(@Param("genreId") long genreId, Pageable pageable);
    
    //we are getting the content of book via id
    @Query("select  b.content FROM BookEntity b where b.id=: id")
    byte[] getContent(@Param("id") long id);
    
	// updating viewing for book
	@Modifying
	@Query("update BookEntity b set b.viewCount=:viewCount where b.id =:id")
	void updateViewCount(@Param("viewCount") long viewCount, @Param("id") long id);
	
	// updating result of viewing
	@Modifying
	@Query("update BookEntity b set b.totalVoteCount=:totalVoteCount, b.totalRating=:totalRating, b.avgRating=:avgRating where b.id =:id")
	void updateRating(@Param("totalRating") long totalRating, @Param("totalVoteCount") long totalVoteCount,  @Param("avgRating") int avgRating, @Param("id") long id);
	
	
	
}
