package com.server.hispath.student.domain.participate;

import javax.persistence.*;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.student.domain.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE participate SET deleted = true Where id = ?")
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;
}
