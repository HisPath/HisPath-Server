package com.server.hispath.scholarship.presentation.response;

import java.util.ArrayList;
import java.util.List;

import com.server.hispath.scholarship.application.dto.ScholarshipDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipDetailResponse {
    private String name;
    private String studentNum;
    private String semester;
    private int studentSemester;
    private String departmentName;
    private String major1Name;
    private String major2Name;
    private String email;
    private String phone;
    private int totalWeight;
    private String result;
    private List<ScholarshipActivityResponse> activities = new ArrayList<>();

    public static ScholarshipDetailResponse from(ScholarshipDto dto, List<ScholarshipActivityResponse> activities) {
        return new ScholarshipDetailResponse(dto.getName(), dto.getStudentNum(), dto.getSemester(), dto.getStudentSemester(),
                dto.getDepartmentName(), dto.getMajor1Name(), dto.getMajor2Name(), dto.getEmail(),
                dto.getPhone(), dto.getTotalWeight(), dto.getResult(), activities);
    }
}
