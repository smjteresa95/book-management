package com.example.bookmanagement.service;

import com.example.bookmanagement.config.LoanPolicyConfig;
import com.example.bookmanagement.domain.entity.Book;
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
import java.util.concurrent.*;

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
    @DisplayName("3명의 사용자가 동일한 서적을 대출하려고 시도했을 때, 한 명의 사용자에게만 대출을 허용하고, 나머지 사용자에게는 대출이 불가능하다는 응답을 하는 지 확인.")
    public void testConcurrentBookLoan() throws InterruptedException {
        int LOAN_REQUEST_USER = 3;
        long bookId = 1L; //대출 요청이 들어갈 bookId

//        모든 스레드가 startLatch.await()에서 대기하다가,
//        startLatch.countDown()이 한 번 호출되면 모든 스레드가 거의 동시에 작업을 시작하도록 한다.
        CountDownLatch startLatch = new CountDownLatch(1); //1은 대출 가능한 권수와 상관이 없다.
        CountDownLatch doneLatch = new CountDownLatch(LOAN_REQUEST_USER);

        // 대출시도의 성공여부가 Boolean 타입으로 반환된다.
        ExecutorService executorService = Executors.newFixedThreadPool(LOAN_REQUEST_USER);

        for(int i = 0; i < LOAN_REQUEST_USER; i++) {
            final long userId = i + 4; //각 사용자에게 고유한 ID 할당
//            각각 다른 사용자가 동시에 대출요청을 하는 경우를 가정하는 것이기 때문에, userId가 각각 다른 쓰레드를 만들어야 한다.
            executorService.submit(()->{

                    try {
                        startLatch.await(); //모든 쓰레드가 시작하기를 기다림
                        LoanRecordRequestDto requestDto = LoanRecordRequestDto.builder()
                                .bookId(bookId)
                                .userId(userId).build();
                        service.createBookLoan(requestDto); //대출처리 메서드 호출

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                    } finally {
                        doneLatch.countDown();  //작업완료시 doneLatch 감소
                    }
                }
            );

        }

        startLatch.countDown(); //쓰레드 시작
        doneLatch.await(); //모든 쓰레드가 작업을 완료할 때까지 대기

        executorService.shutdown();

        int loanRecordsNum = 0;
        for(int i = 0 ; i < LOAN_REQUEST_USER; i++){
            loanRecordsNum += repository.getAllByUserId(i+4L).size();
        }
        assertThat(loanRecordsNum).isEqualTo(1);

    }


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
        assertThat(loanRecords.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("도서대출처리")
    public void createBookLoanTest(){
        //연체가 있는 사용자가 도서대출을 하려고 할 때 예외처리
        long overdueUserId  = 2L; //연체가 있는 사용자
        long availableBookId = 1L; //재고가 있는 도서
        LoanRecordRequestDto overDueRequestDto = LoanRecordRequestDto.builder()
                .userId(overdueUserId).bookId(availableBookId).build();
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
