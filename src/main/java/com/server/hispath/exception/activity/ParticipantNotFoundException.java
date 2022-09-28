package com.server.hispath.exception.activity;

import org.springframework.http.HttpStatus;

public class ParticipantNotFoundException extends ActivityException {
    public ParticipantNotFoundException() {
        super("존재하지 않는 참여자 정보입니다.", HttpStatus.BAD_REQUEST);
    }
}
