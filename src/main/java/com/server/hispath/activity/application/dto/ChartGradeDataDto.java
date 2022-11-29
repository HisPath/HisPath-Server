package com.server.hispath.activity.application.dto;


import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChartGradeDataDto {

    private int grade;
    private Long cnt;

    @QueryProjection
    public ChartGradeDataDto(int grade, Long cnt) {
        this.grade = grade;
        this.cnt = cnt;
    }

    public void addCnt(Long cnt) {
        this.cnt += cnt;
    }
}
