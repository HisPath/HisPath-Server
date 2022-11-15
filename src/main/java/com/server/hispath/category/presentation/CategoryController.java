package com.server.hispath.category.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.application.dto.CategoryCUDto;
import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.presentation.request.CategoryCURequest;
import com.server.hispath.category.presentation.response.CategoryResponse;
import com.server.hispath.docs.ApiDoc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/category")
    @ApiOperation(value = ApiDoc.CATEGORY_CREATE)
    @RequiredManagerLogin
    public ResponseEntity<Long> create(@RequestBody CategoryCURequest request) {
        Long savedId = categoryService.create(CategoryCUDto.of(request));
        return ResponseEntity.ok(savedId);
    }


    @GetMapping("/category/{id}")
    @ApiOperation(value = ApiDoc.CATEGORY_READ)
    @RequiredLogin
    public ResponseEntity<CategoryResponse> find(@PathVariable Long id) {
        CategoryContentDto dto = categoryService.find(id);
        CategoryResponse response = CategoryResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    @ApiOperation(value = ApiDoc.CATEGORY_READ_ALL)
    @RequiredLogin
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryContentDto> dtos = categoryService.findAll();
        List<CategoryResponse> responses = dtos.stream()
                                               .map(CategoryResponse::from)
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/category/{id}")
    @ApiOperation(value = ApiDoc.CATEGORY_UPDATE)
    @RequiredManagerLogin
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryCURequest request) {
        CategoryContentDto dto = categoryService.update(id, CategoryCUDto.of(request));
        CategoryResponse response = CategoryResponse.from(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/category/{id}")
    @ApiOperation(value = ApiDoc.CATEGORY_DELETE)
    @RequiredManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(id);
    }
}
