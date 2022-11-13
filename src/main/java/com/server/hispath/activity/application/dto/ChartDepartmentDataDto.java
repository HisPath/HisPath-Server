package com.server.hispath.activity.application.dto;


import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChartDepartmentDataDto {

    private String department;
    private Long cnt;

    @QueryProjection
    public ChartDepartmentDataDto(String department, Long cnt) {
        this.department = department;
        this.cnt = cnt;
    }
}
