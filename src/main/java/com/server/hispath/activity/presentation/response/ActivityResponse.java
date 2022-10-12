package com.server.hispath.activity.presentation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.activity.application.dto.ActivityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String semester;
    private boolean personal;
    private String name;
    private String remark;
    private int weight;
    private boolean studentRegistered;

    public static ActivityResponse from(ActivityDto dto) {
        return new ActivityResponse(dto.getId(), dto.getCategoryDto().getId(), dto.getCategoryDto().getName(),
                dto.getSemester(), dto.isPersonal(), dto.getName(), dto.getRemark(), dto.getWeight(),
                dto.isStudentRegistered());
    }
}
