package com.example.bookmanagement.web.dto;

import com.example.bookmanagement.domain.entity.Book;
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
    private Integer availableCopies;

    public Book toEntity(){
        return Book.builder()
                .id(id)
                .majorCategoryId(majorCategoryId)
                .subCategoryId(subCategoryId)
                .title(title)
                .isbn(isbn)
                .author(author)
                .translator(translator)
                .publisher(publisher)
                .publicationDate(publicationDate)
                .availableCopies(availableCopies)
                .build();
    }
}
