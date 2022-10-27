package com.server.hispath.exception.activity;

import org.springframework.http.HttpStatus;

public class ParticipantDuplicateException extends ActivityException {
    public ParticipantDuplicateException(String studentNum, String name) {
        super("이미 존재하는 참여자입니다.\n" + "학번: " + studentNum + " 이름: " + name, HttpStatus.BAD_REQUEST);
    }
}
