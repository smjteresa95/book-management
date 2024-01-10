package com.example.bookmanagement.web.controller;

import com.example.bookmanagement.domain.repository.BookRepository;
import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.service.LoanRecordService;
import com.example.bookmanagement.web.dto.BookLoanRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookLoanApiController.class)
public class BookLoanApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;
    @MockBean
    LoanRecordService loanService;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    LoanRecordRepository loanRepository;

    @Test
    @WithMockUser
    public void loanBookTest() throws Exception {
        long userId = 1L;
        long bookId = 1L;
        BookLoanRequestDto requestDto = BookLoanRequestDto.builder()
                .bookId(bookId)
                .userId(userId).build();
        String requestJson = objectMapper.writeValueAsString(requestDto);
        mockMvc.perform(post("/api/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                        .with(csrf()))
                .andExpect(status().isCreated());
    }
}
