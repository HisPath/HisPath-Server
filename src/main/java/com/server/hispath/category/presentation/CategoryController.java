package com.server.hispath.category.presentation;

import com.server.hispath.category.application.CategoryService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
}
