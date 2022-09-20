package com.server.hispath.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends CategoryException {
    public CategoryNotFoundException() {
        super("존재하지 않는 카테고리입니다.", HttpStatus.BAD_REQUEST);
    }
}
