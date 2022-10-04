package com.server.hispath.student.presentation.request;

import com.server.hispath.major.domain.Major;
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
    private String phone;
    private String email;
    private String profile;
//    private Department department;
    private Major major1;
    private Major major2;
    private long loginCnt;
    private String blog;
    private String githubId;
}
