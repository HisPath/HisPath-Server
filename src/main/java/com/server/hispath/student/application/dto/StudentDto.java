package com.server.hispath.student.application.dto;

import com.server.hispath.category.application.dto.CategoryDto;
import com.server.hispath.department.application.dto.DepartmentDto;
import com.server.hispath.major.application.dto.MajorDto;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.presentation.request.StudentCURequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private int semester;
    private String studentNum;
    private String phone;
    private String email;
    private String profile;
    private DepartmentDto departmentDto;
    private MajorDto major1;
    private MajorDto major2;
    private long loginCnt;
    private String blog;
    private String githubId;
    private String readme;

    public static StudentDto from (Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getSemester(),  student.getStudentNum(), student.getPhone(),
                student.getEmail(), student.getProfile(), DepartmentDto.from(student.getDepartment()),
                MajorDto.from(student.getMajor1()), MajorDto.from(student.getMajor2()),
                student.getLoginCnt(), student.getBlog(), student.getGithubId(), student.getReadme());
    }

}
