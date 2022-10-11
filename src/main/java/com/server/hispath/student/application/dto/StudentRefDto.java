package com.server.hispath.student.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRefDto {
    private Long id;
    private String name;
    private String studentNum;
    private int semester;
    private Long departmentId;
    private Long major1Id;
    private Long major2Id;
    private String phone;
    private String email;
    private String profile;
    private String blog;
    private String githubId;
    private String readme;
}
