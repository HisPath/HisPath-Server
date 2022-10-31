package com.server.hispath.exception.scholarship;

import org.springframework.http.HttpStatus;

public class ScholarshipNotMatchException extends ScholarshipException {
    public ScholarshipNotMatchException(String msg) {
        super("입력 맞지 않는 내역입니다.\n" + msg, HttpStatus.BAD_REQUEST);
    }
}
