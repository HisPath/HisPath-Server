package com.server.hispath.category.application.dto;

import com.server.hispath.category.domain.ActivityType;
import com.server.hispath.category.presentation.request.CategoryCreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {
    private ActivityType type;
    private String name;

    public static CategoryCreateDto of(CategoryCreateRequest request) {
        return new CategoryCreateDto(ActivityType.valueOf(request.getType()), request.getName());
    }
}
