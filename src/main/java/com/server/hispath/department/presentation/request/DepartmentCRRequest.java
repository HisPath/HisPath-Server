package com.server.hispath.department.presentation.request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentCRRequest {
    private Long departmentId;
    private String name;
}
