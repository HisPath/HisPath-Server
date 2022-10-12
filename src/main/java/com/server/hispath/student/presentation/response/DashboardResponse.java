package com.server.hispath.student.presentation.response;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.major.presentation.response.MajorResponse;
import com.server.hispath.notice.application.dto.DashboardNoticeDto;
import com.server.hispath.notice.presentation.response.NoticeDashboardResponse;
import com.server.hispath.student.application.dto.DashboardDto;
import com.server.hispath.student.application.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private Long id;
    private String profile;
    private String name;
    private String studentNum;
    private MajorResponse major1;
    private MajorResponse major2;
    private int semester;
    private String email;
    private String phone;
    private String githubId;
    private String blog;
    private String readme;
    private List<NoticeDashboardResponse> notice;

    public static DashboardResponse from(StudentDto studentDto, List<DashboardNoticeDto> noticeDtos) {
        return new DashboardResponse(studentDto.getId(), studentDto.getProfile(), studentDto.getName(), studentDto.getStudentNum(),
                MajorResponse.from(studentDto.getMajor1()), MajorResponse.from(studentDto.getMajor2()), studentDto.getSemester(),
                studentDto.getEmail(), studentDto.getPhone(), studentDto.getGithubId(), studentDto.getBlog(), studentDto.getReadme(),
                noticeDtos.stream().map(NoticeDashboardResponse::of).collect(Collectors.toList()));
    }
}
