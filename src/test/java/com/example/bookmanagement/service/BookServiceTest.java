package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.enums.BookStatus;
import com.example.bookmanagement.web.dto.BookRequestDto;
import com.example.bookmanagement.web.dto.BookResponseDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Sql(scripts = {"/db/h2/schema.sql", "/db/h2/data.sql"})
public class BookServiceTest {

    @Autowired
    private BookRepository repository;
    @Autowired
    private BookService service;

    @Test
    public void getBookTest(){
        long id = 1L;
        BookResponseDto responseDto = service.getBook(id);
        assertThat(responseDto.getAuthor()).isEqualTo("자청");
    }

    @Test
    public void saveBookTest(){
        BookRequestDto dto = BookRequestDto.builder()
                .majorCategoryId(10)
                .subCategoryId(5)
                .title("일하는 마음")
                .isbn("9791160560626")
                .author("제현주")
                .publisher("어크로스")
                .publicationDate(LocalDate.of(2018,8,11))
        .build();

        service.saveBook(dto);

        List<Book> books = repository.findAll();
        //8개의 도서가 이미 등록 되어있음.
        assertThat(books.size()).isEqualTo(9);
        assertThat(books.get(8).getIsbn()).isEqualTo("9791160560626");
        assertThat(books.get(8).getTitle()).isEqualTo("일하는 마음");
    }

    @Test
    @Transactional
    public void updateBookTest(){
        //given
        Long bookId = 1L;

        BookRequestDto requestDto = BookRequestDto.builder()
                .status(BookStatus.LOANED).build();
        //when
        service.updateBook(bookId, requestDto);

        //then
        Book updatedBook = repository.findById(1L).get();
        assertThat(updatedBook.getTitle()).isEqualTo("역행자");
        assertThat(updatedBook.getAuthor()).isEqualTo("자청");
        assertThat(updatedBook.getStatus()).isEqualTo(requestDto.getStatus());
    }
}
