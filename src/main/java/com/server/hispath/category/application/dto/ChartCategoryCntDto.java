package com.server.hispath.category.application.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartCategoryCntDto {
    private CategoryDto category;
    private Long cnt;

    @QueryProjection
    public ChartCategoryCntDto(Long categoryId, String categoryName, Long cnt) {
        this.category = new CategoryDto(categoryId, categoryName);
        this.cnt = cnt;
    }
}
