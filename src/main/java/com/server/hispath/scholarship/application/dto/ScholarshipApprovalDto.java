package com.server.hispath.scholarship.application.dto;

import java.util.Arrays;
import java.util.List;

import com.server.hispath.scholarship.domain.Scholarship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipApprovalDto {
    private String name;
    private String studentNum;
    private int studentSemester;
    private int weight;
    private String result;

    @Override
    public String toString() {
        return "학생명: " + name + " 학번: " + studentNum + " 학생 학기수: " + studentSemester + " 전체 가중치: " + weight + " 결과: " + result;
    }

    public static ScholarshipApprovalDto from(Scholarship scholarship) {
        return new ScholarshipApprovalDto(scholarship.getStudent().getName(), scholarship.getStudent().getStudentNum(),
                scholarship.getStudentSemester(), scholarship.getTotalMileage(), "");
    }

    public List<String> getStringList() {
        return Arrays.asList(this.studentNum, this.name, String.valueOf(this.studentSemester), String.valueOf(this.weight), this.result);
    }

    public String StudentInfo() {
        return studentNum + " " + name;
    }
}
