package com.server.hispath.category.application.dto;

import com.server.hispath.category.domain.ActivityType;
import com.server.hispath.category.presentation.request.CategoryCURequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCUDto {
    private ActivityType type;
    private String name;

    public static CategoryCUDto of(CategoryCURequest request) {
        return new CategoryCUDto(ActivityType.valueOf(request.getType()), request.getName());
    }
}
