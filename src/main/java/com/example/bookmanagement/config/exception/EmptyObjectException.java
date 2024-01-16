package com.example.bookmanagement.config.exception;

public class EmptyObjectException extends RuntimeException{

    public EmptyObjectException() {
        super("Object is empty");
    }

    public EmptyObjectException(String message) {
        super(message);
    }

}
