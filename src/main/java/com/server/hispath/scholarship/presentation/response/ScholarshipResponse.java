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
    private String name;
    private String studentNum;
    private String semester;
    private String departmentName;
    private String major1Name;
    private String major2Name;
    private String email;
    private String phone;
    private int totalWeight;

    public static ScholarshipResponse of(ScholarshipDto dto){
        return new ScholarshipResponse(dto.getName(), dto.getStudentNum(),
                dto.getSemester(), dto.getDepartmentName(), dto.getMajor1Name(),
                dto.getMajor2Name(), dto.getEmail(), dto.getPhone(), dto.getTotalWeight());
    }
}
