package com.example.bookmanagement.util;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.repository.MajorCategoryRepository;
import com.example.bookmanagement.domain.repository.SubCategoryRepository;
import com.example.bookmanagement.web.dto.BookRequestDto;
import com.example.bookmanagement.web.dto.BookResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mapper {

    private final MajorCategoryRepository majorCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public BookRequestDto BookRequestToDto(Book book){
        return BookRequestDto.builder().build();
    }

    public BookResponseDto BookResponseToDto(Book book){
        String majorCategory = majorCategoryRepository
                .findById(book.getMajorCategoryId().longValue()).get().getCategory();
        String subCategory = subCategoryRepository
                .findById(book.getSubCategoryId().longValue()).get().getCategory();

        return BookResponseDto.builder()
                .majorCategory(majorCategory)
                .subCategory(subCategory)
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .translator(book.getTranslator())
                .publicationDate(book.getPublicationDate())
                .availableCopies(book.getAvailableCopies())
                .build();
    }

}
