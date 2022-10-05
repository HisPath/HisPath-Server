package com.server.hispath.student.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import com.server.hispath.common.BaseEntity;
import com.server.hispath.department.domain.Department;
import com.server.hispath.major.domain.Major;
import com.server.hispath.student.application.dto.StudentCUDto;
import com.server.hispath.student.application.dto.StudentRefDto;
import com.server.hispath.student.domain.participate.Participant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE student SET deleted = true Where id = ?")
@AllArgsConstructor
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String studentNum;

    private int semester;

    private String phone;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Major major1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Major major2;

    private Long loginCnt;

    private String profile;

    private String blog;

    private String githubId;


    private LocalDateTime lastLoginDate;

    private String readme;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();


    public void update(Department department, StudentCUDto dto) {
        this.name = dto.getName();
        this.studentNum = dto.getStudentNum();
        this.semester = dto.getSemester();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.profile = dto.getProfile();
        this.department = department;
//        this.major1 = major;
//        this.major2 = major;
        this.blog = dto.getBlog();
        this.githubId = dto.getGithubId();
        this.readme = dto.getReadme();
    }

    public static Student from(StudentCUDto dto, Department department) {
        return Student.builder()
                .name(dto.getName())
                .department(department)
                .studentNum(dto.getStudentNum())
                .semester(dto.getSemester())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .profile(dto.getProfile())
                .blog(dto.getBlog())
                .githubId(dto.getGithubId())
                .loginCnt(0L)
                .readme(dto.getReadme())
                .build();
    }

    public static Student from(StudentRefDto dto) {
        return Student.builder()
                .name(dto.getName())
                .build();
    }
    public boolean isNameMatch(String name) {
        return Objects.equals(this.name, name);
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }
}
