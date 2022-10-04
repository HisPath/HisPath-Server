package com.server.hispath.exception.oauth;

import org.springframework.http.HttpStatus;

public class UnsupportedOauthProviderException extends OauthException {

    public UnsupportedOauthProviderException() {
        super("지원되지 않는 소셜로그인입니다.", HttpStatus.BAD_REQUEST);
    }
}
