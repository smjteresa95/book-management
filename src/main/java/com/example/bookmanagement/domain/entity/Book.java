package com.example.bookmanagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer majorCategoryId;
    private Integer subCategoryId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String isbn;
    private String author;
    private String translator;
    private String publisher;
    private LocalDate publicationDate;
    private Integer availableCopies;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
