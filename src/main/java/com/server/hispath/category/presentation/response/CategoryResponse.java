package com.server.hispath.category.presentation.response;

import com.server.hispath.category.application.dto.CategoryContentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String name;

    public static CategoryResponse from(CategoryContentDto dto) {
        return new CategoryResponse(dto.getId(), dto.getName());
    }
}