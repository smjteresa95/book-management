package com.example.bookmanagement.service;

import com.example.bookmanagement.config.LoanPolicyConfig;
import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.exception.BookLoanBlockedException;
import com.example.bookmanagement.exception.EmptyObjectException;
import com.example.bookmanagement.util.mapper.LoanRecordResponseMapper;
import com.example.bookmanagement.util.mapper.LoanRecordUpdateMapper;
import com.example.bookmanagement.web.dto.BookLoanRequestDto;
import com.example.bookmanagement.web.dto.BookLoanResponseDto;
import com.example.bookmanagement.web.dto.BookLoanUpdateDto;
import com.example.bookmanagement.web.dto.BookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanRecordService {
    private final LoanRecordRepository repository;
    private final BookRepository bookRepository;

    private final LoanRecordResponseMapper responseMapper;
    private final LoanRecordUpdateMapper updateMapper;

    private final BookService bookService;

    private final LoanPolicyConfig policyConfig;

    public List<BookLoanResponseDto> getAllByBookId(long id){
        List<LoanRecord> loanRecordList = repository.getAllByBookId(id);
        if(loanRecordList.isEmpty()) throw new NoSuchElementException("대출 이력이 없는 도서입니다.");
        List<BookLoanResponseDto> responseDtoList = new ArrayList<>();
        for(LoanRecord record : loanRecordList){
            responseDtoList.add(responseMapper.toDto(record));
        }
        return responseDtoList;
    }

    public List<BookLoanResponseDto> getAllByUserId(long id){
        List<LoanRecord> loanRecordList = repository.getAllByUserId(id);
        if(loanRecordList.isEmpty()) throw new NoSuchElementException("대출 이력이 없는 사용자입니다.");
        List<BookLoanResponseDto> responseDtoList = new ArrayList<>();
        for(LoanRecord record : loanRecordList){
            responseDtoList.add(responseMapper.toDto(record));
        }
        return responseDtoList;
    }

    //아이디를 입력 후 도서에 대한 대출처리를 한다.
    public void createBookLoan(BookLoanRequestDto requestDto){
        long userId = requestDto.getUserId();
        long bookId = requestDto.getBookId();
        LocalDateTime today = LocalDateTime.now();

        //TODO 리펙토링 필요
        //해당 도서가 존재하며, availableCopies가 1이상 인지 확인 한다.
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty())
            throw new EmptyObjectException("존재하지 않는 도서입니다.");
        int currentAvailableCopies = bookOptional.get().getAvailableCopies();
        if(currentAvailableCopies < 1)
            throw new BookLoanBlockedException("해당 도서는 대출이 불가한 상태입니다.");

        //TODO 리펙토링 필요
        //유저의 연체 유무를 확인한다. 반납되지 않은 도서갯수를 구한다.
        List<LoanRecord> loanRecordList = repository.getAllByUserId(userId);
        int overDue = 0;
        int loanedBooks = 0;
        for(LoanRecord record : loanRecordList){
            //dueDate이 오늘 날짜 기준으로 지나고, returnDate이 없는 경우 overDue++
            if(record.getDueDate().isBefore(today) && record.getReturnDate() == null)
                overDue++;
            //returnedDate이 없고, dueDate이 오늘 날짜 기준으로 지나거나, 지나지 않은 경우 loanedBooks++
            if(record.getReturnDate() == null && (record.getDueDate().isBefore(today) || record.getDueDate().isAfter(today)))
                loanedBooks++;
        }
        if(overDue > 0) throw new BookLoanBlockedException("연체된 도서가 있어 대출이 불가합니다.");

        //인당 한번에 대출 가능 한 도서 권수를 제한한다.
        if(loanedBooks >= policyConfig.getMaxBooksPerUser())
            throw new BookLoanBlockedException("대여 가능한 도서의 권수를 초과하였습니다.");


        //해당 도서의 availableCopies 갯수를 -1
        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .availableCopies(--currentAvailableCopies).build();
        bookService.updateBook(bookId, bookRequestDto);

        //대출처리
        //dueDate은 지정 된 날짜만큼 더해서 저장되도록 한다.
        BookLoanUpdateDto updateDto = BookLoanUpdateDto.builder()
                .bookId(bookId)
                .userId(userId)
                .loanDate(today)
                .dueDate(today.plusDays(policyConfig.getLoanDays()))
                .build();

        repository.save(updateMapper.toEntity(updateDto));
    }

}
