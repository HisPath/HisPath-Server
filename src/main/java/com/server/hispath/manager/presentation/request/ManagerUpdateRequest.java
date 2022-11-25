package com.server.hispath.manager.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManagerUpdateRequest {

    private String name;
    private String profile;
    private String email;
    private String department;
    private int power;

}
