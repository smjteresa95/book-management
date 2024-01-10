package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.util.mapper.LoanRecordMapper;
import com.example.bookmanagement.web.dto.BookLoanRequestDto;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoanRecordService {
    private final LoanRecordRepository repository;
    private final LoanRecordMapper mapper;

    public List<LoanRecordResponseDto> getAllByBookId(long id){
        List<LoanRecord> loanRecordList = repository.getAllByBookId(id);
        if(loanRecordList.isEmpty()) throw new NoSuchElementException("대출 이력이 없는 도서입니다.");
        List<LoanRecordResponseDto> responseDtoList = new ArrayList<>();
        for(LoanRecord record : loanRecordList){
            responseDtoList.add(mapper.toDto(record));
        }
        return responseDtoList;
    }

    public List<LoanRecordResponseDto> getAllByUserId(long id){
        List<LoanRecord> loanRecordList = repository.getAllByUserId(id);
        if(loanRecordList.isEmpty()) throw new NoSuchElementException("대출 이력이 없는 사용자입니다.");
        List<LoanRecordResponseDto> responseDtoList = new ArrayList<>();
        for(LoanRecord record : loanRecordList){
            responseDtoList.add(mapper.toDto(record));
        }
        return responseDtoList;
    }

    //아이디를 입력 후 도서에 대한 대출처리를 한다.
    public void createBookLoan(BookLoanRequestDto requestDto){
        long userId = requestDto.getUserId();
        long bookId = requestDto.getBookId();


    }

}
