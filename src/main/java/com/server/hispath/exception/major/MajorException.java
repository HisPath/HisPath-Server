package com.server.hispath.exception.major;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class MajorException extends HisPathException {
    protected MajorException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
