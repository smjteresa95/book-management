package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.exception.CocurrentException;
import com.example.bookmanagement.exception.EmptyObjectException;
import com.example.bookmanagement.util.mapper.BookRequestMapper;
import com.example.bookmanagement.util.mapper.BookResponseMapper;
import com.example.bookmanagement.web.dto.BookRequestDto;
import com.example.bookmanagement.web.dto.BookResponseDto;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookRequestMapper requestMapper;
    private final BookResponseMapper responseMapper;

    public BookResponseDto getBook(Long id){
        Optional<Book> bookOptional = repository.findById(id);
        if(bookOptional.isEmpty()) throw new NoSuchElementException("{} 도서가 존재하지 않습니다.");
        return responseMapper.toDto(bookOptional.get());
    }


    @Transactional
    public Long saveBook(BookRequestDto requestDto){

        if(requestDto == null){
            throw new EmptyObjectException("저장 할 도서정보가 없습니다.");
        }

        Book book = repository.save(requestMapper.toEntity(requestDto));

        log.debug("도서정보를 저장 했습니다.");
        return book.getId();
    }

    @Transactional
    public Long updateBook(Long id, BookRequestDto requestDto){
        Optional<Book> existBook = repository.findById(id);

        if(existBook.isEmpty()){
            throw new EmptyObjectException("업데이트 할 도서정보가 존재하지 않습니다.");
        }
        try {
            BookRequestDto bookToUpdate = requestMapper.toDto(existBook.get());
            requestMapper.updateFromDto(requestDto, bookToUpdate);
            log.info("Updated book: {}", bookToUpdate);
            repository.save(requestMapper.toEntity(bookToUpdate));
        } catch (OptimisticLockException e){
            throw new CocurrentException("도서 동시 업데이트 충돌 발생");
        }

        return id;
    }

}
