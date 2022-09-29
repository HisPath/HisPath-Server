package com.server.hispath.activity.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.category.application.dto.CategoryDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.student.application.dto.StudentRefDetailDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MActivityDetailDto {
    private Long id;
    private String name;
    private CategoryDto category;
    private String semester;
    private String remark;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int weight;
    private List<StudentRefDetailDto> students = new ArrayList<>();


    public static MActivityDetailDto from(Activity activity, List<StudentRefDetailDto> students) {
        return new MActivityDetailDto(activity.getId(), activity.getName(), CategoryDto.from(activity.getCategory()),
                activity.getSemester(), activity.getRemark(), activity.getStartDate(), activity.getEndDate(), activity.getWeight(), students);

    }
}
