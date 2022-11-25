package com.server.hispath.activity.presentation.response.chart;

import com.server.hispath.activity.application.dto.chart.ChartCategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartCategoryResponse {
    private Long categoryId;
    private String categoryName;
    private Long myCnt;
    private Double averageCnt;
    private Long totalCategoryCnt;

    public static ChartCategoryResponse of(ChartCategoryDto dto) {
        return new ChartCategoryResponse(dto.getCategory().getId(), dto.getCategory().getName(),
                dto.getMyCnt(), dto.getAverageCnt(), dto.getTotalCnt());
    }
}
