package com.server.hispath.activity.application.dto;

import java.util.Objects;

import com.server.hispath.category.application.dto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartCategoryDto {
    private CategoryDto category;
    private Long myCnt;
    private Double averageCnt;
    private Long totalCnt;


    public ChartCategoryDto(ChartDataDto dto, Long totalActivityCnt) {
        this.category = dto.getCategory();
        this.myCnt = dto.getMyCnt();
        this.averageCnt = dto.getAverageCnt();
        this.totalCnt = totalActivityCnt;
    }

    public boolean isNotETC(){
        return !Objects.equals(category.getName(), "기타");
    }
}
