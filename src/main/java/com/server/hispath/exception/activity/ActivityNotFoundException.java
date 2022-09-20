package com.server.hispath.exception.activity;

import org.springframework.http.HttpStatus;

public class ActivityNotFoundException extends ActivityException {
    public ActivityNotFoundException() {
        super("존재하지 않는 활동입니다.", HttpStatus.BAD_REQUEST);
    }
}
