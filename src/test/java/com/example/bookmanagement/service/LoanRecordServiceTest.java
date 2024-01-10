package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = {"/db/h2/schema.sql", "/db/h2/data.sql"})
public class LoanRecordServiceTest {

    @Autowired
    LoanRecordRepository repository;
    @Autowired
    LoanRecordService service;

    @Test
    public void getAllLoanRecordByBookIdTest(){
        long bookId = 2L;
        List<LoanRecordResponseDto> responseDto = service.getAllByBookId(bookId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String retrievedDate = responseDto.get(0).getLoanDate().format(formatter);

        assertThat(responseDto.size()).isEqualTo(1);
        assertThat(retrievedDate).isEqualTo("2024-01-02");
    }

    @Test
    public void getAllLoanRecordByUserIdTest(){
        long userId = 1L;
        List<LoanRecordResponseDto> responseDto = service.getAllByUserId(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String retrievedDate = responseDto.get(1).getLoanDate().format(formatter);

        assertThat(responseDto.size()).isEqualTo(2);
        assertThat(retrievedDate).isEqualTo("2024-01-04");
    }

    @Test
    public void getAllByBookIdTest(){
        long bookId = 2L;
        List<LoanRecord> loanRecords = repository.getAllByBookId(bookId);
        assertThat(loanRecords.size()).isEqualTo(1);
    }

}
