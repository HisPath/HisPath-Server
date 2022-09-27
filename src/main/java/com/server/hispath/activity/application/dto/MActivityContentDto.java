package com.server.hispath.activity.application.dto;

import java.time.LocalDateTime;

import com.server.hispath.activity.presentation.request.MActivityCURequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MActivityContentDto {
    private Long categoryId;
    private String semester;
    private String name;
    private String remark;
    private int weight;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static MActivityContentDto of(MActivityCURequest request) {
        return new MActivityContentDto(request.getCategoryId(), request.getSemester(), request.getName(),
                request.getRemark(), request.getWeight(), request.getStartDate(), request.getEndDate());
    }
}
