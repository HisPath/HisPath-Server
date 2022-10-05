package com.server.hispath.student.presentation.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentCURequest {
    private Long studentId;
    private String name;
}
