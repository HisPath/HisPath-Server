package com.server.hispath.activity.presentation.response.chart;

import com.server.hispath.activity.application.dto.ChartDepartmentDataDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDepartmentResponse {
    private String department;
    private Long cnt;

    public static ChartDepartmentResponse of(ChartDepartmentDataDto dto) {
        return new ChartDepartmentResponse(dto.getDepartment(), dto.getCnt());
    }
}
