package com.server.hispath.manager.presentation.response;

import com.server.hispath.manager.application.dto.ManagerDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerResponse {
    private Long id;
    private int power;
    private String name;
    private String email;
    private String department;
    private boolean approved;


    public static ManagerResponse of(ManagerDto dto) {
        return new ManagerResponse(dto.getId(), dto.getPower(), dto.getName(),
                dto.getEmail(), dto.getDepartment(), dto.isApproved());
    }

}
