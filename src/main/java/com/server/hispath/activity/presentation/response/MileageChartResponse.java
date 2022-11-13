package com.server.hispath.activity.presentation.response;

import java.util.ArrayList;
import java.util.List;

import com.server.hispath.activity.presentation.response.chart.ChartDataResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageChartResponse {

    private List<ChartDataResponse> chartDatas = new ArrayList<>();
}
