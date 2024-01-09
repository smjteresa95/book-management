package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.web.dto.BookRequestDto;
import com.example.bookmanagement.web.dto.BookResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
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
                .majorCategoryId(0)
                .subCategoryId(1)
                .title("일하는 마음")
                .isbn("9791160560626")
                .author("제현주")
                .publisher("어크로스")
                .publicationDate(LocalDate.of(2018,8,11))
                .availableCopies(3)
        .build();

        service.saveBook(dto);

        List<Book> books = repository.findAll();
        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(1).getIsbn()).isEqualTo("9791160560626");
        assertThat(books.get(1).getTitle()).isEqualTo("일하는 마음");
    }

    @Test
    @Transactional
    public void updateBookTest(){
        //given
        Long bookId = 1L;

        BookRequestDto requestDto = BookRequestDto.builder()
                .availableCopies(5).build();
        //when
        service.updateBook(bookId, requestDto);

        //then
        Book updatedBook = repository.findById(1L).get();
        assertThat(updatedBook.getTitle()).isEqualTo("역행자");
        assertThat(updatedBook.getAuthor()).isEqualTo("자청");
        assertThat(updatedBook.getAvailableCopies()).isEqualTo(requestDto.getAvailableCopies());
    }
}
