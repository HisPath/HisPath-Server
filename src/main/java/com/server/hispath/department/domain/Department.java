package com.server.hispath.department.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.server.hispath.common.BaseEntity;

import com.server.hispath.department.application.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE department SET deleted = true Where id = ?")
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profile;

    public void update(DepartmentDto dto) {
        this.name = dto.getName();
    }

    public static Department from(DepartmentDto dto) {
        return Department.builder()
                .name(dto.getName())
                .build();
    }
}

