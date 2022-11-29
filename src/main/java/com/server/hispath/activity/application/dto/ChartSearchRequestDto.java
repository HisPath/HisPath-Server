package com.server.hispath.activity.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartSearchRequestDto {
    private String semester;
    private Integer grade;
    private String department;
}
