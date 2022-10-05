package com.server.hispath.exception.authorization;

import com.server.hispath.exception.MyPathLoginException;

import org.springframework.http.HttpStatus;

public abstract class AuthorizationException extends MyPathLoginException {

    protected AuthorizationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
