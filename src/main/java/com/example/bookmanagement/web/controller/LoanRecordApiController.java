package com.example.bookmanagement.web.controller;

import com.example.bookmanagement.service.LoanRecordService;
import com.example.bookmanagement.web.dto.LoanRecordRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanRecordApiController {

    private final LoanRecordService loanService;

    @PostMapping
    public ResponseEntity<?> loanBook(@RequestBody LoanRecordRequestDto requestDto){
        loanService.createBookLoan(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
