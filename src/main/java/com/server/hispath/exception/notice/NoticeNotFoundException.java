package com.server.hispath.exception.notice;

import org.springframework.http.HttpStatus;

public class NoticeNotFoundException extends NoticeException {
    public NoticeNotFoundException() {
        super("존재하지 않는 공지입니다.", HttpStatus.BAD_REQUEST);
    }
}
