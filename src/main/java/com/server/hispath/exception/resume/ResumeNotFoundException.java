package com.server.hispath.exception.resume;

import org.springframework.http.HttpStatus;

public class ResumeNotFoundException extends ResumeException {
    public ResumeNotFoundException() {
        super("존재하지 않는 이력서입니다.", HttpStatus.BAD_REQUEST);
    }
}
