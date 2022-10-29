package com.server.hispath.scholarship.domain;

import javax.persistence.*;

import com.server.hispath.common.BaseEntity;
import com.server.hispath.department.domain.Department;
import com.server.hispath.major.domain.Major;
import com.server.hispath.student.domain.Student;
import com.sun.istack.NotNull;

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
@SQLDelete(sql = "UPDATE scholarship SET deleted = true Where id = ?")
public class Scholarship extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @NotNull
    private String semester;

    private int totalMileage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Major sMajor1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Major sMajor2;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department sDepartment;

    private String result;

    private boolean approved = false;

}
