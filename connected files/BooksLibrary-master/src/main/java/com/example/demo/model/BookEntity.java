package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(catalog = "book_library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Setter @Getter
@EqualsAndHashCode(of = "id")
public class BookEntity
{

    public BookEntity() {
    }

    public BookEntity(Long id, String name, Integer pageCount, String isbn, GenreEntity genreEntity,
					  AuthorEntity authorEntity, PublisherEntity publisherEntity, Integer publishYear, byte[] image,
					  String descr, long viewCount, long totalRating, long totalVoteCount, int avgRating) {
		this.id              = id;
		this.name            = name;
		this.pageCount       = pageCount;
		this.isbn            = isbn;
		this.genreEntity     = genreEntity;
		this.authorEntity    = authorEntity;
		this.publisherEntity = publisherEntity;
		this.publishYear     = publishYear;
		this.image           = image;
		this.descr           = descr;
		this.viewCount       = viewCount;
		this.totalRating     = totalRating;
		this.totalVoteCount  = totalVoteCount;
		this.avgRating       = avgRating;
    }

    public BookEntity(Long id, byte[] image) {
        this.id = id;
        this.image = image;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(updatable = false)
    private byte[] content;

    @Column(name = "page_count")
    private Integer pageCount;

    private String isbn;



    @ManyToOne
    @JoinColumn
    private GenreEntity genreEntity;

    @ManyToOne
    @JoinColumn
    private AuthorEntity authorEntity;

    @ManyToOne
    @JoinColumn
    private PublisherEntity publisherEntity;



    @Column(name = "publish_year")
    private Integer publishYear;


    private byte[] image;

    private String descr;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name = "total_rating")
    private long totalRating;

    @Column(name = "total_vote_count")
    private long totalVoteCount;

    @Column(name = "avg_rating")
    private int avgRating;

    @Override
    public String toString() {
        return name;
    }

}
