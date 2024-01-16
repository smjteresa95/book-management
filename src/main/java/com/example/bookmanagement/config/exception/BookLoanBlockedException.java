package com.example.bookmanagement.config.exception;

public class BookLoanBlockedException extends RuntimeException {
    public BookLoanBlockedException(String message) {
        super(message);
    }
}
