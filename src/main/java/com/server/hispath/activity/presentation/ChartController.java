package com.server.hispath.activity.presentation;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ChartSearchRequestDto;
import com.server.hispath.docs.ApiDoc;

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

    @GetMapping("/chart/category")
    @ApiOperation(value = ApiDoc.CHART_STUDENT_CATEGORY)
    public ResponseEntity<Void> getChartDatasByCategory(
            String semester,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String major
    ){
        ChartSearchRequestDto chartSearchRequestDto = new ChartSearchRequestDto(semester, grade, department, major);
        // ToDo 지금은 1L로 하지만 나중에 바꿀 예정
        activityService.getChartDatasByCategory(1L, chartSearchRequestDto);
        return ResponseEntity.ok(null);
    }
}
