package com.server.hispath.activity.presentation.response;

import com.server.hispath.activity.application.dto.ActivityParticipantDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParticipantResponse {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private String section;
    private String data;
    private boolean isMileage;

    public static ActivityParticipantResponse of(ActivityParticipantDto dto) {
        return new ActivityParticipantResponse(dto.getId(), dto.getSemester(), dto.getName(), dto.getRemark(),
                dto.getParticipant().getSection().getName(), dto.getParticipant()
                                                                .getData(), dto.getRequestStatus() == 1);
    }
}
