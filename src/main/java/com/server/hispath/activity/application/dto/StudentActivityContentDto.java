package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.presentation.request.StudentActivityCURequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentActivityContentDto {
    private String semester;
    private String name;
    private String remark;

    public static StudentActivityContentDto of(StudentActivityCURequest request){
        return new StudentActivityContentDto(request.getSemester(), request.getName(), request.getRemark());
    }
}
