package com.server.hispath.scholarship.domain;

import javax.persistence.*;

import com.server.hispath.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE scholarShip SET deleted = true Where id = ?")
public class Scholarship extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ToDO : 나중 구현 예정
}
