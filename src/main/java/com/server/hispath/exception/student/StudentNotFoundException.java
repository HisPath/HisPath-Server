package com.server.hispath.exception.student;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends StudentException{
    public StudentNotFoundException() {
        super("존재하지 않는 학생입니다.", HttpStatus.BAD_REQUEST);
    }
}
