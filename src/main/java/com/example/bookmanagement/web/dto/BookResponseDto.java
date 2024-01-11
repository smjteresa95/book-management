package com.example.bookmanagement.web.dto;

import com.example.bookmanagement.enums.BookStatus;
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
    private BookStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
