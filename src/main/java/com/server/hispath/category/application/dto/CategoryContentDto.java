package com.server.hispath.category.application.dto;

import com.server.hispath.category.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryContentDto {
    private Long id;
    private String name;

    public static CategoryContentDto from(Category category) {
        return new CategoryContentDto(category.getId(), category.getName());
    }
}
