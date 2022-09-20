package com.server.hispath.category.presentation;

import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.application.dto.CategoryCreateDto;
import com.server.hispath.category.presentation.request.CategoryCreateRequest;
import com.server.hispath.category.presentation.response.CategoryResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Long> create(@RequestBody CategoryCreateRequest request) {
        Long savedId = categoryService.create(CategoryCreateDto.of(request));
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> find(@PathVariable Long id) {
        CategoryContentDto dto = categoryService.findById(id);
        CategoryResponse response = CategoryResponse.from(dto);
        return ResponseEntity.ok(response);
    }
}
