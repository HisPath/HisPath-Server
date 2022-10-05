package com.server.hispath.department.application.dto;

import com.server.hispath.department.domain.Department;
import com.server.hispath.department.presentation.request.DepartmentCRRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;

    public static DepartmentDto from (Department department) {
        return new DepartmentDto(department.getId(), department.getName());
    }

    public static DepartmentDto of(DepartmentCRRequest request) {
        return new DepartmentDto(request.getDepartmentId(), request.getName());
    }
}
