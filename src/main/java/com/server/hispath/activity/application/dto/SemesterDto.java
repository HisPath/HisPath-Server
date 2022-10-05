package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.domain.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDto {
    private String semester;

    public static SemesterDto from(Activity activity) {
        return new SemesterDto(activity.getSemester());
    }
}



