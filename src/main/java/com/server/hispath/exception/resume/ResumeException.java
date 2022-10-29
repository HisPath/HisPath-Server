package com.server.hispath.exception.resume;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class ResumeException extends HisPathException {
    protected ResumeException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
