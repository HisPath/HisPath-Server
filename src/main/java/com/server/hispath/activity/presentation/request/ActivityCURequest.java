package com.server.hispath.activity.presentation.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String name;
    private String remark;
    private int weight;

}
