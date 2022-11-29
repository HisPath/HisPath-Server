package com.server.hispath.scholarship.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    private String semester;
    private String studentSemester;
    private String department;
    private String major1;
    private String major2;
}
