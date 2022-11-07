package com.server.hispath.scholarship.application.dto;

import com.server.hispath.scholarship.domain.Scholarship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipDto {
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

    public static ScholarshipDto of(Scholarship scholarship) {
        return new ScholarshipDto(scholarship.getStudent().getName(), scholarship.getStudent().getStudentNum(),
                scholarship.getSemester(), scholarship.getStudentSemester(), scholarship.getSDepartment().getName(),
                scholarship.getSMajor1().getName(), scholarship.getSMajor2().getName(), scholarship.getStudent()
                                                                                                   .getEmail(),
                scholarship.getStudent().getPhone(), scholarship.getResult(), scholarship.getTotalMileage());
    }
}
