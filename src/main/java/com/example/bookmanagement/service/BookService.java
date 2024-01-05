package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.exception.EmptyObjectException;
import com.example.bookmanagement.util.mapper.BookMapper;
import com.example.bookmanagement.web.dto.BookRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;
    public Long saveBook(BookRequestDto requestDto){

        if(requestDto == null){
            throw new EmptyObjectException("저장 할 도서정보가 없습니다.");
        }

        Book book = repository.save(requestDto.toEntity());

        log.debug("도서정보를 저장 했습니다.");
        return book.getId();
    }
    @Transactional
    public Long updateBook(Long id, BookRequestDto requestDto){
        Optional<Book> existBook = repository.findById(id);
        if(existBook.isEmpty()){
            throw new EmptyObjectException("업데이트 할 도서정보가 존재하지 않습니다.");
        }

        BookRequestDto bookToUpdate = mapper.toDto(existBook.get());
        mapper.updateFromDto(requestDto, bookToUpdate);

        log.info("Updated book: {}", bookToUpdate);

        repository.save(mapper.toEntity(bookToUpdate));

        return id;
    }


}
