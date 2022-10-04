package com.server.hispath.exception.oauth;

import com.server.hispath.exception.MyPathLoginException;

import org.springframework.http.HttpStatus;

public abstract class OauthException extends MyPathLoginException {

    protected OauthException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
