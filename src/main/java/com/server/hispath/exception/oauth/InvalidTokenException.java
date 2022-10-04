package com.server.hispath.exception.oauth;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends OauthException {
    public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);
    }
}
