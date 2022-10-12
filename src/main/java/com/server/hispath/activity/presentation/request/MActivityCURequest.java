package com.server.hispath.activity.presentation.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MActivityCURequest {
    private String semester;
    private Long categoryId;
    private String name;
    private String remark;
    private int weight;

}
