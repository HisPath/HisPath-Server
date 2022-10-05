package com.server.hispath.activity.presentation.response;

import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.SemesterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterResponse {
    private String semester;

    public static SemesterResponse from(SemesterDto dto) {
        return new SemesterResponse(dto.getSemester());
    }
}
