package com.server.hispath.exception.activity;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class ActivityException extends HisPathException {
    protected ActivityException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
