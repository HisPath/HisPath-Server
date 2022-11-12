package com.server.hispath.category.application.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartCategoryAvgDto {
    private CategoryDto category;
    private Double avg;

    @QueryProjection
    public ChartCategoryAvgDto(Long categoryId, String categoryName, Double avg) {
        this.category = new CategoryDto(categoryId, categoryName);
        this.avg = avg;
    }
}
