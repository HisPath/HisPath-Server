package com.server.hispath.exception.notice;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class NoticeException extends HisPathException {
    protected NoticeException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
