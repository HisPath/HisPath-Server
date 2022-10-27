package com.server.hispath.exception.manager;

import org.springframework.http.HttpStatus;

public class ManagerNoAuthorizationException extends ManagerException {
    public ManagerNoAuthorizationException() {
        super("사용 권한이 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
