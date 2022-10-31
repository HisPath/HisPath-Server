package com.server.hispath.exception.scholarship;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class ScholarshipException extends HisPathException {
    protected ScholarshipException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
