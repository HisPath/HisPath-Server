package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.presentation.request.StudentActivityCURequest;
import com.server.hispath.student.domain.Section;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantContentDto {
    private Section section;
    private String data;

    public static ParticipantContentDto of(StudentActivityCURequest request) {
        return new ParticipantContentDto(Section.find(request.getSection()), request.getData());
    }
}
