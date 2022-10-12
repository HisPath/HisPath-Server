package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.category.application.dto.CategoryDto;
import com.server.hispath.student.application.dto.StudentRefDetailDto;
import com.server.hispath.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MStudentActivityDetailDto {
//    private Long id;
//    private String name;
//    private CategoryDto category;
//    private int studentNum;
//    private String semester;
//    private String remark;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//    private int weight;


    private Long id;

    private String name;

    private String studentNum;

    private int semester;



    private List<ActivityDto> activities = new ArrayList<>();

    public static MStudentActivityDetailDto from(Student student, List<ActivityDto> activities) {
        return new MStudentActivityDetailDto(student.getId(), student.getName(), student.getStudentNum(), student.getSemester() ,activities);

    }
}
