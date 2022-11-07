package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.student.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MActivityParticipantDto {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private MParticipantContentDto participant;

    public static MActivityParticipantDto of(Participant participant) {
        Activity activity = participant.getActivity();
        return new MActivityParticipantDto(activity.getId(), activity.getSemester(), activity.getName(),
                activity.getRemark(), MParticipantContentDto.of(participant));
    }
}
