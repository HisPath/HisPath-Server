package com.server.hispath.exception.student;

import org.springframework.http.HttpStatus;

public class SectionNotExistException extends StudentException{
    public SectionNotExistException() {
        super("존재하지 않는 섹션입니다.", HttpStatus.BAD_REQUEST);
    }
}
