package com.server.hispath.activity.application.dto;

import com.server.hispath.scholarship.domain.Scholarship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartTimelineDto {
    private String semester;
    private int totalWeight;

    public static ChartTimelineDto of(Scholarship scholarship){
        return new ChartTimelineDto(scholarship.getSemester(), scholarship.getTotalMileage());
    }
}
