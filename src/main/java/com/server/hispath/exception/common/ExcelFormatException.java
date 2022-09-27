package com.server.hispath.exception.common;

import org.springframework.http.HttpStatus;

public class ExcelFormatException extends CommonException{
    public ExcelFormatException(String msg) {
        super("등록하려는 데이터의 양식에 문제가 있습니다. : " + msg, HttpStatus.BAD_REQUEST);
    }
}
