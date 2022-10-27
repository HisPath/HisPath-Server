package com.server.hispath.manager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.server.hispath.common.BaseEntity;
import com.server.hispath.manager.application.dto.ManagerCUDto;

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
@SQLDelete(sql = "UPDATE manager SET deleted = true Where id = ?")
public class Manager extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int power;
    private String name;
    private String email;
    private String department;
    private boolean approved;


    public static Manager of(ManagerCUDto dto) {
        return Manager.builder()
                      .power(dto.getPower())
                      .name(dto.getName())
                      .email(dto.getEmail())
                      .department(dto.getDepartment())
                      .approved(false)
                      .build();
    }

    public void update(ManagerCUDto dto){
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.department = dto.getDepartment();
        this.power = dto.getPower();
    }

    public void approve() {
        this.approved = true;
    }

    public boolean isSuperManager(){
        return this.power > 1;
    }
}
