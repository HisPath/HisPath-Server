package com.server.hispath.manager.application.dto;

import com.server.hispath.manager.domain.Manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDashboardDto {
    private Long id;
    private String profile;
    private String name;
    private String department;
    private boolean approved;
    private String email;
    private int power;
    private Long[] loginCounts;
    private Long totalCounts;

    public static ManagerDashboardDto of(Manager manager, Long[] loginCounts, Long totalCounts) {
        return new ManagerDashboardDto(manager.getId(), manager.getProfile(), manager.getName(), manager.getDepartment(),
                manager.isApproved(), manager.getEmail(), manager.getPower(), loginCounts, totalCounts);
    }
}
