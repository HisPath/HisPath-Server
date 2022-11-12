package com.server.hispath.activity.presentation.response;

import com.server.hispath.activity.application.dto.ActivityParticipantDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParticipantStatusResponse {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private String section;
    private int requestStatus;

    public static ActivityParticipantStatusResponse of(ActivityParticipantDto dto) {
        return new ActivityParticipantStatusResponse(dto.getId(), dto.getSemester(), dto.getName(),
                dto.getRemark(), dto.getParticipant().getSection().getName(), dto.getRequestStatus());
    }
}
