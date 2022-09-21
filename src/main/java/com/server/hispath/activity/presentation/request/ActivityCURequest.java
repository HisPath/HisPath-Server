package com.server.hispath.activity.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActivityCURequest {
    private Long categoryId;
    private String semester;
    private boolean personal;
    private int requestStatus;
    private String data;
}
