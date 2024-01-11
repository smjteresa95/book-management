package com.example.bookmanagement.service;

import com.example.bookmanagement.config.LoanPolicyConfig;
import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.enums.BookStatus;
import com.example.bookmanagement.exception.BookLoanBlockedException;
import com.example.bookmanagement.web.dto.LoanRecordRequestDto;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = {"/db/h2/schema.sql", "/db/h2/data.sql"})
public class LoanRecordServiceTest {

    @Autowired
    LoanRecordRepository repository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LoanRecordService service;

    @Autowired
    LoanPolicyConfig policyConfig;

    @Test
    public void getAllLoanRecordByUserIdTest(){
        long userId = 1L;
        List<LoanRecordResponseDto> responseDto = service.getAllByUserId(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String retrievedDate = responseDto.get(1).getLoanDate().format(formatter);

        assertThat(responseDto.size()).isEqualTo(2);
        assertThat(retrievedDate).isEqualTo("2024-01-10");
    }

    @Test
    public void getAllByBookIdTest(){
        long bookId = 2L;
        List<LoanRecord> loanRecords = repository.getAllByBookId(bookId);
        assertThat(loanRecords.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("도서대출처리")
    public void createBookLoanTest(){
        //연체가 있는 사용자가 도서대출을 하려고 할 때 예외처리
        long overdueUserId  = 2L; //연체가 있는 사용자
        long availableBookId = 1L; //재고가 있는 도서
        LoanRecordRequestDto overDueRequestDto = LoanRecordRequestDto.builder()
                .userId(overdueUserId ).bookId(availableBookId).build();
        assertThrows(BookLoanBlockedException.class, () -> service.createBookLoan(overDueRequestDto));

        //연체가 없는 사용자가 잔여가 없는 도서를 대출하려고 할 때 예외처리
        long userId = 1L; //연체가 없는 사용자
        long unavailableBookId = 2L; //재고가 없는 도서
        LoanRecordRequestDto unavailableRequestDto = LoanRecordRequestDto.builder()
                .userId(userId).bookId(unavailableBookId).build();
        assertThrows(BookLoanBlockedException.class, () -> service.createBookLoan(unavailableRequestDto));

        //대여 가능 권수를 넘어간 사용자가 대여 하려고 할때 예외처리
        long maxUserId = 3L; //대여가능 권수를 채운 사용자
        LoanRecordRequestDto maxRequestDto = LoanRecordRequestDto.builder()
                .userId(maxUserId ).bookId(availableBookId).build();
        assertThrows(BookLoanBlockedException.class, () -> service.createBookLoan(maxRequestDto));

        //대출에 문제 없는 도서와 사용자에 대한 처리
        LoanRecordRequestDto requestDto = LoanRecordRequestDto.builder()
                .userId(userId).bookId(availableBookId).build();
        service.createBookLoan(requestDto);
        //도서 상태를 대출불가로 변경되었는지 확인, dueDate 자동적으로 값 들어가야 함
        assertThat(bookRepository.findById(availableBookId).get().getStatus())
                .isEqualTo(BookStatus.LOANED);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertThat(repository.getAllByUserId(userId).get(2).getDueDate().format(formatter))
                .isEqualTo(LocalDateTime.now().plusDays(policyConfig.getLoanDays()).format(formatter));

    }

    @Test
    @DisplayName("도서반납처리")
    public void bookReturnTest(){
        long userId = 1L;
        long bookId = 1L;
        LoanRecordRequestDto requestDto = LoanRecordRequestDto.builder()
                .bookId(bookId).userId(userId).build();

        service.returnBook(requestDto);

        //도서 상태가 대출가능으로 변경되었는지 확인
        assertThat(bookRepository.findById(bookId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 도서"))
                .getStatus())
                .isEqualTo(BookStatus.AVAILABLE);

        //loanRecord의 returnDate에 값이 들어갔는 지 확인
        LoanRecord updatedRecord = repository.getReturnedBook(bookId, userId).orElseThrow(()-> new NoSuchElementException("대출 이력 조회 불가"));
        assertThat(updatedRecord.getReturnDate()).isNotNull();
        assertThat(updatedRecord.getBookId()).isEqualTo(bookId);
    }

}
