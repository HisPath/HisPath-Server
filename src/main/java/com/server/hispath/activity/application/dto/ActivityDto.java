package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.category.application.dto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Long id;
    private CategoryDto categoryDto;
    private String semester;
    private boolean personal;
    private String name;
    private String remark;
    private int weight;
    private boolean studentRegistered;

    public static ActivityDto from(Activity activity) {
        return new ActivityDto(activity.getId(), CategoryDto.from(activity.getCategory()), activity.getSemester(),
                activity.isPersonal(), activity.getName(), activity.getRemark(), activity.getWeight(),
                activity.isStudentRegistered());
    }
}
