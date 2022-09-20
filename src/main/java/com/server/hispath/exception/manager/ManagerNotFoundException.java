package com.server.hispath.exception.manager;

import org.springframework.http.HttpStatus;

public class ManagerNotFoundException extends ManagerException {
    public ManagerNotFoundException() {
        super("존재하지 않는 관리자입니다.", HttpStatus.BAD_REQUEST);
    }
}
