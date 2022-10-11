package com.server.hispath.student.presentation.response;

import com.server.hispath.student.application.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse {
    private Long studentId;
    private String name;
    private int semester;
    private String studentNum;
    private String phone;
    private String email;
    private String profile;
    private Long departmentId;
    private String departmentName;
    private String major1;
    private String major2;
    private long loginCnt;
    private String blog;
    private String githubId;
    private String readme;

    public static StudentResponse from (StudentDto dto) {
        return new StudentResponse(dto.getId(), dto.getName(), dto.getSemester(), dto.getStudentNum(), dto.getPhone(), dto.getEmail(),
                dto.getProfile(), dto.getDepartmentDto().getId(), dto.getDepartmentDto().getName(),
                dto.getMajor1().getName(), dto.getMajor2().getName(),
                dto.getLoginCnt(),
                dto.getBlog(), dto.getGithubId(), dto.getReadme());
    }
}
