package com.server.hispath.category.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.application.dto.CategoryCUDto;
import com.server.hispath.category.presentation.request.CategoryCURequest;
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
    public ResponseEntity<Long> create(@RequestBody CategoryCURequest request) {
        Long savedId = categoryService.create(CategoryCUDto.of(request));
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> find(@PathVariable Long id) {
        CategoryContentDto dto = categoryService.findById(id);
        CategoryResponse response = CategoryResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryContentDto> dtos = categoryService.findAll();
        List<CategoryResponse> responses = dtos.stream()
                                               .map(CategoryResponse::from)
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryCURequest request) {
        CategoryContentDto dto = categoryService.update(id, CategoryCUDto.of(request));
        CategoryResponse response = CategoryResponse.from(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(id);
    }
}
