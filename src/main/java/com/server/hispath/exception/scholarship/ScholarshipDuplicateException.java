package com.server.hispath.exception.scholarship;

import org.springframework.http.HttpStatus;

public class ScholarshipDuplicateException extends ScholarshipException {
    public ScholarshipDuplicateException() {
        super("중복되는 학생의 정보가 존재합니다.", HttpStatus.BAD_REQUEST);
    }
}
