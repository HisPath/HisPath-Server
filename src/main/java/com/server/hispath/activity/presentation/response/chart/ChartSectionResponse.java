package com.server.hispath.activity.presentation.response.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartSectionResponse {

    private String section;
    private Long myCnt;
    private Double avgCnt;
}
