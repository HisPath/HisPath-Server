package com.server.hispath.student.application.dto;


import com.server.hispath.student.domain.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRefDetailDto {
    private Long id;
    private String name;
    private String studentNum;

    public static StudentRefDetailDto of(Student student){
        return new StudentRefDetailDto(student.getId(), student.getName(), student.getStudentNum());
    }
}
