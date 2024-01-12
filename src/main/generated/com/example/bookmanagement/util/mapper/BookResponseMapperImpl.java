package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.web.dto.BookResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-12T15:40:23+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BookResponseMapperImpl implements BookResponseMapper {

    @Override
    public BookResponseDto toDto(Book arg0) {
        if ( arg0 == null ) {
            return null;
        }

        BookResponseDto.BookResponseDtoBuilder bookResponseDto = BookResponseDto.builder();

        bookResponseDto.majorCategoryId( arg0.getMajorCategoryId() );
        bookResponseDto.subCategoryId( arg0.getSubCategoryId() );
        bookResponseDto.title( arg0.getTitle() );
        bookResponseDto.isbn( arg0.getIsbn() );
        bookResponseDto.author( arg0.getAuthor() );
        bookResponseDto.translator( arg0.getTranslator() );
        bookResponseDto.publisher( arg0.getPublisher() );
        bookResponseDto.publicationDate( arg0.getPublicationDate() );
        bookResponseDto.status( arg0.getStatus() );
        bookResponseDto.createdAt( arg0.getCreatedAt() );
        bookResponseDto.updatedAt( arg0.getUpdatedAt() );

        return bookResponseDto.build();
    }

    @Override
    public Book toEntity(BookResponseDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.majorCategoryId( arg0.getMajorCategoryId() );
        book.subCategoryId( arg0.getSubCategoryId() );
        book.title( arg0.getTitle() );
        book.isbn( arg0.getIsbn() );
        book.author( arg0.getAuthor() );
        book.translator( arg0.getTranslator() );
        book.publisher( arg0.getPublisher() );
        book.publicationDate( arg0.getPublicationDate() );
        book.status( arg0.getStatus() );
        book.createdAt( arg0.getCreatedAt() );
        book.updatedAt( arg0.getUpdatedAt() );

        return book.build();
    }

    @Override
    public void updateFromDto(BookResponseDto arg0, BookResponseDto arg1) {
        if ( arg0 == null ) {
            return;
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
        if ( arg0.getStatus() != null ) {
            arg1.setStatus( arg0.getStatus() );
        }
        if ( arg0.getCreatedAt() != null ) {
            arg1.setCreatedAt( arg0.getCreatedAt() );
        }
        if ( arg0.getUpdatedAt() != null ) {
            arg1.setUpdatedAt( arg0.getUpdatedAt() );
        }
        if ( arg0.getVersion() != null ) {
            arg1.setVersion( arg0.getVersion() );
        }
    }
}
