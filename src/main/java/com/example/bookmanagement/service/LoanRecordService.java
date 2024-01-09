package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.util.mapper.LoanRecordMapper;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanRecordService {
    private final LoanRecordRepository repository;
    private final LoanRecordMapper mapper;

    public LoanRecordResponseDto getAllLoanRecordByBookId(long id){
        Optional<LoanRecord> loanRecordOptional = repository.getLoanRecordByBookId(id);
        if(loanRecordOptional.isEmpty()) throw new NoSuchElementException("존재하지 않는 도서입니다.");
        return mapper.toDto(loanRecordOptional.get());
    }

}
