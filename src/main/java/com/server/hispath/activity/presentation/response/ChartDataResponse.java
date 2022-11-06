package com.server.hispath.activity.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataResponse {

    private Long categoryId;
    private Long categoryName;
    private int myCnt;
    private int averageCnt;

}
