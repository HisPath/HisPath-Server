package com.server.hispath.category.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.ActivityType;
import com.server.hispath.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE category SET deleted = true Where id = ?")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Activity> activities = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ActivityType type;
}
