package com.server.hispath.student.application.dto;

import com.server.hispath.student.domain.Student;
import com.server.hispath.student.presentation.request.StudentCURequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCUDto {
    private Long departmentId;
//    private Long major1Id;
//    private Long major2Id;
    private String studentNum;
    private int semester;
    private String name;
    private String phone;
    private String email;
    private String profile;
    private String blog;
    private String githubId;
    private String readme;

    public static StudentCUDto of(StudentCURequest request) {
        return new StudentCUDto(request.getDepartmentId(), request.getStudentNum(), request.getSemester(), request.getName(), request.getPhone(),
                request.getEmail(), request.getProfile(), request.getBlog(), request.getGithubId(), request.getReadme());
    }

}
