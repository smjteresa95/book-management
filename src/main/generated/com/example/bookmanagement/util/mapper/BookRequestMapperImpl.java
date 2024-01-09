package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.web.dto.BookRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-09T23:16:09+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BookRequestMapperImpl implements BookRequestMapper {

    @Override
    public BookRequestDto toDto(Book e) {
        if ( e == null ) {
            return null;
        }

        BookRequestDto.BookRequestDtoBuilder bookRequestDto = BookRequestDto.builder();

        bookRequestDto.id( e.getId() );
        bookRequestDto.majorCategoryId( e.getMajorCategoryId() );
        bookRequestDto.subCategoryId( e.getSubCategoryId() );
        bookRequestDto.title( e.getTitle() );
        bookRequestDto.isbn( e.getIsbn() );
        bookRequestDto.author( e.getAuthor() );
        bookRequestDto.translator( e.getTranslator() );
        bookRequestDto.publisher( e.getPublisher() );
        bookRequestDto.publicationDate( e.getPublicationDate() );
        bookRequestDto.availableCopies( e.getAvailableCopies() );

        return bookRequestDto.build();
    }

    @Override
    public Book toEntity(BookRequestDto d) {
        if ( d == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.id( d.getId() );
        book.majorCategoryId( d.getMajorCategoryId() );
        book.subCategoryId( d.getSubCategoryId() );
        book.title( d.getTitle() );
        book.isbn( d.getIsbn() );
        book.author( d.getAuthor() );
        book.translator( d.getTranslator() );
        book.publisher( d.getPublisher() );
        book.publicationDate( d.getPublicationDate() );
        book.availableCopies( d.getAvailableCopies() );

        return book.build();
    }

    @Override
    public void updateFromDto(BookRequestDto dto, BookRequestDto target) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            target.setId( dto.getId() );
        }
        if ( dto.getMajorCategoryId() != null ) {
            target.setMajorCategoryId( dto.getMajorCategoryId() );
        }
        if ( dto.getSubCategoryId() != null ) {
            target.setSubCategoryId( dto.getSubCategoryId() );
        }
        if ( dto.getTitle() != null ) {
            target.setTitle( dto.getTitle() );
        }
        if ( dto.getIsbn() != null ) {
            target.setIsbn( dto.getIsbn() );
        }
        if ( dto.getAuthor() != null ) {
            target.setAuthor( dto.getAuthor() );
        }
        if ( dto.getTranslator() != null ) {
            target.setTranslator( dto.getTranslator() );
        }
        if ( dto.getPublisher() != null ) {
            target.setPublisher( dto.getPublisher() );
        }
        if ( dto.getPublicationDate() != null ) {
            target.setPublicationDate( dto.getPublicationDate() );
        }
        if ( dto.getAvailableCopies() != null ) {
            target.setAvailableCopies( dto.getAvailableCopies() );
        }
    }
}
