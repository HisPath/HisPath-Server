package com.server.hispath.exception.student;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class StudentException extends HisPathException {
    protected StudentException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
