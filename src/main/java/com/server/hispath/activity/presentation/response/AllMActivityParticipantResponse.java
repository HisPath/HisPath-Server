package com.server.hispath.activity.presentation.response;

import com.server.hispath.activity.application.dto.AllMActivityParticipantDto;
import com.server.hispath.activity.application.dto.MActivityParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllMActivityParticipantResponse {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private String category;

    private boolean participated;

    public static AllMActivityParticipantResponse of(AllMActivityParticipantDto dto) {
        return new AllMActivityParticipantResponse(dto.getId(), dto.getSemester(), dto.getName(), dto.getRemark(),
                dto.getCategory().getName(), dto.isParticipated());

    }
}