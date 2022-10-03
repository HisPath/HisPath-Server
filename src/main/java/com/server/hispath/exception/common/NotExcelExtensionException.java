package com.server.hispath.exception.common;

import org.springframework.http.HttpStatus;

public class NotExcelExtensionException extends CommonException{
    public NotExcelExtensionException() {
        super("Excel양식의 파일이 아닙니다.", HttpStatus.BAD_REQUEST);
    }
}
