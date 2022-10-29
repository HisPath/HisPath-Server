package com.server.hispath.activity.presentation.response;

import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.ActivityParticipantDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySimpleResponse {
    private Long id;
    private String name;
    private String semester;

    public static ActivitySimpleResponse of(ActivityParticipantDto activityDto){
        return new ActivitySimpleResponse(activityDto.getId(), activityDto.getName(), activityDto.getSemester());
    }
}
