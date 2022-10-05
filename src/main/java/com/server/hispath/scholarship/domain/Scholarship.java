package com.server.hispath.scholarship.domain;

import javax.persistence.*;

import com.server.hispath.common.BaseEntity;
import com.server.hispath.scholarship.application.dto.ScholarshipCUDto;
import com.server.hispath.student.domain.Student;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE scholarShip SET deleted = true Where id = ?")
public class Scholarship extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "student")
//    private List<Student> students = new ArrayList<>();

    @NotNull
    private String semester;

    private int totalMileage;

    private boolean approved;

    public static Scholarship from(ScholarshipCUDto dto){
        return Scholarship.builder().semester(dto.getSemester()).build();
    }
    //ToDO : 나중 구현 예정
}
