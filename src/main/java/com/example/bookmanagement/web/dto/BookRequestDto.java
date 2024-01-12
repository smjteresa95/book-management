package com.example.bookmanagement.web.dto;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.enums.BookStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @Builder
@RequiredArgsConstructor
@ToString
public class BookRequestDto {

    private Long id;
    private Integer majorCategoryId;
    private Integer subCategoryId;
    private String title;
    private String isbn;
    private String author;
    private String translator;
    private String publisher;
    private LocalDate publicationDate;
    private BookStatus status;
    private Long version;

}
