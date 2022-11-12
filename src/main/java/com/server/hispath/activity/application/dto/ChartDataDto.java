package com.server.hispath.activity.application.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.server.hispath.category.application.dto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataDto {

    private CategoryDto category;
    private Long myCnt;
    private Long averageCnt;

    @QueryProjection
    public ChartDataDto(Long categoryId, String categoryName, Long myCnt) {
        this.category = new CategoryDto(categoryId, categoryName);
        this.myCnt = myCnt;
        this.averageCnt = 0L;
    }
}
