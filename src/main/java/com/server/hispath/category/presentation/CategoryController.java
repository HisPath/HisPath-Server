package com.server.hispath.category.presentation;

import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.presentation.request.CategoryCreateRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Long> create(@RequestBody CategoryCreateRequest request){
        Long savedId = categoryService.create(CategoryContentDto.of(request));
        return ResponseEntity.ok(savedId);
    }
}
