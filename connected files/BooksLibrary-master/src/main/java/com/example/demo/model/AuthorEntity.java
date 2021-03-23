package com.example.demo.model;
// this is LOMBOK imports that makes our life a little bit easy
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// some imports from hibernate ORM
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

//just util imports
import javax.persistence.*;
import java.util.Date;
import java.util.List;




@Entity
@Table(catalog = "book_library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@EqualsAndHashCode
@Setter @Getter
public class AuthorEntity
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Basic
    private String fio;

    private Date birthday;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "author")
    private List<BookEntity> bookEntities;

    public AuthorEntity() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "fio='" + fio + '\'' +
                '}';
    }
}
