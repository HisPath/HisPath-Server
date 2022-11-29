package com.server.hispath.exception.activity;

import com.server.hispath.exception.HisPathException;

import org.springframework.http.HttpStatus;

public class ActivityApplyException extends ActivityException {
    public ActivityApplyException() {
        super("이미 처리된 활동은 다시 신청할 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}

