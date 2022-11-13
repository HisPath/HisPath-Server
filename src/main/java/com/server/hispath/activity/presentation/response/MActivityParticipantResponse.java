package com.server.hispath.activity.presentation.response;

import com.server.hispath.activity.application.dto.ActivityParticipantDto;
import com.server.hispath.activity.application.dto.MActivityParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MActivityParticipantResponse {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private String category;
    private String data;

    public static MActivityParticipantResponse of(MActivityParticipantDto dto) {
        return new MActivityParticipantResponse(dto.getId(), dto.getSemester(), dto.getName(), dto.getRemark(),

                dto.getParticipant().getCategory().getName(), dto.getParticipant().getData());
    }
}
