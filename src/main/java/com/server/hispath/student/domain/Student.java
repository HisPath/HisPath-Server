package com.server.hispath.student.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.major.domain.Major;
import com.server.hispath.student.application.dto.StudentDto;
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
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE student SET deleted = true Where id = ?")
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String studentNum;

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

    @OneToMany(mappedBy = "student")
    private List<Participant> participants = new ArrayList<>();

    public void update(StudentDto dto) {
        this.name = dto.getName();
    }
    public static Student from(StudentDto dto) {
        return Student.builder()
                .name(dto.getName())
                .build();
    }
}
