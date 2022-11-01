package com.server.hispath.scholarship.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    private String semester;
    private String studentSemester;
    String department;
    String major1;
    String major2;
}
