package com.example.bookmanagement.web.controller;

import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.service.LoanRecordService;
import com.example.bookmanagement.web.dto.BookRequestDto;
import com.example.bookmanagement.web.dto.BookResponseDto;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService service;
    private final LoanRecordService loanService;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable("id") Long id){
        BookResponseDto responseDto = service.getBook(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> saveBook(@RequestBody BookRequestDto requestDto){
        Long id = service.saveBook(requestDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable("id") Long id,
                                                  @RequestBody BookRequestDto requestDto){
        service.updateBook(id, requestDto);
        log.info("{}: 도서를 수정하였습니다.", id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}/loan")
    public ResponseEntity<List<LoanRecordResponseDto>> getLoanRecordsByBookId(@PathVariable("id") Long id){
        List<LoanRecordResponseDto> recordList = loanService.getAllByBookId(id);
        if(recordList.isEmpty()) log.catching(new NoSuchElementException("대출 이력이 없는 도서이거나 존재하지 않는 도서입니다."));
        return new ResponseEntity<>(recordList, HttpStatus.OK);
    }

}
