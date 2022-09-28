package com.server.hispath.exception.common;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class CommonException extends HisPathException {
    public CommonException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
