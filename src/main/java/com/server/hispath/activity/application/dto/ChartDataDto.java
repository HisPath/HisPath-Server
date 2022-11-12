package com.server.hispath.activity.application.dto;

import com.server.hispath.category.application.dto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataDto {

    private CategoryDto category;
    private Long myCnt;
    private Double averageCnt;

    public ChartDataDto(ChartCategoryCntDto dto, Long totalCnt, int totalStudent) {
        this.category = dto.getCategory();
        this.myCnt = dto.getCnt();
        this.averageCnt = totalCnt / (double) totalStudent;
    }

    public ChartDataDto(CategoryDto dto, Long totalCnt, int totalStudent) {
        this.category = dto;
        this.myCnt = 0L;
        this.averageCnt = totalCnt / (double) totalStudent;
    }
}
