package com.server.hispath.scholarship.presentation.response;

import com.server.hispath.scholarship.application.dto.ScholarshipDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipResponse {
    private Long studentId;
    private String name;
    private String studentNum;
    private String semester;
    private int studentSemester;
    private String departmentName;
    private String major1Name;
    private String major2Name;
    private String email;
    private String phone;
    private String result;
    private int totalWeight;

    public static ScholarshipResponse of(ScholarshipDto dto) {
        return new ScholarshipResponse(dto.getStudentId(), dto.getName(), dto.getStudentNum(),
                dto.getSemester(), dto.getStudentSemester(), dto.getDepartmentName(), dto.getMajor1Name(),
                dto.getMajor2Name(), dto.getEmail(), dto.getPhone(), dto.getResult(), dto.getTotalWeight());
    }
}
