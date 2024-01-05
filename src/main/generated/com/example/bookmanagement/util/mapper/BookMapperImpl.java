package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.web.dto.BookRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T21:39:43+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookRequestDto toDto(Book arg0) {
        if ( arg0 == null ) {
            return null;
        }

        BookRequestDto.BookRequestDtoBuilder bookRequestDto = BookRequestDto.builder();

        bookRequestDto.id( arg0.getId() );
        bookRequestDto.majorCategoryId( arg0.getMajorCategoryId() );
        bookRequestDto.subCategoryId( arg0.getSubCategoryId() );
        bookRequestDto.title( arg0.getTitle() );
        bookRequestDto.isbn( arg0.getIsbn() );
        bookRequestDto.author( arg0.getAuthor() );
        bookRequestDto.translator( arg0.getTranslator() );
        bookRequestDto.publisher( arg0.getPublisher() );
        bookRequestDto.publicationDate( arg0.getPublicationDate() );
        bookRequestDto.availableCopies( arg0.getAvailableCopies() );

        return bookRequestDto.build();
    }

    @Override
    public Book toEntity(BookRequestDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.id( arg0.getId() );
        book.majorCategoryId( arg0.getMajorCategoryId() );
        book.subCategoryId( arg0.getSubCategoryId() );
        book.title( arg0.getTitle() );
        book.isbn( arg0.getIsbn() );
        book.author( arg0.getAuthor() );
        book.translator( arg0.getTranslator() );
        book.publisher( arg0.getPublisher() );
        book.publicationDate( arg0.getPublicationDate() );
        book.availableCopies( arg0.getAvailableCopies() );

        return book.build();
    }

    @Override
    public void updateFromDto(BookRequestDto arg0, BookRequestDto arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getMajorCategoryId() != null ) {
            arg1.setMajorCategoryId( arg0.getMajorCategoryId() );
        }
        if ( arg0.getSubCategoryId() != null ) {
            arg1.setSubCategoryId( arg0.getSubCategoryId() );
        }
        if ( arg0.getTitle() != null ) {
            arg1.setTitle( arg0.getTitle() );
        }
        if ( arg0.getIsbn() != null ) {
            arg1.setIsbn( arg0.getIsbn() );
        }
        if ( arg0.getAuthor() != null ) {
            arg1.setAuthor( arg0.getAuthor() );
        }
        if ( arg0.getTranslator() != null ) {
            arg1.setTranslator( arg0.getTranslator() );
        }
        if ( arg0.getPublisher() != null ) {
            arg1.setPublisher( arg0.getPublisher() );
        }
        if ( arg0.getPublicationDate() != null ) {
            arg1.setPublicationDate( arg0.getPublicationDate() );
        }
        if ( arg0.getAvailableCopies() != null ) {
            arg1.setAvailableCopies( arg0.getAvailableCopies() );
        }
    }
}
