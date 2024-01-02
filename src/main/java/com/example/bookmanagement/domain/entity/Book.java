package com.example.bookmanagement.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Book")
public class Book {
    @Id
    private Long bookId;
    private String title;
    private String isbn;
    private String author;
    private String translator;
    private String publisher;
    private String publicationDate;
    private Long majorCategory;
    private Long subCategory;
    private Integer availableCopies;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
