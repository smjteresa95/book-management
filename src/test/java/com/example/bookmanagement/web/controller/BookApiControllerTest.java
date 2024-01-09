package com.example.bookmanagement.web.controller;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.web.dto.BookRequestDto;
import com.example.bookmanagement.web.dto.BookResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookApiController.class)
public class BookApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService service;
    @MockBean
    BookRepository repository;

    @Test
    @WithMockUser
    public void getBookTest() throws Exception {
        Long id = 1L;
        BookResponseDto bookDto = BookResponseDto.builder()
                .majorCategoryId(0)
                .subCategoryId(1)
                .title("일하는 마음")
                .isbn("9791160560626")
                .author("제현주")
                .publisher("어크로스")
                .publicationDate(LocalDate.of(2018,8,11))
                .availableCopies(3)
                .build();

        when(service.getBook(id)).thenReturn(bookDto);

        mockMvc.perform(get("/api/book/{id}", id))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser //인증된 사용자
    public void saveBookTest() throws Exception {
        //given
        BookRequestDto correctDto = BookRequestDto.builder()
                .majorCategoryId(0)
                .subCategoryId(1)
                .title("일하는 마음")
                .isbn("9791160560626")
                .author("제현주")
                .publisher("어크로스")
                .publicationDate(LocalDate.of(2018,8,11))
                .availableCopies(3)
                .build();


        //제대로 된 도서정보를 저장하려고 할 때,
        String json = objectMapper.writeValueAsString(correctDto);

        ResultActions actions = mockMvc.perform(post("/api/book")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())); //Spring Security가 적용되면서 활성화 된 CSRF(Cross-Site Request Forgery)을 비활성화

        actions.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void updateBookTest() throws Exception {

        //given
        Long id = 1L;

        Book initialBook = Book.builder()
                .majorCategoryId(0)
                .subCategoryId(1)
                .title("일하는 마음")
                .isbn("9791160560626")
                .author("제현주")
                .publisher("어크로스")
                .publicationDate(LocalDate.of(2018,8,11))
                .availableCopies(3)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(initialBook));

        BookRequestDto requestDto = BookRequestDto.builder()
                .subCategoryId(2)
                .availableCopies(4).build();

        mockMvc.perform(patch("/api/book/{id}", id)
                .content(objectMapper.writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk());

    }


}
