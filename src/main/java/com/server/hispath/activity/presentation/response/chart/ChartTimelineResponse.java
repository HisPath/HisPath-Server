package com.server.hispath.activity.presentation.response.chart;

import com.server.hispath.activity.application.dto.ChartTimelineDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartTimelineResponse {
    private String semester;
    private int totalWeight;

    public static ChartTimelineResponse of(ChartTimelineDto dto) {
        return new ChartTimelineResponse(dto.getSemester(), dto.getTotalWeight());
    }
}
