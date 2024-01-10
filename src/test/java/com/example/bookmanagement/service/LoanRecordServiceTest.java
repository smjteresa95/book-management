package com.example.bookmanagement.service;

import com.example.bookmanagement.config.LoanPolicyConfig;
import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.exception.BookLoanBlockedException;
import com.example.bookmanagement.web.dto.BookLoanRequestDto;
import com.example.bookmanagement.web.dto.BookLoanResponseDto;
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
    public void getAllLoanRecordByBookIdTest(){
        long bookId = 2L;
        List<BookLoanResponseDto> responseDto = service.getAllByBookId(bookId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String retrievedDate = responseDto.get(0).getLoanDate().format(formatter);

        assertThat(responseDto.size()).isEqualTo(3);
        assertThat(retrievedDate).isEqualTo("2024-01-02");
    }

    @Test
    public void getAllLoanRecordByUserIdTest(){
        long userId = 1L;
        List<BookLoanResponseDto> responseDto = service.getAllByUserId(userId);

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
        BookLoanRequestDto overDueRequestDto = BookLoanRequestDto.builder()
                .userId(overdueUserId ).bookId(availableBookId).build();
        assertThrows(BookLoanBlockedException.class, () -> service.createBookLoan(overDueRequestDto));

        //연체가 없는 사용자가 잔여가 없는 도서를 대출하려고 할 때 예외처리
        long userId = 1L; //연체가 없는 사용자
        long unavailableBookId = 2L; //재고가 없는 도서
        BookLoanRequestDto unavailableRequestDto = BookLoanRequestDto.builder()
                .userId(userId).bookId(unavailableBookId).build();
        assertThrows(BookLoanBlockedException.class, () -> service.createBookLoan(unavailableRequestDto));

        //대여 가능 권수를 넘어간 사용자가 대여 하려고 할때 예외처리
        long maxUserId = 3L; //대여가능 권수를 채운 사용자
        BookLoanRequestDto maxRequestDto = BookLoanRequestDto.builder()
                .userId(maxUserId ).bookId(availableBookId).build();
        assertThrows(BookLoanBlockedException.class, () -> service.createBookLoan(maxRequestDto));

        //대출에 문제 없는 도서와 사용자에 대한 처리
        BookLoanRequestDto requestDto = BookLoanRequestDto.builder()
                .userId(userId).bookId(availableBookId).build();
        service.createBookLoan(requestDto);
        //도서의 대여가능 권수 1->0로 변경, dueDate 자동적으로 값 들어가야 함
        assertThat(bookRepository.findById(availableBookId).get().getAvailableCopies()).isEqualTo(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertThat(repository.getAllByUserId(userId).get(2).getDueDate().format(formatter))
                .isEqualTo(LocalDateTime.now().plusDays(policyConfig.getLoanDays()).format(formatter));

    }

}
