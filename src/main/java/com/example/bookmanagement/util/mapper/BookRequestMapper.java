package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.web.dto.BookRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookRequestMapper extends GenericMapper<BookRequestDto, Book> {
}
