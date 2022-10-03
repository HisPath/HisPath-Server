package com.server.hispath.student.presentation.response;

import com.server.hispath.student.application.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse {
<<<<<<< HEAD
    private Long studentId;
=======
    private Long id;
>>>>>>> 8ab20c858744d241a9fa9cce8c358a8450ff90b5
    private String name;

    public static StudentResponse from (StudentDto dto) {
        return new StudentResponse(dto.getId(), dto.getName());
    }
}
