package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.web.dto.BookResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-11T17:25:39+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BookResponseMapperImpl implements BookResponseMapper {

    @Override
    public BookResponseDto toDto(Book e) {
        if ( e == null ) {
            return null;
        }

        BookResponseDto.BookResponseDtoBuilder bookResponseDto = BookResponseDto.builder();

        bookResponseDto.majorCategoryId( e.getMajorCategoryId() );
        bookResponseDto.subCategoryId( e.getSubCategoryId() );
        bookResponseDto.title( e.getTitle() );
        bookResponseDto.isbn( e.getIsbn() );
        bookResponseDto.author( e.getAuthor() );
        bookResponseDto.translator( e.getTranslator() );
        bookResponseDto.publisher( e.getPublisher() );
        bookResponseDto.publicationDate( e.getPublicationDate() );
        bookResponseDto.status( e.getStatus() );
        bookResponseDto.createdAt( e.getCreatedAt() );
        bookResponseDto.updatedAt( e.getUpdatedAt() );

        return bookResponseDto.build();
    }

    @Override
    public Book toEntity(BookResponseDto d) {
        if ( d == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.majorCategoryId( d.getMajorCategoryId() );
        book.subCategoryId( d.getSubCategoryId() );
        book.title( d.getTitle() );
        book.isbn( d.getIsbn() );
        book.author( d.getAuthor() );
        book.translator( d.getTranslator() );
        book.publisher( d.getPublisher() );
        book.publicationDate( d.getPublicationDate() );
        book.status( d.getStatus() );
        book.createdAt( d.getCreatedAt() );
        book.updatedAt( d.getUpdatedAt() );

        return book.build();
    }

    @Override
    public void updateFromDto(BookResponseDto dto, BookResponseDto target) {
        if ( dto == null ) {
            return;
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
        if ( dto.getStatus() != null ) {
            target.setStatus( dto.getStatus() );
        }
        if ( dto.getCreatedAt() != null ) {
            target.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            target.setUpdatedAt( dto.getUpdatedAt() );
        }
    }
}
