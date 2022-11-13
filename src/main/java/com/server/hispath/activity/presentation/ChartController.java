package com.server.hispath.activity.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ChartSearchRequestDto;
import com.server.hispath.activity.presentation.response.chart.*;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.scholarship.application.ScholarshipService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChartController {

    private final ActivityService activityService;
    private final ScholarshipService scholarshipService;

    @GetMapping("/chart/mileage")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_CATEGORY)
    public ResponseEntity<List<ChartDataResponse>> getChartMileageData(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department
    ) {
        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department);
        // ToDo 지금은 1L로 하지만 나중에 바꿀 예정
        List<ChartDataResponse> responses = activityService.getChartDatasByCategory(1L, chartSearchRequestDto)
                                                           .stream()
                                                           .map(ChartDataResponse::of)
                                                           .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/popularity")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_POPULARITY_CATEGORY)
    public ResponseEntity<List<ChartCategoryResponse>> getChartMileagePopularity(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department
    ) {
        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department);
        // ToDo 지금은 1L로 하지만 나중에 바꿀 예정
        List<ChartCategoryResponse> responses = activityService.getChartTotalDatasByCategory(1L, chartSearchRequestDto)
                                                               .stream()
                                                               .map(ChartCategoryResponse::of)
                                                               .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/rank")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_RANK)
    public ResponseEntity<ChartRankResponse> getChartMileageRank(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department
    ) {
        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department);
        // ToDo 지금은 1L로 하지만 나중에 바꿀 예정
        return ResponseEntity.ok(ChartRankResponse.of(scholarshipService.getRankChartData(1L, chartSearchRequestDto)));
    }

    @GetMapping("/chart/timeline")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_TIMELINE)
    public ResponseEntity<List<ChartTimelineResponse>> getChartMileageTimeline() {
        // ToDo 지금은 1L로 하지만 나중에 바꿀 예정
        List<ChartTimelineResponse> responses = scholarshipService.getChartTimelines(1L)
                                                                  .stream()
                                                                  .map(ChartTimelineResponse::of)
                                                                  .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/weight")
    @ApiOperation(value = ApiDoc.CHART_SCHOLARSHIP_WEIGHT)
    public ResponseEntity<List<Long>> getChartWeightDistribution(@RequestParam String semester) {
        return ResponseEntity.ok(scholarshipService.getChartWeightDistribution(semester));
    }

    @GetMapping("/chart/grade")
    @ApiOperation(value = ApiDoc.CHART_SCHOLARSHIP_GRADE)
    public ResponseEntity<List<ChartGradeResponse>> getChartGradeDistribution(@RequestParam String semester) {
        List<ChartGradeResponse> responses = scholarshipService.getChartGradeDistribution(semester)
                                                               .stream()
                                                               .map(ChartGradeResponse::of)
                                                               .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
