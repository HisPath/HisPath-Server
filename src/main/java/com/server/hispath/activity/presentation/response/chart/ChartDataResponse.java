package com.server.hispath.activity.presentation.response.chart;

import com.server.hispath.activity.application.dto.chart.ChartDataDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataResponse {

    private Long categoryId;
    private String categoryName;
    private Long myCnt;
    private Double averageCnt;

    public static ChartDataResponse of(ChartDataDto dto) {
        return new ChartDataResponse(dto.getCategory().getId(), dto.getCategory().getName(),
                dto.getMyCnt(), dto.getAverageCnt());
    }
}
