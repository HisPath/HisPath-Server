package com.server.hispath.manager.application.dto;

import com.server.hispath.manager.presentation.request.ManagerUpdateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerUpdateDto {
    private String name;
    private String profile;
    private String email;
    private String department;
    private int power;

    public static ManagerUpdateDto from(ManagerUpdateRequest request){
        return new ManagerUpdateDto(request.getName(), request.getProfile(), request.getEmail(), request.getDepartment(), request.getPower());
    }
}
