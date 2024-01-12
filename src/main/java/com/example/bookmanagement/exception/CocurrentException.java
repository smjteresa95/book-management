package com.example.bookmanagement.exception;

import jakarta.persistence.OptimisticLockException;

public class CocurrentException extends OptimisticLockException {
    public CocurrentException(String message) {
        super(message);
    }

}
