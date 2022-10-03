package com.server.hispath.student.presentation.response;

import com.server.hispath.student.application.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;

    public static StudentResponse from (StudentDto dto) {
        return new StudentResponse(dto.getId(), dto.getName());
    }
}
