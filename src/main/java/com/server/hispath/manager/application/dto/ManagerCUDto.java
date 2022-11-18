package com.server.hispath.manager.application.dto;

import com.server.hispath.manager.presentation.request.ManagerCURequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerCUDto {

    private String name;
    private String profile;
    private int power;
    private String email;
    private String department;

    public static ManagerCUDto of(ManagerCURequest request) {
        return new ManagerCUDto(request.getName(), request.getProfile(), request.getPower(), request.getEmail(), request.getDepartment());
    }

}