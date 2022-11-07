package com.server.hispath.scholarship.presentation.response;

import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.scholarship.application.dto.ScholarshipDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipActivityResponse {
    private Long id;
    private String categoryName;
    private String name;
    private String remark;
    private String semester;
    private int weight;

    public static ScholarshipActivityResponse of(ActivityDto dto){
        return new ScholarshipActivityResponse(dto.getId(), dto.getCategoryDto().getName(), dto.getName(),
                dto.getRemark(), dto.getSemester(), dto.getWeight());
    }
}
