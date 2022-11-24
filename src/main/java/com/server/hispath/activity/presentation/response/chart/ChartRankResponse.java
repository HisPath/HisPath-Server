package com.server.hispath.activity.presentation.response.chart;

import com.server.hispath.activity.application.dto.chart.ChartRankDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartRankResponse {
    private int myTotalWeight;
    private double avgTotalWeight;
    private int maxTotalWeight;

    public static ChartRankResponse of(ChartRankDto dto){
        return new ChartRankResponse(dto.getMyTotalWeight(), dto.getAvgTotalWeight(), dto.getMaxTotalWeight());
    }
}
