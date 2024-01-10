package com.example.bookmanagement.exception;

public class BookLoanBlockedException extends RuntimeException {
    public BookLoanBlockedException(String message) {
        super(message);
    }
}
