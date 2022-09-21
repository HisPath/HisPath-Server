package com.server.hispath.category.application.dto;

import com.server.hispath.category.domain.ActivityType;
import com.server.hispath.category.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String type;
    private String name;

    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getType().name(), category.getName());
    }
}
