package com.server.hispath.student.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentCURequest {
    private Long departmentId;
    private Long major1Id;
    private Long major2Id;
    private String studentNum;
    private int semester;
    private String name;
    private String phone;
    private String email;
    private String profile;
    private String blog;
    private String githubId;
    private String readme;
}
