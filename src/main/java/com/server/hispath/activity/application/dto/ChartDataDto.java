package com.server.hispath.activity.application.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataDto {

    private Long categoryId;
    private String categoryName;
    private Long myCnt;
    private Long averageCnt;

    @QueryProjection
    public ChartDataDto(Long categoryId, String categoryName, Long myCnt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.myCnt = myCnt;
        this.averageCnt = 0L;
    }
}
