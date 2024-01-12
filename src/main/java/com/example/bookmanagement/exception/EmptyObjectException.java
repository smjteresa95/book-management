package com.example.bookmanagement.exception;

public class EmptyObjectException extends RuntimeException{

    public EmptyObjectException() {
        super("Object is empty");
    }

    public EmptyObjectException(String message) {
        super(message);
    }

}
