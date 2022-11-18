package com.server.hispath.manager.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManagerCURequest {

    private String name;
    private String profile;
    private int power;
    private String email;
    private String department;

}
