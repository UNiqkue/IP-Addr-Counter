package com.codereview.counter.exception;

public class WrongFormatException extends RuntimeException {
    public WrongFormatException(String message) {
        super(message);
    }
}
