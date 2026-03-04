package demo.databaseconfig.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book", schema = "bookstore")
@Data
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "info")
    private String info;

}
