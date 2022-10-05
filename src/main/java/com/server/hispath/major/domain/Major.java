package com.server.hispath.major.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.common.BaseEntity;

import com.server.hispath.major.application.dto.MajorContentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE major SET deleted = true Where id = ?")
public class Major extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static Major from(MajorContentDto dto){
        return Major.builder()
                .name(dto.getMajorName())
                .build();
    }

    public void update(MajorContentDto dto){
        this.name = dto.getMajorName();
    }



}
