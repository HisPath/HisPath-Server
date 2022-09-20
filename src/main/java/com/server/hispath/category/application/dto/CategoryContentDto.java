package com.server.hispath.category.application.dto;

import com.server.hispath.category.domain.ActivityType;
import com.server.hispath.category.presentation.request.CategoryCreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryContentDto {
    private ActivityType type;
    private String name;

    public static CategoryContentDto of(CategoryCreateRequest request) {
        return new CategoryContentDto(ActivityType.valueOf(request.getType()), request.getName());
    }
}
