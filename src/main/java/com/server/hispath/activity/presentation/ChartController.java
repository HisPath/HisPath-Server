package com.server.hispath.activity.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ChartGradeDataDto;
import com.server.hispath.activity.application.dto.ChartSearchRequestDto;
import com.server.hispath.activity.presentation.response.chart.*;
import com.server.hispath.auth.domain.LoginStudent;
import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.auth.domain.StudentLogin;
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
    @RequiredLogin
    public ResponseEntity<List<ChartDataResponse>> getChartMileageData(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department,
            @StudentLogin LoginStudent loginStudent
    ) {

        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department);
        List<ChartDataResponse> responses = activityService.getChartDatasByCategory(loginStudent.getId(), chartSearchRequestDto)
                                                           .stream()
                                                           .map(ChartDataResponse::of)
                                                           .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/popularity")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_POPULARITY_CATEGORY)
    @RequiredLogin
    public ResponseEntity<List<ChartCategoryResponse>> getChartMileagePopularity(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department,
            @StudentLogin LoginStudent loginStudent
    ) {

        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department);
        List<ChartCategoryResponse> responses = activityService.getChartTotalDatasByCategory(loginStudent.getId(), chartSearchRequestDto)
                                                               .stream()
                                                               .map(ChartCategoryResponse::of)
                                                               .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/rank")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_RANK)
    @RequiredLogin
    public ResponseEntity<ChartRankResponse> getChartMileageRank(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department,
            @StudentLogin LoginStudent loginStudent
    ) {

        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department);
        return ResponseEntity.ok(ChartRankResponse.of(scholarshipService.getRankChartData(loginStudent.getId(), chartSearchRequestDto)));
    }

    @GetMapping("/chart/timeline")
    @ApiOperation(value = ApiDoc.CHART_MILEAGE_TIMELINE)
    @RequiredLogin
    public ResponseEntity<List<ChartTimelineResponse>> getChartMileageTimeline(@StudentLogin LoginStudent loginStudent) {

        List<ChartTimelineResponse> responses = scholarshipService.getChartTimelines(loginStudent.getId())
                                                                  .stream()
                                                                  .map(ChartTimelineResponse::of)
                                                                  .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/weight")
    @ApiOperation(value = ApiDoc.CHART_SCHOLARSHIP_WEIGHT)
    @RequiredManagerLogin
    public ResponseEntity<List<Long>> getChartWeightDistribution(@RequestParam String semester) {

        return ResponseEntity.ok(scholarshipService.getChartWeightDistribution(semester));
    }

    @GetMapping("/chart/grade")
    @ApiOperation(value = ApiDoc.CHART_SCHOLARSHIP_GRADE)
    @RequiredManagerLogin
    public ResponseEntity<List<ChartGradeResponse>> getChartGradeDistribution(@RequestParam String semester) {

        List<ChartGradeResponse> responses = scholarshipService.getChartGradeDistribution(semester)
                                                               .stream()
                                                               .map(ChartGradeResponse::of)
                                                               .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/department")
    @ApiOperation(value = ApiDoc.CHART_SCHOLARSHIP_DEPARTMENT)
    @RequiredManagerLogin
    public ResponseEntity<List<ChartDepartmentResponse>> getChartDepartmentDistribution(@RequestParam String semester) {

        List<ChartDepartmentResponse> responses = scholarshipService.getChartDepartmentDistribution(semester)
                                                                    .stream()
                                                                    .map(ChartDepartmentResponse::of)
                                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/chart/activities")
    @ApiOperation(value = ApiDoc.CHART_STUDENT_ACTIVITY)
    @RequiredLogin
    public ResponseEntity<List<ChartSectionResponse>> getActivityChartDatas(@StudentLogin LoginStudent loginStudent, @RequestParam String semester) {
        return ResponseEntity.ok(activityService.getChartTotalDatasBySections(loginStudent.getId(), semester));
    }
}
