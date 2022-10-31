package com.server.hispath.scholarship.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipApprovalDto {
    private String name;
    private String studentNum;
    private int weight;
    private String result;

    @Override
    public String toString() {
        return "학생명: " + name + " 학번: " + studentNum + " 전체 가중치: " + weight + " 결과: " + result;
    }

    public String StudentInfo(){
        return studentNum + " " + name;
    }
}
