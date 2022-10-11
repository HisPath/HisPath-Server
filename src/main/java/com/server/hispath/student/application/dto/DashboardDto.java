package com.server.hispath.student.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    private Long id;
    private String profile;
    private String name;
    private String studentNum;
    private String major1;
    private String major2;
    private int semester;
    private String email;
    private String phone;
    private String githubId;
    private String blog;
    private String readme;
}
