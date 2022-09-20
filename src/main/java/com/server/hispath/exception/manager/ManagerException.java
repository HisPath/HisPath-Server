package com.server.hispath.exception.manager;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class ManagerException extends HisPathException {
    protected ManagerException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
