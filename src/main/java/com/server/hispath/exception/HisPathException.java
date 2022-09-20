package com.server.hispath.exception;

import org.springframework.http.HttpStatus;

public class HisPathException extends RuntimeException{
    private final HttpStatus httpStatus;

    public HisPathException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
