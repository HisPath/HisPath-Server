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
    private List<Notice> notices = new ArrayList<>();
    private String name;
    private String email;
    private String department;

    public static ManagerDto from (Manager manager){
        return new ManagerDto(manager.getId(), manager.getNotices(), manager.getName(),
                manager.getEmail(), manager.getDepartment());
    }


}
