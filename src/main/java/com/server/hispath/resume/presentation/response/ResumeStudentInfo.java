package com.server.hispath.resume.presentation.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.dto.ActivityParticipantDto;
import com.server.hispath.activity.presentation.response.ActivitySimpleResponse;
import com.server.hispath.student.application.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeStudentInfo {
    private String studentName;
    private String phone;
    private String profile;
    private String email;
    private String departmentName;
    private String major1;
    private String major2;
    private String blog;
    private String githubId;
    private List<ActivitySimpleResponse> activities = new ArrayList<>();

    public ResumeStudentInfo(StudentDto studentDto, List<ActivityParticipantDto> activityDtos){
        this.studentName = studentDto.getName();
        this.phone = studentDto.getPhone();
        this.profile = studentDto.getProfile();
        this.email = studentDto.getEmail();
        this.departmentName = studentDto.getDepartmentDto().getName();
        this.major1 = studentDto.getMajor1().getName();
        this.major2 = studentDto.getMajor2().getName();
        this.blog = studentDto.getBlog();
        this.githubId = studentDto.getGithubId();
        this.activities = activityDtos.stream()
                                      .map(ActivitySimpleResponse::of)
                                      .collect(Collectors.toList());
    }
}
