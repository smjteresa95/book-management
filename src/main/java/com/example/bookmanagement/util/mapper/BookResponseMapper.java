package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.web.dto.BookResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookResponseMapper extends GenericMapper<BookResponseDto, Book> {
}
