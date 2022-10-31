package com.server.hispath.exception.scholarship;

import com.server.hispath.exception.resume.ResumeException;

import org.springframework.http.HttpStatus;

public class ScholarshipNotFoundException extends ScholarshipException {
    public ScholarshipNotFoundException() {
        super("존재하지 않는 장학금 수혜 내역입니다.", HttpStatus.BAD_REQUEST);
    }
}
