package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.presentation.request.ActivityCURequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityContentDto {

    private String semester;
    private boolean personal;
    private int requestStatus;

    public static ActivityContentDto from(ActivityCURequest request){
        return new ActivityContentDto(request.getSemester(), request.isPersonal(), request.getRequestStatus());
    }
}
