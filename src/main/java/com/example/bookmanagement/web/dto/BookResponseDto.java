package com.example.bookmanagement.web.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @Builder
@RequiredArgsConstructor
@ToString
public class BookResponseDto {

    private Integer majorCategoryId;
    private Integer subCategoryId;
    private String title;
    private String isbn;
    private String author;
    private String translator;
    private String publisher;
    private LocalDate publicationDate;
    private Integer availableCopies;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
