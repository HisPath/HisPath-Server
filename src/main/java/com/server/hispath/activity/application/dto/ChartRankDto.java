package com.server.hispath.activity.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartRankDto {

    private int myTotalWeight;
    private double avgTotalWeight;
    private int maxTotalWeight;

}
