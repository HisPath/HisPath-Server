package com.server.hispath.exception.student;

import org.springframework.http.HttpStatus;

public class StudentDataNotMatchException extends StudentException {
    public StudentDataNotMatchException(String studentNum, String name) {
        super("정보가 일치하지 않습니다.\n 학번 : " + studentNum + "\n 이름 : " + name, HttpStatus.BAD_REQUEST);
    }
}
