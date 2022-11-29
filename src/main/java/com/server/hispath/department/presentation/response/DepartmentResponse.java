package com.server.hispath.department.presentation.response;

import com.server.hispath.department.application.dto.DepartmentDto;
import com.server.hispath.student.application.dto.StudentDto;
import com.server.hispath.student.presentation.response.StudentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentResponse {
    private Long departmentId;
    private String name;
    private String profile;

    public static DepartmentResponse from (DepartmentDto dto) {
        return new DepartmentResponse(dto.getId(), dto.getName(), dto.getProfile());
    }
}
