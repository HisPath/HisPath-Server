package com.server.hispath.activity.application.dto;

import java.util.Objects;

import com.querydsl.core.annotations.QueryProjection;
import com.server.hispath.category.application.dto.CategoryDto;

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

    @QueryProjection
    public ChartCategoryCntDto(Long categoryId, String categoryName, Integer cnt) {
        this.category = new CategoryDto(categoryId, categoryName);
        this.cnt = cnt.longValue();
    }

    public ChartCategoryCntDto(CategoryDto category) {
        this.category = category;
        this.cnt = 0L;
    }

    public boolean isSameCategory(CategoryDto categoryDto){
        return Objects.equals(categoryDto.getId(), this.category.getId());
    }
}
