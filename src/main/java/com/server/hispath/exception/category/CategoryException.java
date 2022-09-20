package com.server.hispath.exception.category;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class CategoryException extends HisPathException {
    protected CategoryException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
