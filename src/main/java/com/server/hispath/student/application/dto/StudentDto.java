package com.server.hispath.student.application.dto;

import com.server.hispath.student.domain.Student;
import com.server.hispath.student.presentation.request.StudentCURequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;

    public static StudentDto from (Student student) {
        return new StudentDto(student.getId(), student.getName());
    }

    public static StudentDto of(StudentCURequest request) {
        return new StudentDto(request.getStudentId(), request.getName());
    }
}
