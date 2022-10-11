package com.server.hispath.scholarship.application.dto;

import com.server.hispath.scholarship.domain.Scholarship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipContentDto {
    private Long id;
    private String semester;
    private int totalMileage;
    private boolean approved;

    public static ScholarshipContentDto from(Scholarship scholarship) {
        return new ScholarshipContentDto(scholarship.getId(), scholarship.getSemester(), scholarship.getTotalMileage(), scholarship.isApproved());
    }

}
