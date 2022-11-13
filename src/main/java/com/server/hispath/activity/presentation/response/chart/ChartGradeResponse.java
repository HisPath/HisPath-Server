package com.server.hispath.activity.presentation.response.chart;

import com.server.hispath.activity.application.dto.ChartGradeDataDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartGradeResponse {
    private int grade;
    private Long cnt;

    public static ChartGradeResponse of(ChartGradeDataDto dto) {
        return new ChartGradeResponse(dto.getGrade(), dto.getCnt());
    }
}
