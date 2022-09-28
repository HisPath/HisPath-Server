package com.server.hispath.activity.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MParticipantRequest {
    private Long activityId;
    private Long studentId;
}
