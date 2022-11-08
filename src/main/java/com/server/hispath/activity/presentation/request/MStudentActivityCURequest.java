package com.server.hispath.activity.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MStudentActivityCURequest {

    private String semester;
    private String name;
    private String remark;
    private String category;
    private String data;
}
