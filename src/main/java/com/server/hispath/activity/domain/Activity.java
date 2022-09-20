package com.server.hispath.activity.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.student.domain.participate.Participant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE activity SET deleted = true Where id = ?")
public class Activity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String semester;

    private boolean personel;

    private int requestStatus;

    private String date;

    @OneToMany(mappedBy = "activity")
    private List<Participant> participants = new ArrayList<>();
}
