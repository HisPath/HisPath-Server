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
    private String profile;

    public DepartmentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DepartmentDto from (Department department) {
        return new DepartmentDto(department.getId(), department.getName(), department.getProfile());
    }

    public static DepartmentDto from(DepartmentCRRequest request) {
        return new DepartmentDto(request.getDepartmentId(), request.getName());
    }
}
