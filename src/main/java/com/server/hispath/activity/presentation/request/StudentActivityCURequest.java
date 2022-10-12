package com.server.hispath.activity.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentActivityCURequest {

    private String semester;
    private String name;
    private String remark;
    private String section;
    private String data;
}
