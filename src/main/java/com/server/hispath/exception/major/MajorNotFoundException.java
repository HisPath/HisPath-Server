package com.server.hispath.exception.major;

import org.springframework.http.HttpStatus;

public class MajorNotFoundException extends MajorException {
    public MajorNotFoundException() {
        super("존재하지 않는 전공입니다.", HttpStatus.BAD_REQUEST);
    }
}
