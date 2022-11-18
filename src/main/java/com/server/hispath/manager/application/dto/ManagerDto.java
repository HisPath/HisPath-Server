package com.server.hispath.manager.application.dto;

import com.server.hispath.manager.domain.Manager;
import com.server.hispath.notice.domain.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    private Long id;
    private String name;
    private String email;
    private String profile;
    private String department;
    private int power;
    private boolean approved;

    public static ManagerDto of(Manager manager) {
        return new ManagerDto(manager.getId(), manager.getName(), manager.getEmail(),
                manager.getProfile(), manager.getDepartment(), manager.getPower(), manager.isApproved());
    }


}
