package com.server.hispath.exception.common;

import org.springframework.http.HttpStatus;

public class ExcelDataFormatException extends CommonException{
    public ExcelDataFormatException(String data) {
        super("등록하려는 데이터의 양식에 문제가 있습니다.\n 문제되는 데이터 : " + data, HttpStatus.BAD_REQUEST);
    }
}
