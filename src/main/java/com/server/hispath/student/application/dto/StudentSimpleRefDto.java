package com.server.hispath.student.application.dto;

import com.server.hispath.activity.presentation.request.MStudentRegisterRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSimpleRefDto {
    private String studentNum;
    private String name;

    public static StudentSimpleRefDto of(MStudentRegisterRequest request){
        return new StudentSimpleRefDto(request.getStudentNum(), request.getName());
    }
}
