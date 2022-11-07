package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.presentation.request.MStudentActivityCURequest;
import com.server.hispath.activity.presentation.request.StudentActivityCURequest;
import com.server.hispath.category.domain.Category;
import com.server.hispath.student.domain.Participant;
import com.server.hispath.student.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MParticipantContentDto {
    private Category category;
    private String data;

    public static MParticipantContentDto of(MStudentActivityCURequest request) {
        return new MParticipantContentDto(Category.fromString(request.getCategory()), request.getData());
    }
    public static MParticipantContentDto of(Participant participant) {
        return new MParticipantContentDto(participant.getActivity().getCategory(), participant.getData());
    }
}

