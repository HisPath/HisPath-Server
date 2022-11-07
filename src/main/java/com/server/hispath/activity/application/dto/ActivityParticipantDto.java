package com.server.hispath.activity.application.dto;


import com.server.hispath.activity.domain.Activity;
import com.server.hispath.student.domain.Participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParticipantDto {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private ParticipantContentDto participant;
    private int requestStatus;
    public static ActivityParticipantDto of(Participant participant) {
        Activity activity = participant.getActivity();
        return new ActivityParticipantDto(activity.getId(), activity.getSemester(), activity.getName(),
                activity.getRemark(), ParticipantContentDto.of(participant), activity.getRequestStatus());
    }
}
