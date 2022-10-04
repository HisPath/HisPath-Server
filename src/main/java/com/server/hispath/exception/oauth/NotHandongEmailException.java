package com.server.hispath.exception.oauth;

import org.springframework.http.HttpStatus;

public class NotHandongEmailException extends OauthException {
    public NotHandongEmailException() {
        super("한동대학교 메일이 아닙니다.", HttpStatus.BAD_REQUEST);
    }
}
